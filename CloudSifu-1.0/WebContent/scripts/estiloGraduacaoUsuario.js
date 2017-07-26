function adicionarGraduacaoEstilo(graduacaoEstilo){
var graduacaoEstilo = JSON.stringify(getEstiloGraduacaoUsuario());	
var titulo = "Adicionar Graduação a estilo";
var mensagem = "";
	$.ajax({
		url: "../rest/estiloGraduacaoUsuario/",
		method: "POST",
		data: graduacaoEstilo,
		success: function(){
			mensagem = "Graduação adicionada ao estilo com sucesso";
			showModalOK(titulo, mensagem);			
		},		
		error:function(){
			mensagem = "Erro ao adicionar graduação ao estilo";
			showModalOK(titulo, mensagem);
		}
	});
carregarPagina("../pages/estiloGraduacaoUsuarioForms.html");

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


function buscarestilo(estilo){
	if(estilo.length > 2 ){
	$.ajax({
		url: "../rest/estilo/nome/"+estilo,
		method: "GET",
		success: function(lista){
			listaEstilosGlob = lista;
			popularSeletorestilos(lista);			
		},		
		error:function(){
			console.log("Erro ao buscar estilo "+estilo);
		}
	});
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
	



function buscaGraduacaoNome(nome){
	
	$.ajax({
		url: "../rest/graduacao/nome/"+nome,
		method: "GET",
		success: function(lista){
			if(lista != null){
				popularListaGraduacao(lista);
			}
		},error: function(e){
			console.log(e);
		}
	});

	}



function buscaEstiloGraduacaoUsuario(usuarioId){
		$.ajax({
			url: "../rest/estiloGraduacaoUsuario/usuario/"+usuarioId,
			method: "GET",
			success: function(lista){
				estiloGraduacaoUsuarioGlob = lista;				
				popularListaEstiloGraduacaoUsuario(estiloGraduacaoUsuarioGlob);

			},
			error:function(){
				console.log("Erro ao buscar graduações vinculados a estilo selecionada");
				
			}
		});	
	
}

function removerGraduacaoUsuario(id){
	var titulo = "Remover graduação";
	$.ajax({
		url: "../rest/estiloGraduacaoUsuario/"+id,
		method: "DELETE",
		success: function(msg){
			mensagem = "Graduação removida";
			showModalOK(titulo, mensagem);
			limpaBuscaEstilo();
			limpaBuscaGraduacao();
			limpaBuscaUsuarios();
			
		},
		error:function(){
			mensagem = "Erro ao remover graduação";
			showModalOK(titulo, mensagem);
			
		}
	});		
	carregarPagina("../pages/estiloGraduacaoUsuarioForms.html");
}



