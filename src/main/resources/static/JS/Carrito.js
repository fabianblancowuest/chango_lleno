let agregarProd = document.getElementsByClassName("agregar")
let sacarProd = document.getElementsByClassName("sacar")
let unidades = document.getElementsByClassName("unidades")
let precio = document.getElementsByClassName("precio")
let subtotal = document.getElementsByClassName("subtotal")
/*let totalid = document.getElementById("totalid")
let subtotalid = document.getElementById("subtotalid")*/
let formun = document.getElementById("forms")
let x = 0
let l = 0
let m = 0
for (let contador = 0; contador < unidades.length; contador++) {
        let aux1 = agregarProd[Number(contador)]
            aux1.addEventListener("click", () => {
            unidades[Number(contador)].textContent = (Number(unidades[Number(contador)].textContent)+ 1)
            subtotal[Number(contador)].textContent = ((Number(precio[Number(contador)].textContent)) * (Number(unidades[Number(contador)].textContent)))
            /*x =  Number(subtotal[contador].textContent)
            l +=  x
            subtotalid.textContent = l
            totalid.textContent = l*/
        }

        )
    
        let aux2 = sacarProd[Number(contador)]
        aux2.addEventListener("click" , () => {
            if (unidades[Number(contador)].textContent > 1) {
             unidades[Number(contador)].textContent = (Number(unidades[Number(contador)].textContent) - 1)
               subtotal[Number(contador)].textContent = ((Number(precio[Number(contador)].textContent)) * (Number(unidades[Number(contador)].textContent)))
               /*x =  Number(subtotal[contador].textContent)
               l -=  x
               subtotalid.textContent = l
               totalid.textContent = l*/
            }
            }
        )
        formun.innerHTML += `<input type="hidden" ModelAttribute name="unidades" value="${Number(unidades[contador])}">`
}




