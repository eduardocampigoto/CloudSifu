var lista = ""; 
function adicionarGraduacao(){
	var titulo = "Adicionar Graduação";
	var Obj = JSON.stringify(getGraduacao());
	var validaForm = validaFormGraduacao();
if (validaForm){
	$.ajax({
		url: "../rest/graduacao/",
		method: "POST",
		data: Obj,
		success:function(msg){
			showModalOK(titulo, "Graduacão adicionada com sucesso ");
			carregarPagina('../pages/graduacaoForms.html');
		},
		error: function(msg){
			showModalOK(titulo, "Erro ao adicionar ");
		}
	});
}else{
	showModalOK(titulo, "Todos os campos devem ser preenchidos corretamente! ");
}
	
}




function buscarGraduacoes(){
	$.ajax({
		url: "../rest/graduacao/"+"todas",
		method: "GET",
		success: function(lista){
			popularListaGraduacao(lista);
		},error: function(e){
			console.log(e);
		}
	});	
}
	



function buscarGraduacaoNome(nome){
	
	$.ajax({
		url: "../rest/graduacao/nome/"+nome,
		method: "GET",
		success: function(lista){
			if(lista != null){
				montaListaBusca(lista);
			}
		},error: function(e){
			console.log(e);
		}
	});

	}


function editarGraduacao(){
	var graduacao = JSON.stringify(getGraduacao());
	var titulo = "Editar Graduacão";
	var mensagem = "Prosseguir com a alteração dos dados da graduação ?"
var callback = function(){
	$.ajax({
		url: "../rest/graduacao/",
		method: "PUT",
		data: graduacao,
		success:function(graduacao){
			showModalOK(titulo, "Graduação editada com sucesso");
			carregarPagina('../pages/graduacaoForms.html');
			
		},
		error: function(xhr, status, error){
			showModalOK(titulo, "Falha ao editar  a graduacao \n"+xhr.responseText);
		}
	});
}
showModalSimNao(titulo, mensagem, callback);
}




function excluirGraduacao(id){
	 
	 var titulo = "Excluir graduacao";
	 var mensagem = "Confirma a exclusão da graduação ?"
	 var callback = function(){
		$.ajax({
			url: "../rest/graduacao/id/"+id,
			method: "DELETE",
			success:function(){
				showModalOK(titulo, "Graduacao excluída com sucesso");
				carregarPagina('../pages/graduacaoForms.html');
			},
			error: function(){
				showModalOK(titulo, "Falha ao excluir graduacao");
			}
		});
	}
	showModalSimNao(titulo, mensagem, callback); 
	}

function selecionarObj(count){
	
	setGraduacao(lista[count]);
}

function popularListaGraduacao(listaGraduacoes){
	lista = listaGraduacoes;
	var count = 0;
	if(listaGraduacoes != null && listaGraduacoes != ""){
	
		listaGraduacoes.forEach(function(graduacao){
			$('#listaGraduacoes').append("<tr><td  rows='1'>"+graduacao.id+"</td>"+
					"<td>"+graduacao.nome+"</td>"+
					"<td>"+graduacao.descricao+"</td>"+
					"<td>"+				
			"<a type='button' class='btn btForm glyphicon glyphicon-edit' id='btEditarGraduacao' style='color: #010101' onclick='javascript: selecionarObj("+count+")'> Editar</a>"+
			"<a type='button' class='btn btForm glyphicon glyphicon-remove' id='btExcluirGraduacao' style='color: #010101' onclick='javascript: excluirGraduacao("+graduacao.id+")'> Excluir</a>"+
			"</td></tr>");
			count++;			
		});	
	}
	
}


