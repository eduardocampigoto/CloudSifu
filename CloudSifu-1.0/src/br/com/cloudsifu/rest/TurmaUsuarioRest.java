package br.com.cloudsifu.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Turma;
import br.com.cloudsifu.objects.TurmaUsuario;
import br.com.cloudsifu.services.TurmaUsuarioServico;

@Path("/turmaUsuario")
public class TurmaUsuarioRest extends UtilRest {
	private TurmaUsuarioServico turmaUsuarioServico;

	public TurmaUsuarioRest() {
		turmaUsuarioServico = new TurmaUsuarioServico();

	}

	@POST
	@Consumes("application/*")
	public String novoTurmaUsuario(String turmaUsuarioParam)
			throws GlobalException {
		try {
			TurmaUsuario turmaUsuario = new ObjectMapper().readValue(
					turmaUsuarioParam, TurmaUsuario.class);
			System.out.println(turmaUsuarioParam);
			boolean resposta = turmaUsuarioServico
					.adicionarTurmaUsuario(turmaUsuario);
			if (resposta) {
				return this.buildResponse("TurmaUsuario adicionado");
			} else {
				return this.buildResponse("Falha ao adicionar turmaUsuario");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Path("listaTurmaUsuarios/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarTurmaUsuarios(@PathParam("id") int id)
			throws GlobalException {
		try {
			
			TurmaUsuario busca = new TurmaUsuario();
			busca.setId(id);			
			return this.buildResponse(turmaUsuarioServico.buscarTurmaUsuarios(busca));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}
	
	@GET
	@Path("listaUsuarios/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarListaUsuarios(@PathParam("id") int id)
			throws GlobalException {
		try {
			TurmaUsuario busca = new TurmaUsuario();
			busca.setTurma(new Turma());
			busca.getTurma().setId(id);
			turmaUsuarioServico.buscarTurma(busca);
			return this.buildResponse(turmaUsuarioServico.buscarListaUsuarios(busca));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@PUT
	@Path("/{turmaUsuarioEdit}")
	@Consumes("application/*")
	public String editarTurmaUsuario(String turmaUsuarioEdit)
			throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			TurmaUsuario turmaUsuario = new ObjectMapper().readValue(
					turmaUsuarioEdit, TurmaUsuario.class);
			boolean resposta = turmaUsuarioServico
					.editarTurmaUsuario(turmaUsuario);
			return this.buildResponse(resposta);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@DELETE
	@Path("{id}")
	public String deletarTurmaUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			TurmaUsuario turmaUsuario = new TurmaUsuario();
			turmaUsuario.setId(id);
			boolean resposta = turmaUsuarioServico
					.excluirTurmaUsuario(turmaUsuario);
			return this.buildResponse(resposta);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

}
