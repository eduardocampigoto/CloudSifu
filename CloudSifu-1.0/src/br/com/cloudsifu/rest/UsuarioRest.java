package br.com.cloudsifu.rest;

//-------------------------------|
//Library
//-------------------------------|
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.UsuarioServico;

@Path("/usuario")
public class UsuarioRest extends UtilRest {

	private UsuarioServico usuarioServico;
	
	public UsuarioRest(){
		usuarioServico = new UsuarioServico();
	}
	
	@POST
	@Consumes("application/*")
	public String novoUsuario(String userParam) throws GlobalException {

		try {
			Usuario usuario = getObjectMapper().readValue(userParam,
					Usuario.class);
			usuarioServico.adicionarUsuario(usuario);
		
		}catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
		return this.buildResponse("Usuario adicionado");
	}

	@GET
	@Path("/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public String buscarUsuario(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Usuario busca = new Usuario();
			busca.setNome(nome);
			Usuario usuario = usuarioServico
					.buscarUsuario(busca);
			
			return this.buildResponse(usuario);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Path("/lista/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public String buscarUsuarioLista(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			List<Usuario> listaUsuarios = usuarioServico
					.buscarUsuarioNome(usuario);
			return this.buildResponse(listaUsuarios);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String deletarUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			Usuario usuario = new Usuario();
			usuario.setId(id);
			return this.buildResponse(usuarioServico.deletarUsuario(usuario));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarUsuario(@PathParam("id") int id) throws GlobalException {
		try {
			Usuario usuario = new Usuario();
			usuario.setId(id);
			return this.buildResponse(usuarioServico.buscarUsuario(usuario));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@PUT
	@Consumes("application/*")
	public String editarUsuario(String usuarioEdit) throws GlobalException {
		try {
			Usuario usuario = getObjectMapper().readValue(usuarioEdit,
					Usuario.class);			
			
			return this.buildResponse(usuarioServico.editarUsuario(usuario));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

}// EndClass
