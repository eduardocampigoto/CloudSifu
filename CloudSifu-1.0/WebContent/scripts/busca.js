var listaBusca = "";
var buscaString = "";
var buscaEstiloGlob = "";
var buscaUsuarioGlob = "";
var buscaEventoGlob = "";
var listaBuscaUsuario = "";
var listaBuscaEvento = "";
var listaBuscaEstilo = "";
var dataVar="";
$(document).ready(
		function() {

			$('#campoBuscaId').focus(function() {
				$("#buscaLista").html("");
				$('#campoBuscaId').val("");
				$('#buscaAmostra').html($('#campoBuscaId').val());
			});

			$('#dataBar').datepicker().change(function() {
						listaBusca="";
						$('#campoBuscaId').val($('#dataBar').val());
						var dataVar = $('#dataBar').val();
						$('#campoBuscaId').focus().val(dataVar);
						var dataParte = dataVar.split('/');
						dataVar = dataParte[2] + "-" + dataParte[1] + "-"
								+ dataParte[0];
						buscarEventoBarData(dataVar);
			});

			$('#campoBuscaId').keyup(function(){
						var buscaString="";
						if (event.keyCode == 8 || event.keyCode == 46) {
							return false;
						}
						if (event.keyCode == 32) {
							return false;
						}
						regex = "/";
				
						buscaString = $('#campoBuscaId').val();
						if (buscaString.length >=2 
							&& !buscaString.match(regex)
							&& buscaString != " "
							&& buscaString != undefined
							&& buscaString != null) {
							buscarUsuarioBar(buscaString);
							buscarEventoBar(buscaString);
							buscarestiloBar(buscaString);
							listaBusca = listaBuscaUsuario + listaBuscaEvento
									+ listaBuscaEstilo;
							mostrarListaBusca();
						} else {
							limpaBusca();
						}

					});
			
			$('#campoBuscaId').focusout(function() {
				$('#campoBuscaId').val("");
			})

			  $('#buscaLista').mouseleave(function() { limpaBusca();
			  $('#campoBuscaId').val(""); });
		 
});

function mostrarListaBusca() {
	$("#buscaLista").html(listaBusca);
	$('#buscaLista').css("display", "block");
};

function limpaBusca() {
	listaBusca = "";
	listaBusca = "";
	buscaString = "";
	listaBuscaUsuario = "";
	listaBuscaEvento = "";
	listaBuscaEstilo = "";
	$('#buscaLista').html("");
	$('#buscaLista').css("display", "none");
};

function buscarEventoBar(titulo) {

	$.ajax({
		url : "../rest/evento/titulo/" + titulo,
		method : "GET",
		success : function(lista) {
			if (lista != null && lista != undefined && lista != "") {
				buscaEventoGlob = lista;
				montarListaEventoBusca(lista);
			}else{
				return;
			}
		},
		error : function(e) {
			console.log(e);
		}
	});

}

function buscarEventoBarData(dataVar){
	$.ajax({
		url: "../rest/evento/data/"+dataVar,
		method: "GET",
		success: function(lista){
			if(lista != null){
				buscaEventoGlob = lista;
				montarListaEventoBusca();
			}
		},error: function(e){
			console.log(e);
		}
	});

}

function montarListaEventoBusca() {
	listaBuscaEvento = "";
	if (buscaEventoGlob != null && buscaEventoGlob != "" && buscaEventoGlob != undefined) {
		buscaEventoGlob.forEach(function(obj) {
					listaBuscaEvento += "<li id='"
							+ obj.id
							+ "' class='resultBusca'><a href='#' onclick='selecionaEventoBusca("
							+ obj.id + ")'> Evento | " + obj.id + " | "
							+ obj.titulo + "</a></li>";
				});
		listaBusca = listaBuscaEvento;
		mostrarListaBusca();
		
	}
}




function selecionaEventoBusca(id) {
	var callback = function() {
		buscaEventoGlob.forEach(function(obj) {
			if (id == obj.id) {
				setEvento(obj);
			}
		});
	}

	carregarPagina("../pages/eventoForms.html", callback);
}

function buscarUsuarioBar(busca) {
	$.ajax({
		url : "../rest/usuario/lista/" + busca,
		method : "GET",
		success : function(lista) {
			if (lista != null && lista != undefined && lista != "") {
				montarListaUsuarioBusca(lista);
			}
		},
		error : function(data) {
			console.log("erro" + data);
		}
	});

}

function montarListaUsuarioBusca(listaUsuario) {
	listaBuscaUsuario = "";
	buscaUsuarioGlob = listaUsuario;
	if (listaUsuario != null && listaUsuario != "" && listaUsuario != undefined) {
		listaUsuario
				.forEach(function(obj) {
					listaBuscaUsuario += "<li id='"
							+ obj.id
							+ "' class='resultBusca'><a href='#' onclick='javascript: selecionaUsuarioBusca("
							+ obj.id + ")'> Usuário | " + obj.id + " | "
							+ obj.nome + " " + obj.sobrenome + "</a></li>";
				});
	}
	listaUsuario = "";
}

function selecionaUsuarioBusca(id) {
	listaUsuariosGlob=buscaUsuarioGlob;
	var callback = function() {
		buscaUsuarioGlob.forEach(function(obj) {
			if (id == obj.id) {
				setUsuario(obj);
			}
		});
	}
	carregarPagina("../pages/usuarioForms.html", callback);
}

function buscarestiloBar(nome) {
	$.ajax({
		url : "../rest/estilo/nome/" + nome,
		method : "GET",
		success : function(lista) {
			if (lista != null && lista != undefined && lista != "") {
				montarListaEstiloBusca(lista);
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function montarListaEstiloBusca(listaEstilo) {
	listaBuscaEstilo = "";
	if (listaEstilo != null && listaEstilo != "" && listaEstilo != undefined) {
		buscaEstiloGlob = listaEstilo;
		listaEstilo
				.forEach(function(obj) {
					listaBuscaEstilo += "<li id='"
							+ obj.id
							+ "' class='resultBusca'><a href=# onClick='javascript: selecionaEstiloBusca("
							+ obj.id + ");'> Estilo | " + obj.id + " | "
							+ obj.nome + "</a></li>";
				});
	}
	listaEstilo = "";
}

function selecionaEstiloBusca(id) {

	var callback = function() {
		buscaEstiloGlob.forEach(function(obj) {
			if (id == obj.id) {
				setEstilo(obj);
			}
		});
	};
	carregarPagina("../pages/estiloForms.html", callback);

}
