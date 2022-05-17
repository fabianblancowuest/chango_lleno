let array = document.getElementsByClassName("divCarruselProductos1") 
let anterior = document.getElementsByClassName("carousel__anterior")
let siguiente = document.getElementsByClassName("carousel__siguiente")
for (let index = 0; index < array.length; index++) {
    anterior[index].classList.add(`carousel__anterior${index}`)
    siguiente[index].classList.add(`carousel__siguiente${index}`)
    window.addEventListener('load', function (){
        new Glider(document.querySelector(`.carrusel__lista${index}`),{
            slidesToShow: 4,
            slidesToScroll: 4,
            arrows: {
                prev: `.carousel__anterior${index}`,
                next: `.carousel__siguiente${index}`
            }
        })
    })  
}
