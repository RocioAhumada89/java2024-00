package com.coderhouse.controllers;

import java.util.List;

import com.coderhouse.dtos.AsignacionCategoriaProductoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
@Tag(name = "Gestion de Productos", description = "Endpoints para gestionar Productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;


	@Operation(summary = "Obtener Lista de Productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Productos obtenida Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a los Productos",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		try {
			List<Producto> productos = productoService.getAllProductos();
			return ResponseEntity.status(HttpStatus.OK).body(productos);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Obtener un Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto obtenido Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Producto",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
		try {
			Producto producto = productoService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(producto);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Obtener Stock de un Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Stock de Producto obtenido Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Stock del Producto",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping("/validar-stock/{id}")
    public ResponseEntity<String> validarStock(@PathVariable Long id) {
		try {
			String infoStock = productoService.validarStock(id);
			return ResponseEntity.status(HttpStatus.OK).body(infoStock);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
    }


	@Operation(summary = "Creacion de un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Producto creado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto productoCreado = productoService.saveProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Edicion o Modificacion de un Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto editado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Producto",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProductoById(@PathVariable Long id, @RequestBody Producto productoModificado){
		try {
			Producto updateProducto = productoService.updateProductoById(id, productoModificado);
			return ResponseEntity.status(HttpStatus.OK).body(updateProducto);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Eliminacion de un Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Producto",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
		try {
			productoService.deleteProductoById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Asignacion de Categoria a un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria asignada Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Producto o Categoria",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PostMapping("/asignar-categoria")
	public ResponseEntity<Producto> asignarCategoriaAProducto(@RequestBody AsignacionCategoriaProductoDTO dto){
		try {
			Producto productoActualizado = productoService.asignarCategoriaAProducto(dto.getProductoId(), dto.getCategoriaId());
			return ResponseEntity.status(HttpStatus.OK).body(productoActualizado);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


}