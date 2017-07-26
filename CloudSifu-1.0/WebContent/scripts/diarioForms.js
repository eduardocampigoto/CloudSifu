turmasGlob = "";
turmaGlob = "";
usuarioTurmaGlob = "";
diarioTurmaGlob = "";
diarioUsuarioGlob = "";
diarioUsuariosEnviarGlob=[];
diarioUsuariosGlob = [];
novoDiarioUsuarioGlob = "";
novoDiarioUsuariosGlob = [];

$('document').ready(function(){
	buscarTurmaDiario("todas");	
	$('#buscaTurma').keyup(function() {
		if (event.keyCode == 8 || event.keyCode == 46) {
			limpaBuscaTurma();
			return false;
		}
		if (event.keyCode == 32) {
			return false;
		}

		buscarTurmaDiario($('#buscaTurma').val());
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
	$('.form-control').focus(function(){
		$(this).css('background-color','#FFFFFF');
	});

	$('#data').change(function(){
		if($('#buscaTurma').val() != ""){
			selecionaTurma(turmaGlob.id);
		}
	});
	
	$(turmaGlob).change(function(){
		$('#buscaTurma').val(turma.nome);
	});
});

function limpaBuscaTurma() {
	turmaGlob = "";
	$('#seletorTurma').html("");
	$('#seletorTurma').hide();
	//buscarTurmaDiario("todas");
};



function popularSeletorTurmas() {
	seletorTurmas = "";
	if (turmasGlob != null && turmasGlob != "") {
		turmasGlob.forEach(function(turma) {
			seletorTurmas += "<li role='presentation' value='" + turma.id
					+ "' onclick='javascript: selecionaTurma(" + turma.id
					+ ");'><a role='menuitem' href='#'>" + turma.nome
					+ "</a></li>";
		});
		$('#seletorTurma').html(seletorTurmas);
	}
}

function selecionaTurma(id) {
	turmasGlob.forEach(function(turma) {
		if (turma.id == id) {
			$('#buscaTurma').val(turma.nome);
			turmaGlob = turma;
			$('#turmaId').val(turma.id);
			if($('#data').val() != ""){
				dataGlob = $('#data').val();
				diarioTurmaGlob = JSON.stringify(getDiarioTurma());	
				if(diarioTurmaGlob != "" && diarioTurmaGlob != undefined){					
				 buscaDiarioData();
				}
			}
		}
	});	
}

function criarDiario(){
	novoDiarioUsuarioGlob = new Object();
	novoDiarioUsuarioGlob.id = "0";
	novoDiarioUsuarioGlob.usuario = new Object();
	novoDiarioUsuarioGlob.diarioTurma = new Object();
	novoDiarioUsuarioGlob.presenca = false;
	novoDiarioUsuarioGlob.observacao = "";
	return novoDiarioUsuarioGlob;
}

function popularTabelaDiario() {
	listaAlunos = "";
	if (turmasGlob != undefined && turmasGlob != "") {
		turmasGlob.forEach(function(usuarioTurma) {
			usuarioTurmaGlob = usuarioTurma;
			listaAlunos+="<tr><td>"+usuarioTurmaGlob.usuario.id	
			+"<td>"
			+usuarioTurmaGlob.usuario.nome+" "+usuarioTurmaGlob.usuario.sobrenome
			+"</td>"
			+"<td>"
			+"<label class='checkbox-inline'>"
			+"<input id='presenca"+usuarioTurmaGlob.usuario.id+"' type='checkbox' >"
			+"</label>"
			+"</td>"
			+"<td>"
			+"<input type='text' class='form-control'  title='Observações' placeholder='Observações' aria-describedby='basic-addon1' "
			+"id='observacao"+usuarioTurmaGlob.usuario.id+"' value=''>"
			+"</td>";
			novoDiarioUsuarioGlob = criarDiario();
			novoDiarioUsuarioGlob.usuario = usuarioTurmaGlob.usuario;
			novoDiarioUsuarioGlob.diarioTurma = diarioTurmaGlob;
			novoDiarioUsuariosGlob.push(novoDiarioUsuarioGlob);
		});
		$('#listaAlunos').html(listaAlunos);
		

		diarioUsuariosGlob.forEach(function(diarioUsuario) {

					if (diarioUsuario.presenca == true) {
						$('#presenca' + diarioUsuario.usuario.id).attr(
								'checked', true);
					}
					if (diarioUsuario.observacao == undefined) {
						diarioUsuario.observacao = "";
					}
					$('#observacao' + diarioUsuario.usuario.id).val(
							diarioUsuario.observacao);
					novoDiarioUsuariosGlob.forEach(function(novoDiarioUsuario) {
								if (diarioUsuario.usuario.id == novoDiarioUsuario.usuario.id) {
									novoDiarioUsuario.id = diarioUsuario.id;
								}

							});

				});		
		$("#btCancelar").show();
	}
	

}

function salvarDiario(){
novoDiarioUsuariosGlob.forEach(function(diarioUsuario){	
		diarioTurmaGlob.observacoes = $('#observacoesTurma').val();
		diarioTurmaGlob.data = dataGlob+" 00:00:00";
		diarioTurmaGlob.turma = turmaGlob;
		
		diarioUsuario.usuario.nascimento = diarioUsuario.usuario.nascimento + " 00:00:00"; 
		if($('#presenca'+diarioUsuario.usuario.id).is(":checked")){
			diarioUsuario.presenca = "true";
		}else{
			diarioUsuario.presenca = "false";
		}
		diarioUsuario.observacao = $('#observacao'+diarioUsuario.usuario.id).val();
		diarioUsuario.diarioTurma = diarioTurmaGlob;
		diarioUsuariosEnviarGlob.push(diarioUsuario);
		
	});
		
	enviarDiarioTurma();
}


function setDiarioTurma(){
	$("#diarioTurmaId").val(diarioTurmaGlob.id);
	$("#turmaId").val(turmaGlob.id);
	$('#observacoesTurma').val(diarioTurmaGlob.observacoes);	
}

function validaFormDiario(){
	var valida = true;
	
	             if($('#data').val() == "" ||  $('#data').val() == null){
	            	 $('#data').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             
	             if($('#buscaTurma').val() == "" ||  $('#buscaTurma').val() == null){
	            	 $('#buscaTurma').css('background-color','#ffb3b3');
	            	 valida = false;
	             };
	              
	             return valida;
}



function getDiarioTurma(){
	turma = new Object();
	turma.id = "0";
	id = "0";
	if($("#diarioTurmaId").val() != "" && $("#diarioTurmaId").val() != undefined){
		id = $("#diarioTurmaId").val();
	}
	if($("#turmaId").val() != "" && $("#turmaId").val() != undefined){
		turma.id = $("#turmaId").val();
	}
	return{
		"id": id,
		"data": dataGlob+" 00:00:00",
		"observacoes": $('#observacoesTurma').val(),
		"turma": turma
	}
}