package br.com.cloudsifu.rest;

//-------------------------------|
//Library
//-------------------------------|
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Graduacao;
import br.com.cloudsifu.services.GraduacaoServico;

@Path("/graduacao")
public class GraduacaoRest extends UtilRest {
	private GraduacaoServico servicoGraduacao;
	
	public GraduacaoRest(){
		servicoGraduacao = new GraduacaoServico();
	}

	@POST
	@Consumes("application/*")
	public String adicionarGraduacao(String graduaParam)
			throws GlobalException, org.codehaus.jackson.JsonParseException,
			JsonMappingException, IOException {
		try {
			Graduacao graduacao = new ObjectMapper().readValue(graduaParam,
					Graduacao.class);
			servicoGraduacao.adicionarGraduacao(graduacao);
			return this.buildResponse("Graduacao"+graduacao.getNome()+" adicionada");
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@GET
	@Path("/nome/{nome}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarGraduacaoNome(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Graduacao busca = new Graduacao();
			busca.setDescricao(nome);
			busca.setNome(nome);
			return this.buildResponse(servicoGraduacao.buscarGraduacaoNome(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarGraduacao(@PathParam("param") String param)
			throws GlobalException {
		try {
			return this.buildResponse(servicoGraduacao.buscarGraduacao(param));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	

	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarGraduacao(@PathParam("id") int id)
			throws GlobalException {
		try {
			Graduacao busca = new Graduacao();
			busca.setId(id);
			return this.buildResponse(servicoGraduacao.buscarGraduacaoId(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@PUT
	@Consumes("application/*")
	public String editarGraduacao(String graduacaoEdit) throws GlobalException,
			org.codehaus.jackson.JsonParseException, JsonMappingException,
			IOException {
		try {
			Graduacao graduacao = new ObjectMapper().readValue(graduacaoEdit,
					Graduacao.class);
			return this.buildResponse(servicoGraduacao.editarGraduacao(graduacao));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}
	
	@DELETE
	@Path("id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String deletarGraduacao(@PathParam("id") int id)
			throws GlobalException {
		try {
			Graduacao graduacao = new Graduacao();
			graduacao.setId(id);
			return this.buildResponse(servicoGraduacao.excluirGraduacao(graduacao));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

}
