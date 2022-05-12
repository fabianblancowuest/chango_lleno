let checkSucursal= document.getElementById("checkSucursal");
let checkEnvio= document.getElementById("checkEnvio");
let envioText = document.querySelectorAll(".envioText");
let nroEnvioDato = document.querySelectorAll(".nroEnvioDato");
let SignoNroEnvioText = document.querySelectorAll(".SignoNroEnvioText")
let cajaEnviosDetalle = document.getElementsByClassName("cajaEnviosDetalle");
let cajaSucursalRetiro = document.getElementsByClassName("cajaSucursalRetiro");
let nroProductoDato = parseFloat(document.getElementsByClassName("nroProductoDato")[0].innerText);
let envioPrecio = parseFloat(document.getElementById("envioPrecio").innerText);
let nroSubtotalDato = parseFloat(document.getElementsByClassName("nroSubtotalDato")[0].innerText);
let nroTotalDato = document.getElementsByClassName("nroTotalDato")[0];


checkSucursal.onclick = function (){ 
    envioText[0].style.display="none";
    nroEnvioDato[0].style.display="none";
    SignoNroEnvioText[0].style.display="none";
    cajaEnviosDetalle[0].style.background="#EFEFEF";
    cajaSucursalRetiro[0].style.background="#c3e0ff";
  
    nroTotalDato.innerText = nroProductoDato+nroSubtotalDato;
}

checkEnvio.onclick = function (){
    cajaSucursalRetiro[0].style.background="#EFEFEF"
    cajaEnviosDetalle[0].style.background="#c3e0ff"; 
    envioText[0].style.display="block";
    nroEnvioDato[0].style.display="block";
    SignoNroEnvioText[0].style.display="block";
 
    nroTotalDato.innerText = nroProductoDato+envioPrecio+nroSubtotalDato;;
    
}

