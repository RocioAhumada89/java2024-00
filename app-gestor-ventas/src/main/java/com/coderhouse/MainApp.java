package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			
			Producto producto1 = new Producto("Shampoo Dove", "Producto de Higiene", 5000d);
			Producto producto2 = new Producto("Servilleta Carrefour", "Producto de Cocina", 1550.75d);
			Producto producto3 = new Producto("Repelente OFF", "Producto General", 525d);
			Producto producto4 = new Producto("Pasta Dental", "Producto de Higiene", 2300d);

			Cliente cliente1 = new Cliente("Rocío", "Ahumada", "roahumada@gmail.com", "2944864529", "Los Pinos 5063, Córdoba", 22333444);
			Cliente cliente2 = new Cliente("Martín", "Colaso", "martin_colaso@gmail.com", "351738200", "Bv. Chacabuco 611, Córdoba", 34845421);
			Cliente cliente3 = new Cliente("Santiago", "Pons", "spons@yahoo.com.ar", "351129400", "Av. Colon 120, Córdoba", 32949438);
			Cliente cliente4 = new Cliente("Macarena", "Carrion", "mcarrion@yahoo.com.ar", "341672377", "Mariano Benitez 1372, Córdoba", 40031222);

			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			dao.persistirCliente(cliente4);

			dao.persistirProducto(producto1);
			dao.persistirProducto(producto2);
			dao.persistirProducto(producto3);
			dao.persistirProducto(producto4);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

}
