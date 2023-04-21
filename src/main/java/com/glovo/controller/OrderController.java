package com.glovo.controller;

import com.glovo.model.OrderDTO;
import com.glovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Integer id) {
        return orderService.get(id).orElseThrow();
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }

    @PutMapping
    public void update(@RequestBody OrderDTO orderDTO) {
        orderService.update(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

}
