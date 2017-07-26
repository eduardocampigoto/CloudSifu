var pagina = ""
$(document).ready(function(){
	
	
});

function carregarPagina(pagina, callback){
	
	$.ajax({
		url: pagina,
		success:function(pagina){
			$('#centerBarId').html(pagina);
			if(typeof callback === "function"){
				callback.call();
			}
			
		},error:function(){
			
		}
	});
	
}


function carregarHtml(pagina, barra){
	
	$.ajax({
		url: barra,
		success:function(barra){
			$('#topBarId').html(barra);
		
			$.ajax({
				url: pagina,
				success:function(pagina){
					$('#centerBarId').html(pagina);					
				},error:function(){
					
				}
			});		
		},error:function(){
			
		}
	});
}	

	function carregarNavBar(barra){
		
		$.ajax({
			url: barra,
			success:function(barra){
				$("#topBarId").html(barra);
			},error:function(){
				
			}
		});
		
	
}