package com.coderhouse.dtos;

import com.coderhouse.models.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de Cliente DTO", title = "Cliente DTO")
public class ClienteDTO {

    @Schema(description = "ID del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "Nombre del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Rocío")
    private String nombre;

    @Schema(description = "Apellido del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ahumada")
    private String apellido;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
    }

}
