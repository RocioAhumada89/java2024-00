package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.DetalleVenta;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	public List<Venta> getAllVentas(){
		return ventaRepository.findAll();		
	}
	
	public Venta findById(Long id){
		return ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));		
	}
	
	
	@Transactional
	public Venta saveVenta(Venta venta){
		double total = 0;
		
		for (DetalleVenta detalle : venta.getDetalles()) {
			detalle.setVenta(venta);
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
            			.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));	
            	
           detalle.setSubtotal(producto.getPrecio() * detalle.getCantidad());
            	
    	   total += detalle.getSubtotal();
    	   
		}
        
		venta.setTotal(total);
	
		return ventaRepository.save(venta);
		
	}
	
	

}
