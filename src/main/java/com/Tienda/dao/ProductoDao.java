package com.Tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Tienda.domain.Producto;

    public interface ProductoDao extends JpaRepository<Producto, Long>{
    
}
