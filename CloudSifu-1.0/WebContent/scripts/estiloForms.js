$(document).ready(function(){
	$('.form-control').focus().css('background-color','#FFFFFF');
	buscarestilos("todos");
});



function validaFormEstilo(){
var valida = true;
		
	if($('#nome').val() == "" ||  $('#nome').val() == null){
	 	$('#nome').css('background-color','#ffb3b3');
	    valida = false;
    };
    
return valida;

}

function getEstilo(estilo){
	
	return{
		"id": $("#id").val(),
		"nome": $("#nome").val(),
		"descricao": $("#descricao").val()
	}
	
}


function setEstilo(estilo){
	$('#btAdicionarEstilo').remove();
	$('#btSalvarEstilo').css('display','block');
	//$('#btExcluirestilo').css('display','block');
	$('#btCancelar').css('display','block');
	$("#id").val(estilo.id);
	$("#nome").val(estilo.nome);
	$("#descricao").val(estilo.descricao);
	
}




function popularListaEstilo(listaEstilos){
	listaDropDown="";
	lista = listaEstilos;
	var count = 0;
	if(listaEstilos != null && listaEstilos != ""){
		listaEstilos.forEach(function(estilo){
			listaDropDown+= "<tr><td  rows='1'>"+estilo.nome+"</td><td>"+estilo.descricao+"</td><td>"+
			"<a type='button' class='btn btForm glyphicon glyphicon-edit' id='btEditarestilo' style='color: #010101' onclick='javascript: selecionarObj("+count+")'> Editar</a>"+
			"<a type='button' class='btn btForm glyphicon glyphicon-remove' id='btExcluirestilo' style='color: #010101' onclick='javascript: excluirEstilo("+estilo.id+")'> Excluir</a>"+
			"</td></tr>";
			count++;			
		});	
		
		$('#listaEstilos').html(listaDropDown);
	}
	
}


