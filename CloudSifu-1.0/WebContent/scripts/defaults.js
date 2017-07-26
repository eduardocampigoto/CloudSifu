$('document').ready(function(){
	$('#form-control').css("background-color","#FFFFFF");
	
	$('.datepicker-br').datepicker({
        format: "dd/mm/yyyy",
        todayBtn: "linked",
        clearBtn: true,
        language: "pt-BR"
    });
	
	$('.data-br').mask('00/00/0000');
	$('.hora-minuto').mask('00:00');
	$('.cep-semform').mask('99999999');
	$('.telefone').mask('(99) 99999999');
	$('.celular').mask('(99) 999999999');
	$('.numero').mask('9999999999');
	$('.rg').mask('99999999');
	$('.cpf').mask('99999999999');


	
});


function logout(){
	$.ajax({
		method:"GET",
		url:"../Logout",
		success: function(){
			window.location.pathname = "CloudSifu-1.0/"; 
		},error: function(){
			
		}
});
}