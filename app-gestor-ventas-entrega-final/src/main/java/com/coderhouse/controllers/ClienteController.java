package com.coderhouse.controllers;

import java.util.List;

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

import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Gestion de Clientes", description = "Endpoints para gestionar Clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;


	@Operation(summary = "Obtener Lista de Clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Clientes obtenida Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a los Clientes",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping("/all-clientes")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		try {
			List<Cliente> clientes = clienteService.getAllClientes();
			return ResponseEntity.status(HttpStatus.OK).body(clientes);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Obtener un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente obtenido Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Cliente",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		try {
			Cliente cliente = clienteService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Crear un Cliente nuevo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente creado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "400", description = "Error al intentar crear el Cliente",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PostMapping("/agregar-cliente-nuevo")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clienteService.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Editar un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente editado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "400", description = "Error al intentar editar el Cliente",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long id, @RequestBody Cliente clienteModificado){
		try {
			Cliente updateCliente = clienteService.updateClienteById(id, clienteModificado);
			return ResponseEntity.status(HttpStatus.OK).body(updateCliente);
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Operation(summary = "Elimiar un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado Exitosamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el Cliente. El Cliente no existe",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
		try {
			clienteService.deleteClienteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
}