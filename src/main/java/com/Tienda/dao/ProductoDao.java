package com.Tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    // Consulta para obtener productos ordenados por existencias

    @Query("SELECT p FROM Producto p WHERE p.activo = true ORDER BY p.existencias DESC")
    List<Producto> listarProductosOrdenadosPorExistencias();
}
