package com.andresgaleano.pruebatecnicaorden.domain;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class OrderProductos {
	private String nombreProducto;
	private Integer cantidad;

}
