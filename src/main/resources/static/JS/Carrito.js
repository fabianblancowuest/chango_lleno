let agregarProd = document.getElementsByClassName("agregar")
let sacarProd = document.getElementsByClassName("sacar")
let unidades = document.getElementsByClassName("unidades")
let precio = document.getElementsByClassName("precio")
let subtotal = document.getElementsByClassName("subtotal")
let totalid = document.getElementById("totalid")
let subtotalid = document.getElementById("subtotalid")
let formun = document.getElementById("forms")

for (let contador = 0; contador < unidades.length; contador++) {
        let aux1 = agregarProd[Number(contador)]
            aux1.addEventListener("click", () => {
            unidades[Number(contador)].textContent = (Number(unidades[Number(contador)].textContent)+ 1)
            subtotal[Number(contador)].textContent = ((Number(precio[Number(contador)].textContent)) * (Number(unidades[Number(contador)].textContent)))
            }
        )
    
        let aux2 = sacarProd[Number(contador)]
        aux2.addEventListener("click" , () => {
            if (unidades[Number(contador)].textContent > 1) {
             unidades[Number(contador)].textContent = (Number(unidades[Number(contador)].textContent) - 1)
                subtotal[Number(contador)].textContent = ((Number(precio[Number(contador)].textContent)) * (Number(unidades[Number(contador)].textContent)))
            }
            }
        )
        subtotalid.textContent += subtotal[contador] 
        totalid.textContent += subtotal[contador]
        formun.innerHTML += `<input type="hidden" name="unidades" value="${Number(unidades[contador])}">`
}


