window.addEventListener('load', function (){
    new Glider(document.querySelector('.carrusel__lista'),{
        slidesToShow: 4,
        slidesToScroll: 4,
        arrows: {
            prev: '.carousel__anterior',
            next: '.carousel__siguiente'
        }
    });
});

window.addEventListener('load', function (){
    new Glider(document.querySelector('.carrusel__lista2'),{
        slidesToShow: 4,
        slidesToScroll: 4,
        arrows: {
            prev: '.carousel__anterior2',
            next: '.carousel__siguiente2'
        }
    });
});

window.addEventListener('load', function (){
    new Glider(document.querySelector('.carrusel__lista3'),{
        slidesToShow: 4,
        slidesToScroll: 4,
        arrows: {
            prev: '.carousel__anterior3',
            next: '.carousel__siguiente3'
        }
    });
});