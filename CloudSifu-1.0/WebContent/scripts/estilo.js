var lista = ""; 
function adicionarEstilo(){
	var titulo = "Adicionar Estilo";
	var Obj = JSON.stringify(getEstilo());
	var validaForm = validaFormEstilo();
if (validaForm){
	$.ajax({
		url: "../rest/estilo/",
		method: "POST",
		data: Obj,
		success:function(msg){
			showModalOK(titulo, "Estilo adicionado com sucesso ");
			carregarPagina('../pages/estiloForms.html');
		},
		error: function(msg){
			showModalOK(titulo, "Erro ao adicionar ");
		}
	});
}else{
	showModalOK(titulo, "Todos os campos devem ser preenchidos corretamente! ");
}
	
}

function editarEstilo(){
	var estilo = JSON.stringify(getEstilo());
	var titulo = "Editar Estilo";
	var mensagem = "Prosseguir com a alteração dos dados do estilo ?"
var callback = function(){
	$.ajax({
		url: "../rest/estilo/",
		method: "PUT",
		data: estilo,
		success:function(estilo){
			showModalOK(titulo, "Estilo editado com sucesso");
			carregarPagina('../pages/estiloForms.html');
			
		},
		error: function(xhr, status, error){
			showModalOK(titulo, "Falha ao editar estilo \n"+xhr.responseText);
		}
	});
}
showModalSimNao(titulo, mensagem, callback);
}



function excluirEstilo(id){
	 
	 var titulo = "Excluir estilo";
	 var mensagem = "Confirma a exclusão do estilo ?"
	 var callback = function(){
		$.ajax({
			url: "../rest/estilo/id/"+id,
			method: "DELETE",
			success:function(){
				showModalOK(titulo, "Estilo excluído com sucesso");
				carregarPagina('../pages/estiloForms.html');
			},
			error: function(){
				showModalOK(titulo, "Falha ao excluir estilo");
			}
		});
	}
	showModalSimNao(titulo, mensagem, callback); 
	}

function selecionarObj(count){	
	setEstilo(lista[count]);
}


function buscarestilos(estilo){

	$.ajax({
		url: "../rest/estilo/nome/"+estilo,
		method: "GET",
		success: function(lista){
			popularListaEstilo(lista);
		},error: function(e){
			console.log(e);
		}
	});
	
}

