package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();		
	}
	
	public Producto findById(Long id){
		return productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));		
	}
	
	@Transactional
	public Producto saveProducto(Producto producto){
		return productoRepository.save(producto);
	}
	
	@Transactional
	public Producto updateProductoById(Long id, Producto productoDetails) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
		producto.setNombre(productoDetails.getNombre());
		producto.setDescripcion(productoDetails.getDescripcion());
		producto.setPrecio(productoDetails.getPrecio());
		producto.setStock(producto.getStock() + productoDetails.getStock());
		
		return productoRepository.save(producto);
	}
	

	public String validarStock(Long id) {
		String infoStock = "STOCK NO DISPONIBLE";
	    Producto producto = productoRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	    
	    if(producto.getStock() > 0) 
	    	return infoStock = "STOCK DISPONIBLE: " + producto.getStock();
	    
	    return infoStock;
	    
	}
	

	public void deleteProductoById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		
		productoRepository.deleteById(id);
	}
	
	

}