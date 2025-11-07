package com.andresgaleano.pruebatecnicaorden.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoreoController {
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/pruebatecnicaorden/monitoreo")
	public Map<String, Object> verificarConexionBD() {
		Map<String, Object> response = new HashMap<>();

		try {
			entityManager.createNativeQuery("SELECT 1").getSingleResult();
			response.put("estado", "OK");
			response.put("mensaje", "Conexión a base de datos  exitosa");
		} catch (Exception e) {
			response.put("estado", "ERROR");
			response.put("mensaje", "Error al conectar con la base de datos: " + e.getMessage());
		}

		return response;
	}
}
