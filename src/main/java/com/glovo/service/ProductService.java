package com.glovo.service;

import com.glovo.converter.ProductConverter;
import com.glovo.model.ProductDTO;
import com.glovo.repository.ProductRepository;
import com.glovo.utils.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductDTO> get(Integer id) {
        return Optional.of(ProductConverter.productToProductDTO(productRepository.findById(id).orElseThrow()));
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(product -> products.add(ProductConverter.productToProductDTO(product)));
        return products;
    }

    public void save(ProductDTO productDTO) {
        if (Util.checkNull(productDTO, productDTO.getName(), productDTO.getCost()))
            throw new IllegalArgumentException("Can't save null product");

        productRepository.save(ProductConverter.productDTOToProduct(productDTO));
    }

    public void update(ProductDTO productDTO) {
        if (Util.checkNull(productDTO, productDTO.getId()))
            throw new IllegalArgumentException("Can't update null product");

        save(productDTO);
    }

    public void delete(Integer id) {
        if (Util.checkNull(id))
            throw new IllegalArgumentException("Can't delete null product");

        productRepository.deleteById(id);
    }


}
