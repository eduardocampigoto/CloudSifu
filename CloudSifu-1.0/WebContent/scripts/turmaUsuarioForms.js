listaTurmasGlob = "";
listaUsuariosGlob = "";
usuarioSelecionadoGlob = "";
turmaSelecionadaGlob = "";
usuarioSelecionadoStrGlob = "";
// Lista dos usuários vinculados a turma selecionada
usuariosTurmaSelecionadaGlob = "";
$(document).ready(function() {
	buscarTurma("todas");
	$('#buscaUsuario').keyup(function() {
		validarAdicionarUsuario();
		if (event.keyCode == 8 || event.keyCode == 46) {
			limpaBuscaUsuarios();
			return false;
		}
		if (event.keyCode == 32) {
			return false;
		}
		buscaUsuarioNome($('#buscaUsuario').val());
		$('#seletorUsuario').show();
		
	});
	
	$('#buscaUsuario #seletorUsuario').focus(function() {
		if ($('#seletorUsuario').html() != "") {
			$('#seletorUsuario').show();
		}
	});
	
	$('#buscaUsuario #seletorUsuario').focusout(function() {
		$('#seletorUsuario').html("");
		$('#seletorUsuario').hide();

	});
	
	$('#buscaTurma').keyup(function() {
		if (event.keyCode == 8 || event.keyCode == 46) {
			limpaBuscaTurma();
			return false;
		}
		if (event.keyCode == 32) {
			return false;
		}

		buscarTurma($('#buscaTurma').val());
		$('#seletorTurma').show();
	});

	$('#buscaTurma #seletorTurma').focus(function() {
		if ($('#seletorTurma').html() != "") {
			$('#seletorTurma').show();
		}
	});

	$('#buscaTurma #seletorTurma').focusout(function() {
		$('#seletorTurma').html("");
		$('#seletorTurma').hide();

	});

});

function limpaBuscaUsuarios() {
	listaUsuarios = "";
	usuarioSelecionadoGlob = "";
	$('#seletorUsuario').html("");
	$('#seletorUsuario').hide();
};

function limpaBuscaTurma() {
	listaTurmas = "";
	turmaSelecionadaGlob = "";
	$('#seletorTurma').html("");
	$('#seletorTurma').hide();
	buscarTurma("todas");
};

function validarAdicionarUsuario() {

	if (turmaSelecionadaGlob != undefined && turmaSelecionadaGlob != ""
			&& usuarioSelecionadoGlob != undefined
			&& usuarioSelecionadoGlob != "") {
		$('#btAdicionarUsuario').prop('disabled', false);
	} else {
		$('#btAdicionarUsuario').prop('disabled', true);
	}
}
function popularBuscaUsuarios(listaUsuarios) {
	seletorUsuario = "";
	if (listaUsuarios != null && listaUsuarios != "") {
		listaUsuariosGlob = listaUsuarios;
		listaUsuarios.forEach(function(usuario) {
			seletorUsuario += "<li role='presentation' value='"
					+ usuario.id + "' onclick='javascript: selecionaUsuario("
					+ usuario.id + ");'>" + "<a role='menuitem' href='#'>"
					+ usuario.nome + " " + usuario.sobrenome + "</a></li>";

		});

		$('#seletorUsuario').html(seletorUsuario);
	}
}

function selecionaUsuario(id) {
	listaUsuariosGlob.forEach(function(usuarioSelecionado) {
		if (usuarioSelecionado.id == id) {
			usuarioSelecionadoGlob = usuarioSelecionado;
			usuarioSelecionadoStrGlob = usuarioSelecionado.nome + " "
					+ usuarioSelecionado.sobrenome;
			$('#buscaUsuario').val(usuarioSelecionadoStrGlob);
			validarAdicionarUsuario();

		}
	});

}

function popularSeletorTurmas(listaTurmas) {
	seletorTurmas = "";
	if (listaTurmas != null && listaTurmas != "") {
		listaTurmas.forEach(function(turma) {
			seletorTurmas += "<li role='presentation' value='" + turma.id
					+ "' onclick='javascript: selecionaTurma(" + turma.id
					+ ");'><a role='menuitem' href='#'>" + turma.nome
					+ "</a></li>";
		});
		$('#seletorTurma').html(seletorTurmas);
	}
}

function selecionaTurma(id) {
	listaTurmasGlob.forEach(function(turma) {
		if (turma.id == id) {
			$('#buscaTurma').val(turma.nome);
			window.turmaSelecionadaGlob = turma;
			buscaTurmaUsuarios(window.turmaSelecionadaGlob.id);
			validarAdicionarUsuario();
		}
	});
}

function popularListaTurmaUsuarios(usuariosTurmaSelecionada) {
	listaUsuarios = "";
	if (usuariosTurmaSelecionada.graduacao == undefined) {
		graduacao = "sem graduação definida";
	} else {
		graduacao = usuariosTurmaSelecionada.graduacao;
	}
	if (usuariosTurmaSelecionadaGlob != null
			&& usuariosTurmaSelecionadaGlob != "") {
		usuariosTurmaSelecionadaGlob
				.forEach(function(turmaUsuario) {
					turma = turmaUsuario.turma;
					listaUsuarios += "<tr>"
							+ "<td  rows='1'>"
							+ turmaUsuario.usuario.id
							+ "</td>"
							+ "<td  rows='1'>"
							+ turmaUsuario.usuario.nome
							+ "</td>"
							+ "<td>"
							+ graduacao
							+ "</td>"
							+ "<td rows='1'><a type='button' class='btn btForm glyphicon glyphicon-remove' id='btRemoverUsuario' style='color: #010101' onclick='javascript: removerUsuario("
							+ turmaUsuario.id + ");'> Remover</a>"
							+ "</td></tr>";
				});
		$('#dadosTurma').show();
		$('#turmaUsuarioId').html(turma.id);
		$('#idTurma').html("<div class='destaque'>Id:</div>" + turma.id);
		$('#nomeTurma').html("<div class='destaque'>Nome:</div>" + turma.nome);
		$('#diaTurma').html("<div class='destaque'>Dia da semana:</div>" + turma.diaSemana);
		$('#horarioTurma').html("<div class='destaque'>Horario:</div>" + turma.horario);
		$('#descricaoTurma').html("<div class='destaque'>Descrição:</div>" + turma.descricao);
		$('#listaUsuarios').html(listaUsuarios);
	}

}

function getUsuarioTurma() {
	usuario = usuarioSelecionadoGlob;
	turma = turmaSelecionadaGlob;
	return {
		"usuario" : {
			"id": usuario.id
		},		
		"turma" : {
			"id":  turma.id	
		}
	}
}
