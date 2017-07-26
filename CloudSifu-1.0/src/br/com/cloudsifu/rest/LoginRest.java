package br.com.cloudsifu.rest;

//-------------------------------|
//Library
//-------------------------------|
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.LoginServico;

public class LoginRest extends UtilRest{
	
	public LoginRest(){
		
	}
	
	@POST
	@Path("/{usuario}&{senha}")
	@Consumes("application/*")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String fazerLogin(@PathParam("usuario") String login,@PathParam("senha") String senha){
		try{
			LoginServico compara = new LoginServico();
			Usuario usuario = new Usuario();
			usuario.setUsuario(login);
			usuario.setSenha(senha);
			
			if (!compara.validarLogin(usuario).equals(null)){				
				return "alert('Logado');";
				
			}else{
				return "alert(falha ao fazer login);";
			}
			
			
			
			

		}catch(Exception e){
			return "Erro";
		
		}
		
	}
	

}
