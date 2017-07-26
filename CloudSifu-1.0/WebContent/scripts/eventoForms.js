$('document').ready(function(){
	
	$('.form-control').focus(function(){
		$(this).css('background-color','#FFFFFF');
	});
	
});


function validaFormEvento(){
	var valida = true;
	
	              if($('#titulo').val() == "" ||  $('#titulo').val() == null){
	            		$('#titulo').css('background-color','#ffb3b3');
	            	  valida = false;
	              };
	            	   
	             if($('#data').val() == "" ||  $('#data').val() == null){
	            	 $('#data').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#hora').val() == "" ||  $('#hora').val() == null){
	            	 $('#hora').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#descricao').val() == "" ||  $('#descricao').val() == null){
	            	 $('#descricao').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#cep').val() == "" ||  $('#cep').val() == null ||  $('#cep').val().length !=8){
	            	 $('#cep').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	                         
	             if($('#nlogradouro').val() == "" ||  $('#nlogradouro').val() == null){
	            	 $('#nlogradouro').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#numero').val() == "" ||  $('#numero').val() == null && $('#numero').value().regExp('[^0-9]')){
	            	 $('#numero').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#bairro').val() == "" ||  $('#bairro').val() == null){
	            	 $('#bairro').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             if($('#cidade').val() == "" ||  $('#cidade').val() == null){
	            	 $('#cidade').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	             
	              
	             if($('#nUF').val() == "" || $('#nUF').val() == null){
	            	 $('#nUF').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             return valida;
	              
}