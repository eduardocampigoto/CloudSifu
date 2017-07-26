$(document).ready(function(){
	$('.form-control').focus().css('background-color','#FFFFFF');
	buscarTurmas();

	$(document).on('click', '#diaSemana li a', function () {
        var selecao = $(this).text();
        $('#btDiaSemana').html(selecao + '<span class="caret"</span>');
    });
});

function validaFormTurma(){
var valida = true;
		
	if($('#nome').val() == "" ||  $('#nome').val() == null){
	 	$('#nome').css('background-color','#ffb3b3');
	    valida = false;
    };
    
return valida;

}

function getTurma(turma){
	
	return{
		"id": $("#id").val(),
		"nome": $("#nome").val(),
		"horario": $("#horario").val(),
		"diaSemana": $("#btDiaSemana").text(),
		"descricao": $("#descricao").val()
	}
	
}


function setTurma(turma){
	$('#btAdicionarTurma').remove();
	$('#btEditarTurma').css('display','block');
	//$('#btExcluirTurma').css('display','block');
	$('#btCancelar').css('display','block');
	$("#id").val(turma.id);
	$("#horario").val(turma.horario);
	$("#btDiaSemana").html(turma.diaSemana);
	$("#nome").val(turma.nome);
	$("#descricao").val(turma.descricao);
	
}


