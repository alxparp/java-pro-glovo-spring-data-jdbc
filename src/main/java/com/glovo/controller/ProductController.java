package com.glovo.controller;

import com.glovo.model.ProductDTO;
import com.glovo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Integer id) {
        return productService.get(id).orElseThrow();
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public void save(@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
    }

    @PutMapping
    public void update(@RequestBody ProductDTO productDTO) {
        productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

}
