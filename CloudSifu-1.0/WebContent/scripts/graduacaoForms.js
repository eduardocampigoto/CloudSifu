$(document).ready(function(){
	$('.form-control').focus().css('background-color','#FFFFFF');
	buscarGraduacoes();
});

function validaFormGraduacao(){
var valida = true;
		
	if($('#nome').val() == "" ||  $('#nome').val() == null){
	 	$('#nome').css('background-color','#ffb3b3');
	    valida = false;
    };
    
return valida;

}

function getGraduacao(graduacao){
	
	return{
		"id": $("#id").val(),
		"nome": $("#nome").val(),
		"descricao": $("#descricao").val()
	}
	
}


function setGraduacao(graduacao){
	$('#btAdicionarGraduacao').remove();
	$('#btEditarGraduacao').css('display','block');
	$('#btExcluirGraduacao').css('display','block');
	$('#btExcluirGraduacao').attr('onClick','javascript: excluirGraduacao('+graduacao.id+')');
	$('#btCancelar').css('display','block');
	$("#id").val(graduacao.id);
	$("#nome").val(graduacao.nome);
	$("#descricao").val(graduacao.descricao);
	
}


