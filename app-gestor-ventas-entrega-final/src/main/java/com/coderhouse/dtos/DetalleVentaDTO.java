package com.coderhouse.dtos;

import com.coderhouse.models.DetalleVenta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de Detalle de Venta DTO", title = "Detalle DTO")
public class DetalleVentaDTO {

    @Schema(description = "Detalle ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "Datos del Producto")
    private ProductoDTO producto;

    @Schema(description = "Cantidad de Unidades del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer cantidad;

    @Schema(description = "Precio SubTotal por Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "8000")
    private Double subtotal;

    public DetalleVentaDTO(DetalleVenta detalleVenta) {
        this.id = detalleVenta.getId();
        this.producto = new ProductoDTO(detalleVenta.getProducto());
        this.cantidad = detalleVenta.getCantidad();
        this.subtotal = detalleVenta.getSubtotal();
    }

}
