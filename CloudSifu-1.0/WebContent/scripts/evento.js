$(document).ready(function(){
});

function adicionarEvento(){
	var titulo = "Adicionar Evento";
	var eventoObj = JSON.stringify(getEvento());
	var validaForm = validaFormEvento();
if (validaForm){	
	$.ajax({
		url: "../rest/evento",
		method: "POST",
		data: eventoObj,
		success:function(msg){
			showModalOK(titulo, "Evento adicionado com sucesso ");
			carregarPagina('../pages/eventoForms.html');
		},
		error: function(msg){
			showModalOK(titulo, "Erro ao adicionar evento");
		}
	});
}else{
	showModalOK(titulo, "Todos os campos devem ser preenchidos corretamente! ");
}
	
}


function buscarEventoTitulo(titulo){
	$.ajax({
			url : "../rest/evento/titulo/" + titulo,
			method : "GET",
			success : function(lista) {
				montarListaEvento(listaEvento);
			},
			error : function(e) {
				console.log(e);
			}
		});
	
	 
}


function montarListaEvento(listaEvento){
	
	if (listaEvento != null && listaEvento != "" && listaEvento != undefined){
		 this.listaEvento = listaEvento;	        		  
		    listaEvento.forEach(function(obj) {
		    	lista+="<li id='" +obj.id +"' class='resultBusca'><a onClick='javascript:selecionaEvento(" +
		                obj.id + ")'> Evento | " + obj.id + " | " + obj.titulo + "</a></li>";
		    });
		    $("#buscaLista").html(lista);
	}
}

function buscarEventoData(data){
	
	$.ajax({
		url: "../rest/evento/data/"+data,
		method: "GET",
		success: function(lista){
			if(lista != null){
				montarListaEvento(lista);
			}
		},error: function(e){
			console.log(e);
		}
	});

}

function editarEvento(){
	var evento = JSON.stringify(getEvento());
	var titulo = "Editar Evento";
	var mensagem = "Prosseguir com a alteração dos dados do evento ?"
var callback = function(){
	$.ajax({
		url: "../rest/evento/",
		method: "PUT",
		data: evento,
		success:function(evento){
			showModalOK(titulo, "Evento editado com sucesso");
			carregarPagina('../pages/eventoForms.html');
			
		},
		error: function(xhr, status, error){
			showModalOK(titulo, "Falha ao editar evento \n"+xhr.responseText);
		}
	});
}
showModalSimNao(titulo, mensagem, callback);
}

function excluirEvento(){
 var id = $('#eventoId').val();
 var titulo = "Excluir evento";
 var mensagem = "Confirma a exclusão do evento ?"
 var callback = function(){
	$.ajax({
		url: "../rest/evento/id/"+id,
		method: "DELETE",
		success:function(){
			showModalOK(titulo, "Evento excluído com sucesso");
			carregarPagina('../pages/eventoForms.html');
		},
		error: function(){
			showModalOK(titulo, "Falha ao excluir evento");
		}
	});
}
showModalSimNao(titulo, mensagem, callback); 
}


function setEvento(evento){
	$('#btAdicionarEvento').remove();
	$('#btEditarEvento').css('display','block');
	$('#btExcluirEvento').css('display','block');
	$("#eventoId").val(evento.id);
	$("#titulo").val(evento.titulo);
	$("#data").val(evento.data),
	$("#hora").val(evento.hora),
	$("#descricao").val(evento.descricao);
	$("#eventoEnderecoId").val(evento.endereco.id);
	$("#cep").val(evento.endereco.cep);
	$('#tlogradouro').val(evento.endereco.logradouro);
	$('#nlogradouro').val(evento.endereco.nome);
	$('#numero').val(evento.endereco.numero);
	$('#referencia').val(evento.endereco.referencia);
	$('#bairroId').val(evento.endereco.bairro.id);
	$('#bairro').val(evento.endereco.bairro.nome);
	$('#cidadeId').val(evento.endereco.bairro.cidade.id);
	$('#cidade').val(evento.endereco.bairro.cidade.nome);
	$('#ufId').val(evento.endereco.bairro.cidade.unidadeFederativa.id);
	$('#nUF').val(evento.endereco.bairro.cidade.unidadeFederativa.nome);


}


function getEvento(){

	return{
		"id" :  $("#eventoId").val(),
		"titulo": $("#titulo").val(),
		"data": $("#data").val()+" 00:00:00",
		"hora": $("#hora").val(),
		"descricao": $("#descricao").val(),
		"endereco":{
				"id" :  $("#eventoEnderecoId").val(),
				"cep": $("#cep").val(),
			    "logradouro": $('#tlogradouro').val(),
			    "nome": $('#nlogradouro').val(),
			    "numero" : $('#numero').val(),
			    "referencia": $('#referencia').val(),
			    "bairro": {
			     "id":$('#bairroId').val(),	
			     "nome": $('#bairro').val(),
			     "cidade":{
			       "id":$('#cidadeId').val(),
			       "nome": $('#cidade').val(),
			       "unidadeFederativa":{
			    	 "id":$("#ufId").val(),
			    	 	"nome" : $('#nUF').val()
			       }
			     }
			   }
		}


	}


	



}
