function showModal(titulo, mensagem, callback) {

	   $('#dialogDiv').attr('title', titulo);
	   $('#dialogDiv').html(mensagem);
	   $('#dialogDiv').dialog({
	       resizable: false,
	       height: "auto",
	       width: "auto",
	       modal: true,
	       draggable: false,
	       dialogClass: 'main-dialog-class',
	       buttons: {
	           "Sim": function() {
	if(typeof callback === "function"){
	callback.call();
	}			  
	              $(this).dialog('close');
	              $('#dialogDiv').html("");
	              
	           },
	           "Não": function() {
	               $(this).dialog('close');
	           }
	       }

	   });

	}




function showModalOK(titulo, mensagem, callback) {

	   $('#dialogDiv').attr('title', titulo);
	   $('#dialogDiv').html(mensagem);
	   $('#dialogDiv').dialog({
	       resizable: false,
	       height: "auto",
	       width: "auto",
	       modal: true,
	       draggable: false,
	       dialogClass: 'main-dialog-class',
	       buttons: {
	           "OK": function() {
	if(typeof callback === "function"){
	callback.call();
	}			  
	              $(this).dialog('close');
	              $('#dialogDiv').html("");
	              
	           }
	       }

	   });

	}


function showModalSimNao(titulo, mensagem, callback) {

	   $('#dialogDiv').attr('title', titulo);
	   $('#dialogDiv').html(mensagem);
	   $('#dialogDiv').dialog({
	       resizable: false,
	       height: "auto",
	       width: "auto",
	       modal: true,
	       draggable: false,
	       dialogClass: 'main-dialog-class',
	       buttons: {
	           "Sim": function() {
	if(typeof callback === "function"){
	callback.call();
	}			  
	              $(this).dialog('close');
	              $('#dialogDiv').html("");
	              
	           },
	           "Não": function() {
	               $(this).dialog('close');
	           }
	       }

	   });

	}