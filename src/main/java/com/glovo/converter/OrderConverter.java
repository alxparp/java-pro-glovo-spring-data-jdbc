package com.glovo.converter;

import com.glovo.entity.Order;
import com.glovo.model.OrderDTO;

import java.util.HashSet;

public class OrderConverter {

    public static OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getOrderId())
                .date(order.getDate())
                .cost(order.getCost())
                .build();
    }

    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getId())
                .date(orderDTO.getDate())
                .cost(orderDTO.getCost())
                .products(new HashSet<>())
                .build();
    }

}
