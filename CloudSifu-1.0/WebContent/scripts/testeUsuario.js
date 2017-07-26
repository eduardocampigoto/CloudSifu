$(document).ready(function(){
	
	$('#btAdicionarEscola').click(function(){
		if($('#escform').attr('display','block') 
		    || $('#escform').attr('display','visible')){	
			$('#escolanome').val('senai');
			$('#escolaresponsavel').val('professor');
			$('#escolatelefone').val('4799999999');
			$('#escolaemail').val('email@escola.com');
			$('#escolacep').val('89228001');
			$('#escolanlogradouro').val('RuaY');
			$('#escolanumero').val('7777');
			$('#escolabairro').val('bairroy');
			$('#escolacidade').val('cidadey');
			$('#escolanUF').val('Santa Morcegada');
		}
	});
	
});
	
function testaAdd() {
	
		$('#nome').val('nomeuser');
		$('#sobrenome').val('SobrenomeUser');
		$('#cpf').val('99999999999');
		$('#rg').val('99999999');
		$('#nascimento').val('19/02/1988');
		$('#tSangue').val('O');
		$("#genero").val('M');
		$('#telefone').val('4799999999');
		$('#celular').val('4799999999');
		$('#contato').val('4799999999');
		$('#email').val('nome@dom.com');
		$('#profissao').val('profissaoUser');
		$('#sysuser').val('nome.sobrenome');
		$('#senha').val('senhausuario');
		$('#conta').val('1');
		$('#descricao').val('descricao do usuario');
		$('#cep').val('89228000');
		//$('#tlogradouro').val('1');
		$('#nlogradouro').val('RuaX');
		$('#numero').val('9999');
		$('#bairro').val('bairroX');
		$('#cidade').val('cidadeX');
		//$('#sUF').val('SC');
		$('#nUF').val('santa tartaruga');

}


