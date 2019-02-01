// Slick Carousel //



$('.carousel-multiple').slick({
  dots: true,
  infinite: true,
  slidesToShow: 3,
  slidesToScroll: 3
});


	
$('.carousel-single').slick({
  dots: true,
  infinite: true,
  speed: 300,
  slidesToShow: 1,
  slidesToScroll: 1
});


//Controls loading of more Cards in Cards component as well as it's responsiveness
respCards = function() {
    var width = $(window).width()
    if (width >= 992) {
        $(".cards-section:not(.expanded) .card-group .card").hide();
        $(".cards-section:not(.expanded) .card-group .card:nth-of-type(-n+4)").show();
    } 

    else {
        $(".cards-section:not(.expanded) .card-group .card").hide();
        $(".cards-section:not(.expanded) .card-group .card:nth-of-type(-n+3)").show();
    }
    $(".cardsbutton").click(function(e){
        var cardsContainer = $(this).closest(".cards-section").attr("id");
        e.preventDefault();
        $("#" + cardsContainer + " .card-group .card:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + cardsContainer + " .button-wrapper").fadeOut();
    });
    
    $(".cards-section:not(.static) .button-wrapper").show();
}

$(document).ready(respCards);
$(window).resize(respCards);

//sets equal heights to all Cards
equalheight = function(cardContainer){
  
var currentTallest = 0,
     currentRowStart = 0,
     rowDivs = new Array(),
     $el,
     topPosition = 0;
 $(cardContainer).each(function() {

   $el = $(this);
   $($el).height('auto')
   topPostion = $el.position().top;

   if (currentRowStart != topPostion) {
     for (currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
       rowDivs[currentDiv].height(currentTallest);
     }
     rowDivs.length = 0; // empty the array
     currentRowStart = topPostion;
     currentTallest = $el.height();
     rowDivs.push($el);
   } else {
     rowDivs.push($el);
     currentTallest = (currentTallest < $el.height()) ? ($el.height()) : (currentTallest);
  }
   for (currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
     rowDivs[currentDiv].height(currentTallest);
   }
 });
}

$(window).on('load resize',function() {
//  var width = $(window).width()
//  if (width >= 768) {
    equalheight('.card-group .card:not(.expanded)');
    equalheight('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-details');
    equalheight('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-host');
//  }
//  else {
//    $('.card-group .card:not(.expanded)').height(auto);
//    $('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-details').height(auto);
//    $('.card-group .card:not(.expanded) .card-content .event-table .event-row .event-host').height(auto);
//  }
});

//Adds load more functionality to Tiles component
loadTiles = function() {
    
        $(".tiles-row.hidden").hide();
        $(".tiles-row").slice(0, 1).show();
   
    
    $(".tiles-load-button").click(function(e){
        var tilesContainer = $(this).closest(".tiles-wrapper").attr("id");
        e.preventDefault();
        $("#" + tilesContainer + " .tiles-row:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + tilesContainer + " .button-wrapper").fadeOut();
    });
    $(".tiles-section .button-wrapper").show();
}

$(document).ready(loadTiles);
$(window).resize(loadTiles);

//Adds load more functionality to cta-squares component
loadSquares = function() {
    
    var width = $(window).width()
    if (width < 992) {
        $(".square-wrapper .square-container").hide();
        $(".square-wrapper .square-container").slice(0, 4).show();
        $(".cta-squares .button-wrapper").show();
    } 

    else {
        $(".square-wrapper .square-container").show();
        $(".cta-squares .button-wrapper").hide();
    }
    
    $(".squaresbutton").click(function(e){
        var squaresContainer = $(this).closest(".cta-squares").attr("id");
        e.preventDefault();
        $("#" + squaresContainer + " .square-wrapper .square-container:hidden").slideDown( "slow", function() {
            // Animation complete.
        });
        $("#" + squaresContainer + " .button-wrapper").fadeOut();
    });
}

$(document).ready(loadSquares);
$(window).resize(loadSquares);

//*******
$('.btn-hero-menu').click(function(){
  $('.hero-slide-menu').toggleClass('on');
  $('.btn-hero-menu').toggleClass('on');
});


$('.dropdown-toggle').dropdown()

$( ".search-icon" ).click(function() {
  $(".search-icon").toggleClass("shown");
});
$( ".mobile-open" ).click(function() {
  $(".mobile-open").parent().toggleClass("open");
});

//Stops modal-embedded YouTube video on Modal Close
$(document).ready(function(){
    $('.modal').each(function(){
            var src = $(this).find('iframe').attr('src');

        $(this).on('click', function(){

            $(this).find('iframe').attr('src', '');
            $(this).find('iframe').attr('src', src);

        });
    });
});

