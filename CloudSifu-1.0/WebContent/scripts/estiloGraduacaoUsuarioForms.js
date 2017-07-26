listaEstilosGlob = "";
listaGraduacaoGlob = "";
listaEstiloGraduacaoUsuarioGlob ="";
graduacaoSelecionadaGlob = "";
estiloSelecionadoGlob = "";
graduacaoSelecionadaStrGlob = "";
estiloGraduacaoUsuarioGlob ="";
estiloGraduacaoUsuarioStrGlob = "";
graduacaoEstiloSelecionadaGlob = "";
usuarioSelecionadoGlob = "";

$(document).ready(function() {
	buscarestilo("todos");	
	buscarGraduacoes("todas");
	$('#dataGraduacao').change(function(){
		validarAdicionarGraduacao();
	});
	
	$('#buscaUsuario').keyup(function() {
		validarAdicionarGraduacao();
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
	
	
	
	
	$('#buscaGraduacao').keyup(function() {
		if (event.keyCode == 8 || event.keyCode == 46) {
			limpaBuscaGraduacao();
			return false;
		}
		if (event.keyCode == 32) {
			return false;
		}
		buscaGraduacaoNome($('#buscaGraduacao').val());
		$('#seletorGraduacao').show();
		validarAdicionarGraduacao();
	});
	
	$('#buscaGraduacao #seletorGraduacao').focus(function() {
		if ($('#seletorGraduacao').html() != "") {
			$('#seletorGraduacao').show();
		}
	});
	
	$('#buscaGraduacao #seletorGraduacao').focusout(function() {
		$('#seletorGraduacao').html("");
		$('#seletorGraduacao').hide();

	});
	
	$('#buscaEstilo').keyup(function() {
		if (event.keyCode == 8 || event.keyCode == 46) {
			limpaBuscaEstilo();
			return false;
		}
		if (event.keyCode == 32) {
			return false;
		}

		buscarestilo($('#buscaEstilo').val());
		$('#seletorestilo').show();
	});

	$('#buscaEstilo #seletorestilo').focus(function() {
		if ($('#seletorestilo').html() != "") {
			$('#seletorestilo').show();
		}
	});

	$('#buscaEstilo #seletorestilo').focusout(function() {
		$('#seletorestilo').html("");
		$('#seletorestilo').hide();
	});
	
	

});

function limpaBuscaUsuarios() {
	listaUsuarios = "";
	usuarioSelecionadoGlob = "";
	$('#seletorUsuario').html("");
	$('#seletorUsuario').hide();
};

function limpaBuscaGraduacao() {
	listaGraduacao = "";
	graduacaoSelecionadaGlob = "";
	$('#seletorGraduacao').html("");
	$('#seletorGraduacao').hide();
};

function limpaBuscaEstilo() {
	listaEstilos = "";
	estiloSelecionadoGlob = "";
	$('#seletorestilo').html("");
	$('#seletorestilo').hide();
	buscarestilo("todas");
};


function validarAdicionarGraduacao() {
		if (estiloSelecionadoGlob != undefined 
			&& estiloSelecionadoGlob != ""
			&& usuarioSelecionadoGlob != undefined
			&& usuarioSelecionadoGlob != ""
			&& $('#dataGraduacao').val() != undefined
			&& $('#dataGraduacao').val() != ""
			&& $('#dataGraduacao').val().length == 10) {
		$('#btAdicionarUsuario').prop('disabled', false);
	} else {
		$('#btAdicionarUsuario').prop('disabled', true);
	}
}

function popularBuscaUsuarios(listaUsuarios) {
	seletorUsuario = "";
	if (listaUsuarios != undefined && listaUsuarios != "") {
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
			usuarioSelecionadoNomeGlob = usuarioSelecionado.nome + " "
					+ usuarioSelecionado.sobrenome;
			$('#buscaUsuario').val(usuarioSelecionadoNomeGlob);
			estiloGraduacaoUsuarioStrGlob = getEstiloGraduacaoUsuario();
			buscaEstiloGraduacaoUsuario(usuarioSelecionado.id);
			validarAdicionarGraduacao();

		}
	});

}

