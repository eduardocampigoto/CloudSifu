function adicionarUsuarioTurma(usuarioTurma){
var usuarioTurma = JSON.stringify(getUsuarioTurma());	
var titulo = "Adicionar Usuário a turma";
var mensagem = "";
	$.ajax({
		url: "../rest/turmaUsuario/",
		method: "POST",
		data: usuarioTurma,
		success: function(){
			mensagem = "Usuário adicionado a turma com sucesso";
			showModalOK(titulo, mensagem);			
		},		
		error:function(){
			mensagem = "Erro ao adicionar usuário a turma";
			showModalOK(titulo, mensagem);
		}
	});
carregarPagina("../pages/turmaUsuarioForms.html");

}

function buscarTurma(turma){
	if(turma.length > 2 ){
	$.ajax({
		url: "../rest/turma/nome/"+turma,
		method: "GET",
		success: function(lista){
			listaTurmasGlob = lista;
			popularSeletorTurmas(lista);			
		},		
		error:function(){
			console.log("Erro ao buscar turmas")
		}
	});
	}
}




function buscaUsuarioNome(busca){

	$.ajax({
		url: "../rest/usuario/lista/"+busca,
		method: "GET",
		success: function(lista){
			listaUsuariosGlob = lista;			
			popularBuscaUsuarios(lista);
			
		},
		error:function(){
			console.log("erro ao buscar usuário");
						
		}
	});
 
}


function buscaTurmaUsuarios(id){
		$.ajax({
			url: "../rest/turmaUsuario/listaTurmaUsuarios/"+id,
			method: "GET",
			success: function(lista){
				usuariosTurmaSelecionadaGlob = lista;				
				popularListaTurmaUsuarios(usuariosTurmaSelecionadaGlob);

			},
			error:function(){
				console.log("erro ao buscar usuários vinculados a turma selecionada");
				
			}
		});		
}


function removerUsuario(id){
	var titulo = "Remover usuário";
	$.ajax({
		url: "../rest/turmaUsuario/"+id,
		method: "DELETE",
		success: function(msg){
			mensagem = "Usuário removido";
			showModalOK(titulo, mensagem);			
		},
		error:function(){
			mensagem = "Erro ao remover usuário";
			showModalOK(titulo, mensagem);
			
		}
	});		
	carregarPagina("../pages/turmaUsuarioForms.html");	
}



