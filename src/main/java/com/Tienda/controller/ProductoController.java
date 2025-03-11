package com.Tienda.controller;

import com.Tienda.dao.ProductoDao;
import com.Tienda.domain.Categoria;
import com.Tienda.domain.Producto;
import com.Tienda.service.CategoriaService;
import com.Tienda.service.ProductoService;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        List<Producto> productos = productoDAO.listarProductosOrdenadosPorExistencias();

        List<Categoria> listaCategoriasActivas = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", listaCategoriasActivas);
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        List<Categoria> listaCategoriasActivas = categoriaService.getCategorias(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", listaCategoriasActivas);
        return "/producto/modifica";
    }

    @Autowired
    private ProductoDao productoDAO;

    @GetMapping("/productos")
    public String mostrarProductosOrdenados(Model model) {
        // Obtén los productos ordenados por existencias
        List<Producto> productosOrdenados = productoDAO.listarProductosOrdenadosPorExistencias();

        // Pasa los productos al modelo para la vista
        model.addAttribute("productos", productosOrdenados);

        // Devuelve la vista con la lista de productos
        return "productos";
    }
}
