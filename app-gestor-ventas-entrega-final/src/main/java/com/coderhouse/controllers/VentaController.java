package com.coderhouse.controllers;

import java.util.List;

import com.coderhouse.dtos.EliminacionDetalleVentaDTO;
import com.coderhouse.dtos.VentaDTO;
import com.coderhouse.models.DetalleVenta;
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
import org.springframework.web.bind.annotation.*;

import com.coderhouse.models.Venta;
import com.coderhouse.services.VentaService;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Gestion de Ventas", description = "Endpoints para gestionar Ventas")
public class VentaController {
	
	@Autowired
	private VentaService ventaService;


	@Operation(summary = "Obtener Lista de Ventas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Ventas obtenida Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a las Ventas",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping
	public ResponseEntity<List<VentaDTO>> getAllVentas(){
		try {
			List<VentaDTO> ventasDTO = ventaService.getAllVentas();
			return ResponseEntity.status(HttpStatus.OK).body(ventasDTO);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Obtener una Venta por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta obtenida Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener la Venta",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping("/{id}")
	public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(ventaService.findById(id));
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@Operation(summary = "Crear una Venta nueva")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Venta creada Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
					}),
			@ApiResponse(responseCode = "422", description = "Error al intentar crear la Venta",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PostMapping
	public ResponseEntity<VentaDTO> createVenta(@RequestBody Venta venta) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.saveVenta(venta));
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}catch(RestClientException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Editar Detalle de una Venta por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Detalle de Venta editado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
					}),
			@ApiResponse(responseCode = "400", description = "Error al intentar editar la Venta",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PatchMapping("/{id}")
	public ResponseEntity<VentaDTO> updateVentaById(@PathVariable Long id, @RequestBody List<DetalleVenta> detallesActualizados) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new VentaDTO(ventaService.updateDetalleVentaById(id, detallesActualizados)));
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Elimiar Detalle de una Venta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Detalle de Venta eliminado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener la Venta",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@DeleteMapping("/eliminar-detalle")
	public ResponseEntity<Venta> deleteVentaById(@RequestBody EliminacionDetalleVentaDTO dto) {
		try {
			ventaService.deleteDetalleVentaById(dto.getVentaId(), dto.getDetalleIds());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



}
