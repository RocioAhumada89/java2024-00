package com.coderhouse.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.coderhouse.dtos.TimeResponseDTO;
import com.coderhouse.dtos.VentaDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.DetalleVenta;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentaRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.client.RestClientException;

import static com.coderhouse.utils.Util.convertirFecha;

@Service
public class VentaService {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DetalleRepository detalleRepository;

	@Autowired
	private FechaService fechaService;
	
	
	public List<VentaDTO> getAllVentas(){
		List<Venta> ventas = ventaRepository.findAll();

		List<VentaDTO> ventasDTO = new ArrayList<>();

		for (Venta venta : ventas) {
			ventasDTO.add(new VentaDTO(venta));
		}

		return ventasDTO;

	}
	
	public VentaDTO findById(Long id){
		Venta venta = ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
		return new VentaDTO(venta);
	}

	@Transactional
	public VentaDTO saveVenta(Venta venta){
		double total = 0;

		TimeResponseDTO fechaActual = fechaService.obtenerFechaActual();
		if (fechaActual == null) {
			throw new RestClientException("Error, servicio de la API No Disponible.");
		}

		LocalDateTime fechaVenta = convertirFecha(fechaActual);

		Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

		for (DetalleVenta detalle : venta.getDetalles()) {
			detalle.setVenta(venta);

			Producto producto = productoRepository.findById(detalle.getProducto().getId())
					.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

			detalle.getProducto().setNombre(producto.getNombre());
			detalle.getProducto().setDescripcion(producto.getDescripcion());
			detalle.getProducto().setCategoria(producto.getCategoria());
			producto.setStock(producto.getStock() - detalle.getCantidad());

			detalle.setSubtotal(producto.getPrecio() * detalle.getCantidad());

			total += detalle.getSubtotal();

		}

		venta.getCliente().setNombre(cliente.getNombre());
		venta.getCliente().setApellido(cliente.getApellido());
		venta.setTotal(total);
		venta.setFecha(fechaVenta);

		return new VentaDTO(ventaRepository.save(venta));


	}


	@Transactional
	public Venta updateDetalleVentaById(Long id, List<DetalleVenta> detallesActualizados) {
		Venta ventaExistente = ventaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

		double total = 0;

		for (DetalleVenta detalleExistente : ventaExistente.getDetalles()) {
			for (DetalleVenta detalleActualizado : detallesActualizados) {
				if (detalleExistente.getId().equals(detalleActualizado.getId())) {

					Producto producto = productoRepository.findById(detalleExistente.getProducto().getId())
							.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

					if (detalleActualizado.getCantidad() != null) {
						detalleExistente.setCantidad(detalleActualizado.getCantidad());
						producto.setStock(producto.getStock() - detalleActualizado.getCantidad());
					}

					detalleExistente.setSubtotal(producto.getPrecio() * detalleExistente.getCantidad());
					break;
				}
			}
			total += detalleExistente.getSubtotal();
		}

		ventaExistente.setTotal(total);

		return ventaRepository.save(ventaExistente);
	}


	@Transactional
	public Venta deleteDetalleVentaById(Long id, List<Long> detalleIds) {
		Venta ventaExistente = ventaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

		for (Long detalleId : detalleIds) {
			detalleRepository.deleteById(detalleId);
		}

		ventaExistente.getDetalles().removeIf(detalle -> detalleIds.contains(detalle.getId()));

		double total = 0;
		for (DetalleVenta detalle : ventaExistente.getDetalles()) {
			total += detalle.getSubtotal();
		}

		ventaExistente.setTotal(total);

		return ventaRepository.save(ventaExistente);
	}


}
