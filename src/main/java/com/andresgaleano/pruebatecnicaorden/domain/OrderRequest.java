package com.andresgaleano.pruebatecnicaorden.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private String nombreCliente;
    private List<OrderProductosRequest> productos;

}
