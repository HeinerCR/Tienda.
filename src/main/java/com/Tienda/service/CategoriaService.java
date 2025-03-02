package com.Tienda.service;

import java.util.List;
import com.Tienda.domain.Categoria;

public interface CategoriaService {

    public List<Categoria> getCategorias(boolean activos);
    
}
