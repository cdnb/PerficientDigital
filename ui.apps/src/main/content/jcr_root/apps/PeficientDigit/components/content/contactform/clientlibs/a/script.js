$(document).ready(function() {

   $('body').hide().fadeIn(5000);

   $('#submit').click(function() {
        var failure = function(err) {
            alert("Unable to retrive data "+err);
        };

        //Get the user-defined values that represent claim data to persist in the Adobe CQ JCR
        var firstName= $('#firstName').val() ;
        var lastName= $('#lastName').val() ;
        var workEmail= $('#workEmail').val() ;
        var phone= $('#phone').val() ;
        var company= $('#company').val() ;
        var country= $('#country').val() ;
        var canHelp= $('#canHelp').val() ;
        var subscribe= $("input[name='subscribe']:checked").val();
       //var patt = /^\([0-9]{3}\)[0-9]{3}-[0-9]{4}$/;
		var message;
        var ss =document.getElementsByTagName("textarea");
        for(var i = 0;i<ss.length;i++) 
        { 
            if (ss[i].value==='' ){
                alert('Must complete the form')
                return
            }else{
                message = message+ss[i].name+':'+ss[i].value+'\n'
            }
        }

        if(
           subscribe === ''
        ){
            alert('Must complete the form')
            return
        }
       //if(!patt.test(phone)
       // ){
       //     alert('phoneNumber must use (000)000-0000 format')
       //     return
       // }


        //Use JQuery AJAX request to post data to a Sling Servlet
        $.ajax({
             type: 'POST',
             url:'/bin/emailServlet',
             data:'firstName='+ firstName+'&lastName='+ lastName+'&workEmail='+ workEmail+'&phone='+ phone+'&company='+ company+'&canHelp='+ canHelp+'&country='+ country+'&subscribe='+ subscribe+'&message='+ message,
             complete: function(xhr) {
                if( xhr.status === 200 ) {
                    alert("Email Sent Successfully")
                } else {
                    alert("Email Sent failed")
                }
             }
         });
     });

});