var usuario = "";
$(document).ready(function() {
	$(document).on('click', '#tipoContaLista li a', function () {
		var cod = "0";
        var selecao = $(this).text();
        $('#tipoConta').html(selecao + '<span class="caret"</span>');
        if(selecao == "Administrador"){
        	cod = 1;
        }else if(selecao == "Professor"){
        	cod = 2;
        }else{
        	cod= 3;
        }
        $('#conta').val(cod);
    });
	
	
	$('.cadUser').focus(function(){
		$(this).css('background-color', '#FFFFFF');
	});
});
   



function setUsuario(usuario) {
     this.usuario = usuario;
     console.log(usuario.escola);
     if(usuario.tipoConta == "1"){
    	 $('#tipoConta').html("Administrador");
     }else if(usuario.tipoConta == "2"){
    	 $('#tipoConta').html("Professor");
     }else{
    	 $('#tipoConta').html("Aluno");
     }
    
    $('#btAdicionarUsuario').hide();
    $('#btEditarUsuario').show();
    $('#btExcluirUsuario').show();
    $('#userId').val(usuario.id);
    $('#nome').val(usuario.nome);
    $('#sobrenome').val(usuario.sobrenome);
    $('#cpf').val(usuario.cpf);
    $('#rg').val(usuario.rg);
    $('#nascimento').val(usuario.nascimento);
    $('#tSangue').val(usuario.tipoSangue);
    $("#genero").val(usuario.genero);
    $('#telefone').val(usuario.telefone);
    $('#celular').val(usuario.celular);
    $('#contato').val(usuario.contato);
    $('#email').val(usuario.email);
    $('#profissao').val(usuario.profissao);
    $('#sysuser').val(usuario.usuario);
    $('#senha').val(usuario.senha);
    $('#cSenha').val(usuario.senha);
    $('#conta').val(usuario.tipoConta);
    $('#descricao').val(usuario.descricao);
    $('#userEnderecoId').val(usuario.endereco.id);
    $('#cep').val(usuario.endereco.cep);
   // $('#tlogradouro').val(usuario.endereco.logradouro);
    $('#nlogradouro').val(usuario.endereco.nome);
    $('#numero').val(usuario.endereco.numero);
    $('#bairro').val(usuario.endereco.bairro.nome);
    $('#cidade').val(usuario.endereco.bairro.cidade.nome);
   // $('#sUF').val(usuario.endereco.bairro.cidade.unidadeFederativa.sigla);
    $('#nUF').val(usuario.endereco.bairro.cidade.unidadeFederativa.nome);
    if (usuario.escola != null) {
        $('#escform').show();
        $('#escolaId').val(usuario.escola.id);
        $('#escolaEnderecoId').val(usuario.escola.endereco.id);
        $('#escolanome').val(usuario.escola.nome);
        $('#escolaresponsavel').val(usuario.escola.responsavel);
        $('#escolatelefone').val(usuario.escola.telefone);
        $('#escolaemail').val(usuario.escola.endereco.email);
        $('#escolacep').val(usuario.escola.endereco.cep);
       // $('#escolatlogradouro').val(usuario.escola.endereco.logradouro);
        $('#escolareferencia').val(usuario.escola.endereco.referencia);
        $('#escolaEmail').val(usuario.escola.endereco.email);
        $('#escolanlogradouro').val(usuario.escola.endereco.nome);
        $('#escolanumero').val(usuario.escola.endereco.numero);
        $('#escolabairro').val(usuario.escola.endereco.bairro.nome);
        $('#escolacidade').val(usuario.escola.endereco.bairro.cidade.nome);
       // $('#escolasUF').val(usuario.escola.endereco.bairro.cidade.unidadeFederativa.sigla);
        $('#escolanUF').val(usuario.escola.endereco.bairro.cidade.unidadeFederativa.nome);

    } else {
        $('#escform').css('display', 'none');
        $('#escolanome').val(null);
        $('#escolaresponsavel').val(null);
        $('#escolatelefone').val(null);
        $('#escolaemail').val(null);
        $('#escolacep').val(null);
        //$('#escolatlogradouro').val(null);
        $('#escolanlogradouro').val(null);
        $('#escolanumero').val(null);
        $('#escolabairro').val(null);
        $('#escolacidade').val(null);
        //$('#escolasUF').val(null);	
        $('#escolanUF').val(null);
    }
    

}

$('#btAdicionarEscola').click(function() {
    $('#escform').toggle('closed');
});


function removePrefixoTel(valor){
	numero = valor.replace('(','');
	numero = numero.replace(')','');
	numero = numero.replace(' ','');
	return numero;	
}

