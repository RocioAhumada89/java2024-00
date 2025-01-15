package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persistirCliente(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Transactional
	public void persistirProducto(Producto producto) {
		entityManager.persist(producto);
	}
}