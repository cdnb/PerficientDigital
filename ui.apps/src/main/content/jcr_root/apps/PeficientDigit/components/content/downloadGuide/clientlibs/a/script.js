$(document).ready(function() {
    $('.text').each( function () {

        		$this = $(this);
        		if ( this.value != '' ) $this.addClass('activated');        
   				 });


   $('#submit').click(function() {
       	window.open('https://www.perficient.com/-/media/files/guide-pdf-links/leveraging-portal-technologies-to-meet-rising-customer-expectations.pdf');

        var ss =document.getElementsByTagName("input");
        for(var i = 0;i<ss.length;i++) 
        { 
            if (ss[i].value==='' ){
                alert('Must complete the form')
                return
            }else{
                message = message+ss[i].name+':'+ss[i].value+'\n'
            }
        }
       		window.open('https://www.perficient.com/-/media/files/guide-pdf-links/leveraging-portal-technologies-to-meet-rising-customer-expectations.pdf');
		
     });


});