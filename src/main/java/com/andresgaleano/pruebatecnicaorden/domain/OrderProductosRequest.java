package com.andresgaleano.pruebatecnicaorden.domain;

import lombok.Data;

@Data
public class OrderProductosRequest {

	private String nombreProducto;
	private Integer cantidad;

}
