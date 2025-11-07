//const apiBase = "http://localhost:8080/pruebatecnicaorden/orders";
const apiBase = "http://prueba-Publi-6DhubtwLpX4H-22863238.sa-east-1.elb.amazonaws.com/pruebatecnicaorden/orders";



document.getElementById("orderForm").addEventListener("submit", async (e) => {
	e.preventDefault();

	const nombreCliente = document.getElementById("nombreCliente").value;
	const productos = Array.from(document.querySelectorAll(".item-row")).map(row => ({
		nombreProducto: row.querySelector(".nombreProducto").value,
		cantidad: parseInt(row.querySelector(".cantidad").value)
	}));

	const response = await fetch(apiBase, {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ nombreCliente: nombreCliente, productos })
	});

	if (response.ok) {
		const data = await response.json();
		alert(`Orden creada correctamente`);
		loadOrders();
		document.getElementById("orderForm").reset();
	} else {
		alert("Error al crear la orden");
	}
});


document.getElementById("addItem").addEventListener("click", () => {
	const container = document.getElementById("itemsContainer");
	const newItem = document.createElement("div");
	newItem.classList.add("row", "g-2", "mb-2", "item-row");
	newItem.innerHTML = `
    <div class="col-md-6">
      <input type="text" class="form-control nombreProducto" placeholder="Nombre del producto" required>
    </div>
    <div class="col-md-3">
      <input type="number" class="form-control cantidad" placeholder="Cantidad" required min="1">
    </div>
    <div class="col-md-3">
      <button type="button" class="btn btn-danger w-100 removeItem">Eliminar</button>
    </div>
  `;
	container.appendChild(newItem);
});


document.addEventListener("click", (e) => {
	if (e.target.classList.contains("removeItem")) {
		e.target.closest(".item-row").remove();
	}
})

function loadOrders() {
	axios.get(apiBase)
		.then(response => {
			const tableBody = document.querySelector('#ordersTable tbody');
			tableBody.innerHTML = '';

			response.data.forEach(order => {
				const row = `
          <tr>
            <td>${order.idOrder}</td>
            <td>${order.nombreCliente}</td>
            <td>${new Date(order.fechaCreacion).toLocaleString()}</td>
          </tr>`;
				tableBody.insertAdjacentHTML('beforeend', row);
			});
		})
		.catch(error => {
			console.error("❌ Error al cargar las órdenes:", error);
		});
}



document.getElementById("btnSearch").addEventListener("click", async () => {
	const id = document.getElementById("orderId").value;
	if (!id) return alert("Ingrese un ID");

	const response = await fetch(`${apiBase}/${id}`);
	if (response.ok) {
		const data = await response.json();

		document.getElementById("orderResult").classList.remove("d-none");
		document.getElementById("resultCliente").textContent = data.nombreCliente;
		document.getElementById("resultFecha").textContent = data.fechaCreacion.split("T")[0];

		const list = document.getElementById("resultProductos");
		list.innerHTML = "";
		data.productos.forEach(p => {
			const li = document.createElement("li");
			li.classList.add("list-group-item");
			li.textContent = `${p.nombreProducto} (x${p.cantidad})`;
			list.appendChild(li);
		});
	} else {
		alert("Orden no encontrada");
	}

});



document.addEventListener('DOMContentLoaded', () => {
	loadOrders();
});


