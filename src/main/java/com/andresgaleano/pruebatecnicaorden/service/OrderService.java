package com.andresgaleano.pruebatecnicaorden.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresgaleano.pruebatecnicaorden.domain.Order;
import com.andresgaleano.pruebatecnicaorden.domain.OrderProductos;
import com.andresgaleano.pruebatecnicaorden.domain.OrderRequest;
import com.andresgaleano.pruebatecnicaorden.entity.OrderEntity;
import com.andresgaleano.pruebatecnicaorden.entity.OrderProductosEntity;
import com.andresgaleano.pruebatecnicaorden.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public Order crearOrder(OrderRequest request) {
        OrderEntity order = OrderEntity.builder()
                .nombreCliente(request.getNombreCliente())
                .fechaCreacion(LocalDateTime.now())
                .build();

        order.setProductos(request.getProductos().stream()
                .map(i -> OrderProductosEntity.builder()
                        .nombreProducto(i.getNombreProducto())
                        .cantidad(i.getCantidad())
                        .order(order)
                        .build())
                .collect(Collectors.toList()));

        OrderEntity saved = orderRepository.save(order);

        return Order.builder()
                .idOrder(saved.getId())
                .nombreCliente(saved.getNombreCliente())
                .fechaCreacion(saved.getFechaCreacion())
                .productos(saved.getProductos().stream()
                        .map(p -> OrderProductos.builder()
                                .nombreProducto(p.getNombreProducto())
                                .cantidad(p.getCantidad())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
	
	

    public List<Order> listarOrders() {
        return orderRepository.findAll().stream()
                .map(o -> Order.builder()
                        .idOrder(o.getId())
                        .nombreCliente(o.getNombreCliente())
                        .fechaCreacion(o.getFechaCreacion())
                        .productos(o.getProductos().stream()
                                .map(p -> OrderProductos.builder()
                                        .nombreProducto(p.getNombreProducto())
                                        .cantidad(p.getCantidad())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(o -> Order.builder()
                        .idOrder(o.getId())
                        .nombreCliente(o.getNombreCliente())
                        .fechaCreacion(o.getFechaCreacion())
                        .productos(o.getProductos().stream()
                                .map(p -> OrderProductos.builder()
                                        .nombreProducto(p.getNombreProducto())
                                        .cantidad(p.getCantidad())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

}
