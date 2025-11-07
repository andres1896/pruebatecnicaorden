package com.andresgaleano.pruebatecnicaorden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andresgaleano.pruebatecnicaorden.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}