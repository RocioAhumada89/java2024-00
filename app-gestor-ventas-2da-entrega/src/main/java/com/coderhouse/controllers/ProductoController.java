package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		try {
			List<Producto> productos = productoService.getAllProductos();
			return ResponseEntity.ok(productos);
		}catch(Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
		try {
			Producto producto = productoService.findById(id);
			return ResponseEntity.ok(producto);
		}catch(IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}catch(Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/validar-stock/{id}")
    public ResponseEntity<String> validarStock(@PathVariable Long id) {
		try {
			String infoStock = productoService.validarStock(id);
			return ResponseEntity.ok(infoStock);
		}catch(IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}catch(Exception e){
			return ResponseEntity.internalServerError().build();
		}
		
    }
	
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto productoCreado = productoService.saveProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
		}catch(Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProductoById(@PathVariable Long id, @RequestBody Producto productoModificado){
		try {
			Producto updateProducto = productoService.updateProductoById(id, productoModificado);
			return ResponseEntity.ok(updateProducto);
		}catch(IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}catch(Exception e){
			return ResponseEntity.internalServerError().build(); //500
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
		try {
			productoService.deleteProductoById(id);
			return ResponseEntity.noContent().build();
		}catch(IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}catch(Exception e){
			return ResponseEntity.internalServerError().build();
		}
	}

}