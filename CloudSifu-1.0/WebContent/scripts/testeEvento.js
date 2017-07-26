$(document).ready(function(){
	data = new Date();
	$('#titulo').val('Titulo evento x');
	$('#data').val(data.getDate()+'/'+(data.getMonth()+1)+'/'+data.getFullYear());
	$('#descricao').val('Descrição do evento x...');
	$('#cep').val('89000000');
	$('#tlogradouro').val('1');
	$('#nlogradouro').val('Rio dos cedros');
	$('#numero').val('20');
	$('#bairro').val('comasa');
	$('#cidade').val('Joinville');
	$('#sUF').val('SC');
	$('#nUF').val('Santa Catarina');
	$('#referencia').val('Proximo a rua y');
});
