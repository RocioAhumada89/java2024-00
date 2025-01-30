package com.coderhouse.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Data
@Schema(description = "Modelo de Producto", title = "Modelo de Producto")
@Entity
@Table(name = "Productos")
public class Producto {

    @Schema(description = "ID del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Pasta Dental")
    private String nombre;

    @Schema(description = "Descripcion del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Producto de Higiene")
    private String descripcion;

    @Schema(description = "Precio del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1300")
    private Double precio;

    @Schema(description = "Stock del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    private int stock;

    @Schema(description = "Categoria del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "HIGIENE PERSONAL")
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;


}