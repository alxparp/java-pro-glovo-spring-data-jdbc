package com.glovo.service;

import com.glovo.converter.OrderConverter;
import com.glovo.converter.ProductConverter;
import com.glovo.entity.Order;
import com.glovo.entity.Product;
import com.glovo.model.OrderDTO;
import com.glovo.repository.OrderRepository;
import com.glovo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO get(Integer id) {
        return orderRepository.findById(id)
                .map(this::getOrderDTOWithProducts)
                .orElseThrow();
    }

    private OrderDTO getOrderDTOWithProducts(Order order) {
        OrderDTO orderDTO = OrderConverter.orderToOrderDTO(order);
        orderDTO.setProductDTOS(order.getProducts()
                .stream()
                .map(product -> productRepository.findById(product.getProductId()))
                .map(product -> ProductConverter.productToProductDTO(product.orElseThrow()))
                .toList());
        return orderDTO;
    }

    public List<OrderDTO> getAll() {
        List<OrderDTO> orderEntities = new ArrayList<>();
        orderRepository.findAll()
                .forEach(order -> orderEntities.add(getOrderDTOWithProducts(order)));
        return orderEntities;
    }

    public void save(OrderDTO orderDTO) {
        Order order = OrderConverter.orderDTOToOrder(orderDTO);

        List<Product> products = orderDTO.getProductDTOS()
                .stream()
                .filter(productDTO -> productDTO.getId() != null)
                .map(productDTO -> productRepository.findById(productDTO.getId()).orElseThrow())
                .toList();

        order.setCost(calculateAndGetCost(products));
        order.setProducts(products);
        orderRepository.save(order);
    }

    private double calculateAndGetCost(List<Product> products) {
        double cost = 0.0;
        for (Product product : products) {
            if (product.getProductId() != null)
                cost += productRepository.findById(product.getProductId())
                        .orElseThrow()
                        .getCost();
        }
        return cost;
    }

    public void update(OrderDTO orderDTO) {
        save(orderDTO);
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

}
