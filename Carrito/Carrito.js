let botonSuma = document.getElementById("sumarBoton");
let botonMenos = document.getElementById("restarBoton");
let cantidadesProducto = document.getElementById("unidadesProducto");
let subtotalProducto = document.getElementById("subtotalProducto")
let precioProducto = document.getElementById("precioProducto")

subtotalProducto.textContent=Number(cantidadesProducto.textContent)* Number(precioProducto.textContent)
console.log(Number(cantidadesProducto.textContent)* Number(precioProducto.textContent))
console.log(cantidadesProducto.textContent)
console.log(precioProducto.textContent)
subtotalProducto.textContent = cantidadesProducto.textContent
botonSuma.addEventListener("click", () => {
    cantidadesProducto.textContent = (Number(cantidadesProducto.textContent)+ 1)

    subtotalProducto.textContent=Number(cantidadesProducto.textContent)* Number(precioProducto.textContent)
})

botonMenos.addEventListener("click" , () => {
    if (cantidadesProducto.textContent > 1) {
        cantidadesProducto.textContent = (Number(cantidadesProducto.textContent) - 1)
    }
})