function validarAdicionarGraduacao() {

	if (estiloSelecionadoGlob != undefined &&
			estiloSelecionadoGlob != "" &&
			usuarioSelecionadoGlob != undefined &&
			usuarioSelecionadoGlob != "" && 
			graduacaoSelecionadaGlob != undefined &&
			graduacaoSelecionadaGlob != "") {
		$('#btAdicionarGraduacao').prop('disabled', false);
	} else {
		$('#btAdicionarGraduacao').prop('disabled', true);
	}
}

function popularListaGraduacao(listaGraduacao) {
	seletorGraduacao = "";
	if (listaGraduacao != null && listaGraduacao != "") {
		listaGraduacaoGlob = listaGraduacao;
		listaGraduacao.forEach(function(graduacao) {
			seletorGraduacao += "<li role='presentation' value='"
					+ graduacao.id + "' onclick='javascript: selecionaGraduacao("
					+ graduacao.id + ");'>" + "<a role='menuitem' href='#'>"
					+ graduacao.nome +"</a></li>";

		});
		$('#seletorGraduacao').html(seletorGraduacao);
	}
}

function selecionaGraduacao(id) {
	listaGraduacaoGlob.forEach(function(graduacaoSelecionada) {
		if (graduacaoSelecionada.id == id) {
			graduacaoSelecionadaGlob = graduacaoSelecionada;
			graduacaoSelecionadaStrGlob = graduacaoSelecionada.nome;
			$('#buscaGraduacao').val(graduacaoSelecionadaStrGlob);
			validarAdicionarGraduacao();
		}
	});

}

function popularSeletorestilos(listaEstilos) {
	seletorestilos = "";
	if (listaEstilos != undefined && listaEstilos != "") {
		listaEstilos.forEach(function(estilo) {
			seletorestilos += "<li role='presentation' value='" + estilo.id
					+ "' onclick='javascript: selecionaEstilo(" + estilo.id
					+ ");'><a role='menuitem' href='#'>" + estilo.nome
					+ "</a></li>";
		});
		$('#seletorEstilo').html(seletorestilos);
	}
}

function selecionaEstilo(id) {
	listaEstilosGlob.forEach(function(estilo) {
		if (estilo.id == id) {
			$('#buscaEstilo').val(estilo.nome);
			estiloSelecionadoGlob = estilo;			
			validarAdicionarGraduacao();
		}
	});
}



function popularListaEstiloGraduacaoUsuario(estiloGraduacaoUsuario) {
	listaGraduacao="";
	estiloGraduacaoUsuarioGlob = estiloGraduacaoUsuario;
	if (estiloGraduacaoUsuario != undefined
			&& estiloGraduacaoUsuario != "") {
		estiloGraduacaoUsuario.forEach(function(graduacoesUsuario) {
					listaGraduacao += "<tr>"
							+ "<td  rows='1'>"
							+ graduacoesUsuario.data
							+ "</td>"
							+ "<td  rows='1'>"
							+ graduacoesUsuario.usuario.nome
							+ "</td>"
							+ "<td>"
							+ graduacoesUsuario.estilo.nome
							+ "</td>"
							+ "<td>"
							+ graduacoesUsuario.graduacao.nome
							+ "</td>"
							+ "<td rows='1'><a type='button' class='btn btForm glyphicon glyphicon-remove' id='btRemoverGraduacaoUsuario' style='color: #010101' onclick='javascript: removerGraduacaoUsuario("
							+ graduacoesUsuario.id + ");'> Remover</a>"
							+ "</td></tr>";
				});
		$('#dadosEstilo').show();
		$('#estiloGraduacaoUsuarioId').html(estiloGraduacaoUsuario.id);
		/*$('#idEstilo').html(" Id: " + estilo.id);
		$('#nomeEstilo').html(" Nome: " + estilo.nome);
		$('#descricaoEstilo').html("	Descrição: " + estilo.descricao);
		*/
		$('#listaGraduacao').html(listaGraduacao);
	}

}


function getEstiloGraduacaoUsuario(){
	graduacao = graduacaoSelecionadaGlob;
	estilo = estiloSelecionadoGlob;
	usuario = usuarioSelecionadoGlob;
	dataGraduacao = $('#dataGraduacao').val()+" 00:00:00"
	return {
		"graduacao" : {
			"id": graduacao.id
		},		
		"estilo" : {
			"id":  estilo.id	
		},
		"usuario":{
			"id" : usuario.id
		},
		"data": dataGraduacao
	}
}
