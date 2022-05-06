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