package com.glovo.service;

import com.glovo.converter.OrderConverter;
import com.glovo.converter.ProductConverter;
import com.glovo.entity.Order;
import com.glovo.model.OrderDTO;
import com.glovo.model.ProductDTO;
import com.glovo.repository.OrderRepository;
import com.glovo.utils.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Optional<OrderDTO> get(Integer id) {
        return orderRepository.findById(id)
                .map(this::getOrderDTOWithProducts);
    }

    private OrderDTO getOrderDTOWithProducts(Order order) {
        OrderDTO orderDTO = OrderConverter.orderToOrderDTO(order);
        orderDTO.setProductDTOS(order.getProducts()
                .stream()
                .map(productRef -> productService.get(productRef.getProductId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
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
        if (Util.checkNull(orderDTO, orderDTO.getDate(), orderDTO.getCost()))
            throw new IllegalArgumentException("Can't save null order");

        Order order = OrderConverter.orderDTOToOrder(orderDTO);
        for (ProductDTO productDTO : orderDTO.getProductDTOS()) {
            if (productDTO.getId() != null) {
                order.addProduct(ProductConverter.productDTOToProduct(productDTO));
            }
        }
        orderRepository.save(order);
    }

    public void update(OrderDTO orderDTO) {
        if (Util.checkNull(orderDTO, orderDTO.getId()))
            throw new IllegalArgumentException("Can't update null order");

        save(orderDTO);
    }

    public void delete(Integer id) {
        if (Util.checkNull(id))
            throw new IllegalArgumentException("Can't delete null order id");

        orderRepository.deleteById(id);
    }

}
