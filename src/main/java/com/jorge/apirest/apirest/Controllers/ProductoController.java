package com.jorge.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.apirest.apirest.Repositories.ProductoRepository;
import com.jorge.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obteneProductoPorId(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PostMapping
    // Deve resivir todas las caracteristicas de Producto
    public Producto createProducto(@RequestBody Producto producto) {
        return productRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updatedProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productRepository.save(producto);

    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        productRepository.delete(producto);
        return "El producto con el ID: " + id + " se elimino correctamente";

    }

}
