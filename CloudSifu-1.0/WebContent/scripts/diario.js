$(document).ready(function(){
});

function enviarDiarioTurma(){
	var metodo = "POST";
	var titulo = "Salvar Diario";
	var diarioTurmaJson = JSON.stringify(getDiarioTurma());	
	var validaForm = validaFormDiario();
if (validaForm){
	if(diarioTurmaGlob.id != 0){
		metodo = "PUT";
	}
	$.ajax({
		url: "../rest/diarioTurma/",
		method: metodo,
		data: diarioTurmaJson,
		success:function(){
				enviarDiarioUsuario();

		},
		error: function(msg){
			showModalOK(titulo, "Erro ao salvar diario da turma");
		}
	});
}else{
	showModalOK(titulo, "Todos os campos devem ser preenchidos corretamente! ");
}
	
}


function enviarDiarioUsuario(){
	diarioUsuarioJson = JSON.stringify(diarioUsuariosEnviarGlob);
	console.log(diarioUsuariosEnviarGlob);
	var titulo = "Salvar Diario";
	$.ajax({
		url: "../rest/diarioUsuario/",
		method: "POST",
		data: diarioUsuarioJson,
		success:function(){
			showModalOK(titulo, "Diario salvo com sucesso ");
			carregarPagina('../pages/diarioForms.html');

		},
		error: function(msg){
			showModalOK(titulo, "Erro ao salvar diario dos alunos");
		}
	});
}


function buscaDiarioData(){
	$.ajax({
		url: "../rest/diarioTurma/data/",
		method: "POST",
		data: diarioTurmaGlob,
		success: function(diario){
			diarioTurmaGlob = diario;
			if(diario != null && diario != undefined){
				setDiarioTurma();
				buscaAlunosTurma();
			}
		},error: function(e){
			console.log(e);
		}
	});

}

function buscaAlunosTurma(){

	$.ajax({
		url: "../rest/turmaUsuario/listaTurmaUsuarios/"+turmaGlob.id,
		method: "GET",
		success: function(lista){
			console.log(lista);
			turmasGlob = lista;		
			buscaAlunosDiario();
		},
		error:function(){
			console.log("erro ao buscar usuários vinculados a turma selecionada");
			
		}
	});		


}


function buscaAlunosDiario(){
	$.ajax({
		url: "../rest/diarioUsuario/listaDiarioUsuario/"+diarioTurmaGlob.id,
		method: "GET",
		success: function(lista){
			diarioUsuariosGlob = lista;
			popularTabelaDiario();
		},
		error:function(){
			console.log("erro ao buscar usuários vinculados a turma selecionada");
			
		}
	});		
}



function buscarTurmaDiario(nomeTurma){
	if(nomeTurma.length > 2 ){
	$.ajax({
		url: "../rest/turma/nome/"+nomeTurma,
		method: "GET",
		success: function(lista){
			turmasGlob = lista;
			popularSeletorTurmas();			
		},		
		error:function(){
			console.log("Erro ao buscar turmas")
		}
	});
	}
}

function editarDiario(){
	var diario = JSON.stringify(getDiarioTurma());	
	var titulo = "Editar Diario";
	var mensagem = "Prosseguir com a alteração dos dados do diario ?"
var callback = function(){
	$.ajax({
		url: "../rest/diario/",
		method: "PUT",
		data: diario,
		success:function(diario){
			showModalOK(titulo, "Diario editado com sucesso");
			carregarPagina('../pages/diarioForms.html');
			
		},
		error: function(xhr, status, error){
			showModalOK(titulo, "Falha ao editar diario \n"+xhr.responseText);
		}
	});
}
showModalSimNao(titulo, mensagem, callback);
}

function excluirDiario(){
 var id = $('#diarioId').val();
 var titulo = "Excluir diario";
 var mensagem = "Confirma a exclusão do diario ?"
 var callback = function(){
	$.ajax({
		url: "../rest/diario/id/"+id,
		method: "DELETE",
		success:function(){
			showModalOK(titulo, "Diario excluído com sucesso");
			carregarPagina('../pages/diarioForms.html');
		},
		error: function(){
			showModalOK(titulo, "Falha ao excluir diario");
		}
	});
}
showModalSimNao(titulo, mensagem, callback); 
}