function getUsuario() {
	
	 var telefone = removePrefixoTel($('#telefone').val());
     var celular = removePrefixoTel($('#celular').val());
     var contato =removePrefixoTel($('#contato').val());
     var telefoneEscola = removePrefixoTel($('#escolatelefone').val());
    if ($('#escform').css('display') == 'block'){ 
    	        return {
            "id": $('#userId').val(),
            "nome": $('#nome').val(),
            "sobrenome": $('#sobrenome').val(),
            "rg": $('#rg').val(),
            "cpf": $('#cpf').val(),
            "nascimento": $('#nascimento').val() + " 00:00:00",
            "tipoSangue": $('#tSangue').val(),
            "genero": $("#genero").val(),
            "telefone": telefone,
            "celular":celular,
            "contato": contato ,
            "email": $('#email').val(),
            "profissao": $('#profissao').val(),
            "usuario": $('#sysuser').val(),
            "senha": $('#senha').val(),
            "tipoConta": $('#conta').val(),
            "descricao": $('#descricao').val(),

            "endereco": {
                "id": $("#userEnderecoId").val(),
                "cep": $('#cep').val(),
                "nome": $('#nlogradouro').val(),
                "numero": $('#numero').val(),
                "referencia": $('#referencia').val(),
                "bairro": {
                    "nome": $('#bairro').val(),
                    "cidade": {
                        "nome": $('#cidade').val(),
                        "unidadeFederativa": {
                            "nome": $('#nUF').val()
                        }
                    }
                }
            },
            "escola": {
                "id": $('#escolaId').val(),
                "nome": $('#escolanome').val(),
                "responsavel": $('#escolaresponsavel').val(),
                "telefone": telefoneEscola,
                "email": $('#escolaemail').val(),
                "endereco": {
                    "id": $('#escolaEnderecoId').val(),
                    "cep": $('#escolacep').val(),
                    "nome": $('#escolanlogradouro').val(),
                    "numero": $('#escolanumero').val(),
                    "referencia": $('#escolareferencia').val(),
                    "bairro": {
                        "nome": $('#escolabairro').val(),
                        "cidade": {
                            "nome": $('#escolacidade').val(),
                            "unidadeFederativa": {
                                "nome": $('#escolanUF').val()
                            }
                        }
                    }
                }
            }
        }
    } else {

    	return {
            "id": $('#userId').val(),
            "nome": $('#nome').val(),
            "sobrenome": $('#sobrenome').val(),
            "rg": $('#rg').val(),
            "cpf": $('#cpf').val(),
            "nascimento": $('#nascimento').val() + " 00:00:00",
            "tipoSangue": $('#tSangue').val(),
            "genero": $("#genero").val(),
            "telefone": telefone,
            "celular": celular,
            "contato": contato,
            "email": $('#email').val(),
            "profissao": $('#profissao').val(),
            "usuario": $('#sysuser').val(),
            "senha": $('#senha').val(),
            "tipoConta": $('#conta').val(),
            "descricao": $('#descricao').val(),

            "endereco": {
                "id": $("#userEnderecoId").val(),
                "cep": $('#cep').val(),
                "nome": $('#nlogradouro').val(),
                "numero": $('#numero').val(),
                "referencia": $('#referencia').val(),
                "bairro": {
                    "nome": $('#bairro').val(),
                    "cidade": {
                        "nome": $('#cidade').val(),
                        "unidadeFederativa": {
                            "nome": $('#nUF').val()
                        }
                    }
                }
            },
            "escola": null
        }    	
    }
}

function validaCamposUsuario() {

    validados = true;

    if ($('#nome').val().length < 2) {
        $('#nome').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#nome').css('background-color', '#FFFFFF');
    }

    if ($('#sobrenome').val().length < 2) {
        $('#sobrenome').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#sobrenome').css('background-color', '#FFFFFF');
    }

    if ($('#rg').val().length < 7) {
        $('#rg').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#rg').css('background-color', '#FFFFFF');
    }

    if($('#cpf').val().length < 8) {
        $('#cpf').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#cpf').css('background-color', '#FFFFFF');
    }

    if ($('#nascimento').val().length != 10) {
        $('#nascimento').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#nascimento').css('background-color', '#FFFFFF');
    }
    
    if ($('#senha').val() != $('#cSenha').val()) {
        $('#senha, #cSenha').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#senha, #cSenha').css('background-color', '#FFFFFF');
    }

    if ($('#telefone').val().length < 8) {
        $('#telefone').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#telefone').css('background-color', '#FFFFFF');
    }

    if ($('#conta').val() == undefined || $('#conta').val() == "" ) {
        $('#tipoConta').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#tipoConta').css('background-color', '#FFFFFF');
    }
    
    if ($('#cep').val().length != 8) {
        $('#cep').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#cep').css('background-color', '#FFFFFF');
    }
    
    if ($('#nlogradouro').val().length < 2) {
        $('#nlogradouro').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#nlogradouro').css('background-color', '#FFFFFF');
    }
    
    
    if ($('#bairro').val().length < 2) {
        $('#bairro').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#bairro').css('background-color', '#FFFFFF');
    }

    if ($('#cidade').val().length < 2) {
        $('#cidade').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#cidade').css('background-color', '#FFFFFF');
    }


    if ($('#nUF').val().length < 2) {
        $('#nUF').css('background-color', '#FF9966');
        $('#nUF').css('background-color', '#FF9966');
        validados = false;
    }else{
    	$('#nUF').css('background-color', '#FFFFFF');
    }

    if ($('#escform').css('display') == 'block'){
        
    	if ($('#escolacep').val().length != 8) {
            $('#escolacep').css('background-color', '#FF9966');
            validados = false;
        }else{
        	$('#escolacep').css('background-color', '#FFFFFF');
        }
      
            
        if ($('#escolanlogradouro').val().length < 2) {
            $('#escolanlogradouro').css('background-color', '#FF9966');
            validados = false;
        }else{
        	$('#escolanlogradouro').css('background-color', '#FFFFFF');
        }
        
        
        if ($('#escolabairro').val().length < 2) {
            $('#escolabairro').css('background-color', '#FF9966');
            validados = false;
        }else{
        	$('#escolabairro').css('background-color', '#FFFFFF');
        }

        if ($('#escolacidade').val().length < 2) {
            $('#escolacidade').css('background-color', '#FF9966');
            validados = false;
        }else{
        	$('#escolacidade').css('background-color', '#FFFFFF');
        }


        if ($('#escolanUF').val().length < 2) {
            $('#escolanUF').css('background-color', '#FF9966');
            $('#escolanUF').focus().css('background-color', '#FFFFFF');
            validados = false;
       
        }else{
        	$('#escolanUF').css('background-color', '#FFFFFF');
        }

    }
    return validados;
}