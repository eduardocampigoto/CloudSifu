package br.com.cloudsifu.rest;

import java.io.IOException;
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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;


import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.DiarioTurma;
import br.com.cloudsifu.objects.DiarioUsuario;
import br.com.cloudsifu.services.DiarioUsuarioServico;

@Path("/diarioUsuario")
public class DiarioUsuarioRest extends UtilRest {
	
	private DiarioUsuarioServico diarioUsuarioServico;
	
	public DiarioUsuarioRest(){
		diarioUsuarioServico = new DiarioUsuarioServico();
	}

	@POST
	@Consumes("application/*")
	public void novoDiarioUsuario(String diarioUsuarioParam)	throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			List<DiarioUsuario> listaDiarioUsuario= getObjectMapper().readValue(diarioUsuarioParam, getObjectMapper().getTypeFactory().constructCollectionType(List.class, DiarioUsuario.class));
			diarioUsuarioServico.adicionarListaDiarioUsuario(listaDiarioUsuario);
			
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String deletarDiarioUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			DiarioUsuario diarioUsuario = new DiarioUsuario();
			diarioUsuario.setId(id);
			
			boolean resposta = diarioUsuarioServico
					.excluirDiarioUsuario(diarioUsuario);
			return this.buildResponse(resposta);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarDiarioUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			DiarioUsuario busca = new DiarioUsuario();
			busca.setId(id);
			
			DiarioUsuario buscarDiarioUsuarioId = diarioUsuarioServico
					.buscarDiarioUsuario(busca);
			return this.buildResponse(buscarDiarioUsuarioId);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}
	
	@GET
	@Path("/listaDiarioUsuario/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarListaDiarioUsuarios(@PathParam("id") int id)
			throws GlobalException {
		try {
			DiarioUsuario busca = new DiarioUsuario();
			busca.setDiarioTurma(new DiarioTurma());
			busca.getDiarioTurma().setId(id);
			return this.buildResponse(diarioUsuarioServico.buscarListaDiarioUsuario(busca));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@PUT
	@Path("/{diarioUsuarioEdit}")
	@Consumes("application/*")
	public String editarDiarioUsuario(String diarioUsuarioParam)
			throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			List<DiarioUsuario> listaDiarioUsuario= getObjectMapper().readValue( diarioUsuarioParam, getObjectMapper().getTypeFactory().constructCollectionType(List.class, DiarioUsuario.class));
			boolean editarDiarioUsuario = diarioUsuarioServico.editarListaDiarioUsuario(listaDiarioUsuario);
			return this.buildResponse(editarDiarioUsuario);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

}
