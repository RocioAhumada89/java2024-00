package com.coderhouse.repositories;

import com.coderhouse.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRepository extends JpaRepository<DetalleVenta, Long> {
}
