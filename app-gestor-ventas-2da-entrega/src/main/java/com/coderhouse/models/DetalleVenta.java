package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Detalle_Venta")
public class DetalleVenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Venta venta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Producto producto;
	
	private Integer cantidad;

    private Double subtotal;
    
    
    public DetalleVenta() {
    	super();
    }


	public DetalleVenta(Venta venta, Producto producto, Integer cantidad, Double subtotal) {
		super();
		this.venta = venta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
    
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}


	@Override
	public String toString() {
		return "DetalleVenta [id=" + id + ", venta=" + venta + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", subtotal=" + subtotal + "]";
	}
	
	
	
}
