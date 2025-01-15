package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Ventas")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> detalles;
	
	private Double total;
	
	@Column(columnDefinition = "TIMESTAMP(0)", nullable = false, updatable = false)
    @CreationTimestamp
	private LocalDateTime fecha;
	
	
	
	public Venta() {
		super();
	}

	
	public Venta(Cliente cliente, List<DetalleVenta> detalles) {
		super();
		this.cliente = cliente;
		this.detalles = detalles;
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Cliente getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public List<DetalleVenta> getDetalles() {
		return detalles;
	}

	
	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

	
	public LocalDateTime getFecha() {
		return fecha;
	}

	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	

	@Override
	public String toString() {
		return "Venta [id=" + id + ", cliente=" + cliente + ", detalles=" + detalles + ", total=" + total + ", fecha=" + fecha + "]";
	}
	
	
	
}
