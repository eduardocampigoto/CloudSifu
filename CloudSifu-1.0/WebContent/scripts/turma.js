var lista = ""; 
function adicionarTurma(){
	var titulo = "Adicionar Turma";
	var Obj = JSON.stringify(getTurma());
	var validaForm = validaFormTurma();
if (validaForm){
	$.ajax({
		url: "../rest/turma/",
		method: "POST",
		data: Obj,
		success:function(msg){
			showModalOK(titulo, "Turma adicionada com sucesso ");
			carregarPagina('../pages/turmaForms.html');
		},
		error: function(msg){
			showModalOK(titulo, "Erro ao adicionar turma");
		}
	});
}else{
	showModalOK(titulo, "Todos os campos devem ser preenchidos corretamente! ");
}
	
}




function buscarTurmas(){
	
	
	$.ajax({
		url: "../rest/turma/nome/"+"todas",
		method: "GET",
		success: function(lista){
			popularListaTurma(lista);
		},error: function(e){
			console.log(e);
		}
	});
	
}

function editarTurma(){
	var turma = JSON.stringify(getTurma());
	console.log(turma);
	var titulo = "Editar Turma";
	var mensagem = "Prosseguir com a alteração dos dados da turma ?"
var callback = function(){
	$.ajax({
		url: "../rest/turma/",
		method: "PUT",
		data: turma,
		success:function(turma){
			showModalOK(titulo, "Turma editada com sucesso");
			carregarPagina('../pages/turmaForms.html');
			
		},
		error: function(xhr, status, error){
			showModalOK(titulo, "Falha ao editar turma \n"+xhr.responseText);
		}
	});
}
showModalSimNao(titulo, mensagem, callback);
}




function excluirTurma(id){
	 
	 var titulo = "Excluir turma";
	 var mensagem = "Confirma a exclusão da turma ?"
	 var callback = function(){
		$.ajax({
			url: "../rest/turma/id/"+id,
			method: "DELETE",
			success:function(){
				showModalOK(titulo, "Turma excluída com sucesso");
				carregarPagina('../pages/turmaForms.html');
			},
			error: function(){
				showModalOK(titulo, "Falha ao excluir turma");
			}
		});
	}
	showModalSimNao(titulo, mensagem, callback); 
	}

function selecionarObj(count){
	
	setTurma(lista[count]);
}

function popularListaTurma(listaTurmas){
	lista = listaTurmas;
	var count = 0;
	if(listaTurmas != null && listaTurmas != ""){
	
		listaTurmas.forEach(function(turma){
			$('#listaTurmas').append("<tr>"+
					"<td  rows='1'>"+turma.id+"</td>"+
					"<td  rows='1'>"+turma.nome+"</td>"+
					"<td>"+turma.horario+"</td>"+
					"<td>"+turma.diaSemana+"</td>"+
					"<td>"+turma.descricao+"</td>"+
			"<td><a type='button' class='btn btForm glyphicon glyphicon-edit' id='btEditarTurma' style='color: #010101' onclick='javascript: selecionarObj("+count+")'> Editar</a>"+
			"<a type='button' class='btn btForm glyphicon glyphicon-remove' id='btExcluirTurma' style='color: #010101' onclick='javascript: excluirTurma("+turma.id+")'> Excluir</a>"+
			"</td></tr>");
			count++;			
		});	
	}
	
}


