package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Orders;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrderRepository;

class OrderServicesTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServices orderServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrders() {
        // Arrange
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        when(orderRepository.findAll()).thenReturn(ordersList);

        // Act
        List<Orders> result = orderServices.getOrders();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository).findAll();
    }

    @Test
    void testSaveOrder() {
        // Arrange
        Orders order = new Orders();
        when(orderRepository.save(any(Orders.class))).thenReturn(order);

        // Act
        orderServices.saveOrder(order);

        // Assert
        verify(orderRepository).save(order);
    }

    @Test
    void testUpdateOrder() {
        // Arrange
        int orderId = 1;
        Orders order = new Orders();
        when(orderRepository.save(any(Orders.class))).thenReturn(order);

        // Act
        orderServices.updateOrder(orderId, order);

        // Assert
        assertEquals(orderId, order.getoId());
        verify(orderRepository).save(order);
    }

    @Test
    void testDeleteOrder() {
        // Arrange
        int orderId = 1;
        doNothing().when(orderRepository).deleteById(orderId);

        // Act
        orderServices.deleteOrder(orderId);

        // Assert
        verify(orderRepository).deleteById(orderId);
    }

    @Test
    void testGetOrdersForUser() {
        // Arrange
        User user = new User();
        List<Orders> userOrders = new ArrayList<>();
        userOrders.add(new Orders());
        when(orderRepository.findOrdersByUser(user)).thenReturn(userOrders);

        // Act
        List<Orders> result = orderServices.getOrdersForUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository).findOrdersByUser(user);
    }

    @Test
    void testGetOrdersEmptyList() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Orders> result = orderServices.getOrders();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository).findAll();
    }

    @Test
    void testGetOrdersForUserEmptyList() {
        // Arrange
        User user = new User();
        when(orderRepository.findOrdersByUser(user)).thenReturn(new ArrayList<>());

        // Act
        List<Orders> result = orderServices.getOrdersForUser(user);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository).findOrdersByUser(user);
    }
}
