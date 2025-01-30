package com.coderhouse.models;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Schema(description = "Modelo de Cliente", title = "Modelo de Cliente")
@Entity
@Table(name = "Clientes")
public class Cliente {

    @Schema(description = "ID del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Rocío")
    private String nombre;

    @Schema(description = "Apellido del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ahumada")
    private String apellido;

    @Schema(description = "Email del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "roahumada11@gmail.com")
    private String email;

    @Schema(description = "Telefono del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "2944609832")
    private String telefono;

    @Schema(description = "Direccion del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Av. San Juan 1500 - Cordoba")
    private String direccion;

    @Schema(description = "DNI del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "222444888")
    @Column(unique = true, nullable = false)
    private int dni;

    @Schema(description = "Fecha de Alta del Cliente", example = "2025/01/19")
    @Column(columnDefinition = "TIMESTAMP(0)", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


}
