package com.coderhouse.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@Schema(description = "Modelo de Categoria", title = "Modelo de Categoria")
@Entity
@Table(name = "Categorias")
public class Categoria {

    @Schema(description = "ID de la Categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre de la Categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "HIGIENE PERSONAL")
    @Column(unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();


}