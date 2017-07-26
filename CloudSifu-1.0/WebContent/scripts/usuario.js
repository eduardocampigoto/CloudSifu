var buscaUsuario = "";
$('document').ready(function() {

	$('#btAdicionarEscola').click(function() {
		//$('#escform').toggle('closed');
		$('#escform').show();
	});
});

function adicionarUsuario() {
	titulo = "Adicionar usuário";
	mensagem = "Usuário adicionado com sucesso";
if (validaCamposUsuario()){ 
		var usuario = JSON.stringify(getUsuario());
		$.ajax({
			url : "../rest/usuario/",
			method : "POST",
			data : JSON.stringify(getUsuario()),
			success : function() {
				showModalOK(titulo, mensagem);
				carregarPagina("../pages/usuarioForms.html");
			},
			error : (function() {
				
			})

		});

	}else{
		showModalOK("Erro", "Todos os campos devem estar preenchidos corretamente");
	}
}

function editarUsuario() {
	titulo = "Editar usuário";
	mensagem = "Deseja editar o usuário " + usuario.nome + " "
			+ usuario.sobrenome + "? ";
	var callback = function() {
		var editUsuario = JSON.stringify(getUsuario());
		if (validaCamposUsuario()) {
			$.ajax({
				url : "../rest/usuario/",
				method : "PUT",
				data : editUsuario,
				success : function() {
					showModalOK(titulo, "Usuário editado com sucesso");
					carregarPagina("../pages/usuarioForms.html");
				},
				error : function() {
					showModalOK(titulo, "erro ao editar usuário");

				}

			});
		}
		$('#dialogDiv').html("");
	}
	showModal(titulo, mensagem, callback);
}

function excluirUsuario() {
	titulo = "Excluir usuário";
	mensagem = "Deseja excluir o usuário " + usuario.nome + " "
			+ usuario.sobrenome + "? ";

	var callback = function() {
		userDel = $('#userId').val();
		$.ajax({
			url : "../rest/usuario/" + userDel,
			method : "DELETE",
			success : function() {
				showModalOK(titulo, "Usuário excluído com sucesso");
				carregarPagina("../pages/usuarioForms.html");
			},
			error : function() {
				showModalOK(titulo, "erro ao excluir usuário");
			}
		});
	}
	showModal(titulo, mensagem, callback);
}
