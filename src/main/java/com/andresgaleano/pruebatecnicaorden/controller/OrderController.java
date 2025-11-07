package com.andresgaleano.pruebatecnicaorden.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresgaleano.pruebatecnicaorden.domain.Order;
import com.andresgaleano.pruebatecnicaorden.domain.OrderRequest;
import com.andresgaleano.pruebatecnicaorden.service.OrderService;

@RestController
@RequestMapping("/pruebatecnicaorden/orders")
public class OrderController {

	 @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.crearOrder(request));
    }
    
    @GetMapping
    public ResponseEntity<List<Order>> listarOrders() {
        return ResponseEntity.ok(orderService.listarOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}