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
import br.com.cloudsifu.objects.EstiloGraduacaoUsuario;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.EstiloGraduacaoUsuarioServico;

@Path("/estiloGraduacaoUsuario")
public class EstiloGraduacaoUsuarioRest extends UtilRest {
	private EstiloGraduacaoUsuario estiloGraduacaoUsuario;
	private EstiloGraduacaoUsuarioServico estiloGraduacaoUsuarioServico;
	
	public EstiloGraduacaoUsuarioRest(){
		estiloGraduacaoUsuario = new EstiloGraduacaoUsuario();
		estiloGraduacaoUsuarioServico = new EstiloGraduacaoUsuarioServico();
	
	}

	@POST
	@Consumes("application/*")
	public String novoEstiloGraduacaoUsuario(String estiloGraduacaoParam)
			throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		estiloGraduacaoUsuario =  getObjectMapper().readValue(estiloGraduacaoParam, EstiloGraduacaoUsuario.class);
		boolean resposta = estiloGraduacaoUsuarioServico.adicionarEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
		if (resposta) {
			return this.buildResponse("EstiloGraduacaoUsuario adicionado");
		} else {
			return this.buildResponse("Falha ao adicionar estiloGraduacaoUsuario");
		}
	}


	
	@GET
	@Path("listaEstiloGraduacaoUsuario/{estiloGraduacaoUsuario}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarListaEstiloGraduacaoUsuario(@PathParam("estiloGraduacaoUsuario") String estiloGraduacaoUsuarioParam)throws GlobalException {
		try {
			estiloGraduacaoUsuario = getObjectMapper().readValue(estiloGraduacaoUsuarioParam, EstiloGraduacaoUsuario.class);
			return this.buildResponse(estiloGraduacaoUsuarioServico.buscarListaEstiloGraduacaoUsuario(estiloGraduacaoUsuario));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String deletarEstiloGraduacaoUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			estiloGraduacaoUsuario.setId(id);
			boolean resposta = estiloGraduacaoUsuarioServico
					.excluirEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
			return this.buildResponse(resposta);
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@GET
	@Path("/usuario/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarEstiloGraduacaoUsuario(@PathParam("id") int id)
			throws GlobalException {
		try {
			EstiloGraduacaoUsuario busca = new EstiloGraduacaoUsuario();
			busca.setUsuario(new Usuario());
			busca.getUsuario().setId(id);
			
			return this.buildResponse(estiloGraduacaoUsuarioServico.buscarEstiloGraduacaoUsuarioId(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/{estiloGraduacaoEdit}")
	@Consumes("application/*")
	public String editarEstiloGraduacaoUsuario(String estiloGraduacaoEdit)
			throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			EstiloGraduacaoUsuario estiloGraduacaoUsuario = new ObjectMapper().readValue(
					estiloGraduacaoEdit, EstiloGraduacaoUsuario.class);
			EstiloGraduacaoUsuarioServico estiloGraduacaoUsuarioServico = new EstiloGraduacaoUsuarioServico();
			boolean resposta = estiloGraduacaoUsuarioServico
					.editarEstiloGraduacaoUsuario(estiloGraduacaoUsuario);
			return this.buildResponse(resposta);
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

}
