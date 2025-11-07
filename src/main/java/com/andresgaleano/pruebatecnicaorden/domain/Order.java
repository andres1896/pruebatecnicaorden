package com.andresgaleano.pruebatecnicaorden.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Order {
    private Long idOrder;
    private String nombreCliente;
    private LocalDateTime fechaCreacion;
    private List<OrderProductos> productos;

}
