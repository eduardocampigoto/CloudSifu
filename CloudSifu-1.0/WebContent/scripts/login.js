var sessaoUsuarioGlob = "";
$('document').ready(function(){


});

function login(){
	senha = $("#senhaInput").val();
	$("#senhaId").val(btoa(senha));
	$("#senhaInput").val("");
	$.ajax({
		method: "POST",
		url: "acessar",
		data: $("#loginId").serialize(),
		success: function(cod){
			if(cod != 401){
				window.location.pathname = "CloudSifu-1.0/pages/profile.html";
			}
		},
		error: function(){}
	});
	
	
}
