package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Data
@Schema(description = "Modelo de Venta", title = "Modelo de Venta")
@Entity
@Table(name = "Ventas")
public class Venta {

	@Schema(description = "ID de Venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Datos del Cliente")
	@ManyToOne
	private Cliente cliente;

	@Schema(description = "Listado de Detalles de la Venta")
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> detalles;

	@Schema(description = "Precio Total de la Venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "20500")
	private Double total;

	@Schema(description = "Fecha de Venta", example = "2025/01/20")
	@Column(nullable = false, updatable = false)
	private LocalDateTime fecha;
	

}
