package com.coderhouse.dtos;


import com.coderhouse.models.Categoria;
import com.coderhouse.models.Producto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de Producto DTO", title = "Producto DTO")
public class ProductoDTO {

    @Schema(description = "Producto ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "Nombre del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Pasta Dental")
    private String nombre;

    @Schema(description = "Descripcion del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Producto de Higiene")
    private String descripcion;

    @Schema(description = "Categoria del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "HIGIENE PERSONAL")
    private Categoria categoria;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.categoria = producto.getCategoria();
    }

}
