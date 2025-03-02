
package com.Tienda.service.impl;


import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    
    @Autowired
    private CategoriaDao categoriadao;
    
    public List<Categoria> getCategorias(boolean activos){
        var lista = categoriadao.findAll();
        
        if (activos){ // quiere solo las categorias activas
            lista.removeIf(c -> !c.isActivo()); 
        }
        
        return lista;
    }
}
