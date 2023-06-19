package com.glovo.service;

import com.glovo.converter.OrderConverter;
import com.glovo.converter.ProductConverter;
import com.glovo.entity.Order;
import com.glovo.entity.Product;
import com.glovo.model.OrderDTO;
import com.glovo.repository.OrderRepository;
import com.glovo.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    private OrderService orderService;
    private Order order;
    private Integer orderId;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productRepository);
        orderId = 1;
        products = List.of(
                Product.builder().productId(1).name("Burger").cost(30.00).build(),
                Product.builder().productId(2).name("Big Mac").cost(90.00).build()
        );
        order = Order.builder().orderId(orderId).date(LocalDate.now()).cost(120.00).products(new ArrayList<>()).build();
        order.setProducts(products);
    }

    @Test
    public void get() {
        // given
        OrderDTO orderDTOExpected = orderToOrderDTO(order);
        when(orderRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(order));
        setMockForEachProduct(products);

        // when
        OrderDTO orderDTOActual = orderService.get(orderId);

        // then
        Assertions.assertEquals(orderDTOExpected, orderDTOActual);
        assertThrows(NoSuchElementException.class, () -> {
            orderService.get(null);
        });
    }

    private OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTOExpected = OrderConverter.orderToOrderDTO(order);
        orderDTOExpected.setProductDTOS(products.stream()
                .map(ProductConverter::productToProductDTO)
                .toList());
        return orderDTOExpected;
    }

    private void setMockForEachProduct(List<Product> products) {
        for (Product product : products)
            when(productRepository.findById(Mockito.eq(product.getProductId())))
                    .thenReturn(Optional.of(product));
    }

    @Test
    public void getAll() {
        // given
        List<Order> orders = List.of(order);
        List<OrderDTO> orderDTOSExpected = orders.stream()
                .map(this::orderToOrderDTO)
                .toList();
        when(orderRepository.findAll()).thenReturn(orders);
        for (int i = 0; i < orders.size(); i++) {
            setMockForEachProduct(products);
        }

        // when
        List<OrderDTO> orderDTOSActual = orderService.getAll();

        // then
        Assertions.assertEquals(orderDTOSExpected, orderDTOSActual);
    }

    @Test
    public void save() {
        // given
        setMockForEachProduct(products);

        // when
        orderService.save(orderToOrderDTO(order));

        // then
        Order actualOrder = getCapturedOrder();
        Assertions.assertEquals(order, actualOrder);
        Assertions.assertEquals(order.getCost(), actualOrder.getCost());
    }

    private Order getCapturedOrder() {
        ArgumentCaptor<Order> orderArgumentCaptor =
                ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());

        return orderArgumentCaptor.getValue();
    }

    @Test
    public void update() {
        // given
        when(orderRepository.save(order)).thenReturn(order);
        products.get(0).setCost(100.00);
        order.setCost(190.00);
        setMockForEachProduct(products);

        // when
        orderService.update(orderToOrderDTO(order));

        // then
        Assertions.assertEquals(order, getCapturedOrder());
    }

    @Test
    public void delete() {
        // given - precondition or setup
        doNothing().when(orderRepository).deleteById(orderId);

        // when -  action or the behaviour that we are going test
        orderService.delete(orderId);

        // then - verify the output
        verify(orderRepository, times(1)).deleteById(orderId);
    }

}
