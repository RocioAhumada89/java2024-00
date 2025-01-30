package com.coderhouse.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Modelo de Eliminación de Detalle DTO", title = "Eliminaciones Detalles DTO")
public class EliminacionDetalleVentaDTO {

    @Schema(description = "Venta ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long ventaId;

    @Schema(description = "Listado de ID's de Detalles", requiredMode = Schema.RequiredMode.REQUIRED, example = "['1', '2', '3']")
    private List<Long> detalleIds;

}
