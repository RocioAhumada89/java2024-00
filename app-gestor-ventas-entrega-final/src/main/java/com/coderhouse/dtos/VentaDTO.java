package com.coderhouse.dtos;

import com.coderhouse.models.DetalleVenta;
import com.coderhouse.models.Venta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Schema(description = "Modelo de Venta DTO", title = "Ventas DTO")
public class VentaDTO {

    @Schema(description = "Venta ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "Datos del Cliente")
    private ClienteDTO cliente;

    @Schema(description = "Listado de Detalles de la Venta")
    private List<DetalleVentaDTO> detalles;

    @Schema(description = "Precio Total de la Venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "20500")
    private Double total;

    @Schema(description = "Fecha de Venta", example = "2025/01/20")
    private LocalDateTime fecha;

    public VentaDTO(Venta venta) {
        this.id = venta.getId();
        this.cliente = new ClienteDTO(venta.getCliente());
        this.detalles = new ArrayList<>();

        for (DetalleVenta detalle : venta.getDetalles()) {
            this.detalles.add(new DetalleVentaDTO(detalle));
        }

        this.total = venta.getTotal();
        this.fecha = venta.getFecha();
    }


}
