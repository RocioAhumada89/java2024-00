package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Schema(description = "Modelo de Detalle Venta", title = "Modelo Detalle Venta")
@Entity
@Table(name = "Detalle_Venta")
public class DetalleVenta {

	@Schema(description = "ID de Detalle", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Datos de la Venta")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Venta venta;

	@Schema(description = "Datos del Producto")
	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;

	@Schema(description = "Cantidad de Unidades del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Integer cantidad;

	@Schema(description = "Precio SubTotal por Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "8000")
    private Double subtotal;


}
