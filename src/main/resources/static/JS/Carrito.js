let agregarProd = document.getElementById("agregar")
let sacarProd = document.getElementById("sacar")
let unidades = document.getElementById("unidades")
let precio = document.getElementById("precio")
let subtotal = document.getElementById("subtotal")

agregarProd.addEventListener("click", () => {
    unidades.textContent = (Number(unidades.textContent)+ 1)
    subtotal.textContent = ((Number(precio.textContent)) * (Number(unidades.textContent)))
})

sacarProd.addEventListener("click" , () => {
    if (unidades.textContent > 1) {
        unidades.textContent = (Number(unidades.textContent) - 1)
        subtotal.textContent = ((Number(precio.textContent)) * (Number(unidades.textContent)))
    }
})