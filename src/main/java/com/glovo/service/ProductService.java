package com.glovo.service;

import com.glovo.converter.ProductConverter;
import com.glovo.model.ProductDTO;
import com.glovo.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final static Logger logger = Logger.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO get(Integer id) {
        return ProductConverter.productToProductDTO(productRepository.findById(id).orElseThrow());
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(product -> products.add(ProductConverter.productToProductDTO(product)));
        return products;
    }

    public void save(ProductDTO productDTO) {
        productRepository.save(ProductConverter.productDTOToProduct(productDTO));
    }

    public void update(ProductDTO productDTO) {
        save(productDTO);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}
