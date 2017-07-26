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

import org.codehaus.jackson.map.ObjectMapper;
import br.com.cloudsifu.objects.Estilo;
import br.com.cloudsifu.services.EstiloServico;

@Path("/estilo")
public class EstiloRest extends UtilRest {

	private EstiloServico estiloServico;
	public EstiloRest() {
		estiloServico = new EstiloServico();
	}

	@POST
	@Consumes("application/*")
	public String novoEstilo(String estiloParam) {
		try {
			Estilo estilo = new ObjectMapper().readValue(estiloParam,
					Estilo.class);
			estiloServico.adicionarEstilo(estilo);
			return this.buildResponse("Estilo "+estilo.getNome()+" adicionado");

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Path("/nome/{nome}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarEstiloNome(@PathParam("nome") String nome) {
		try {
			Estilo estilo = new Estilo();
			estilo.setNome(nome);
			return this.buildResponse(estiloServico.buscarEstiloNome(estilo));

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}


	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarEstilo(@PathParam("id") int id) {
		try {
			Estilo estilo = new Estilo();
			estilo.setId(id);
			return this.buildResponse(estiloServico.buscarEstiloId(estilo));

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@PUT
	@Consumes("application/*")
	public String editarEstilo(String estiloEdit) {
		try {
			Estilo estilo = new ObjectMapper().readValue(estiloEdit,
					Estilo.class);
			return this.buildResponse(estiloServico.editarEstilo(estilo));

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}
	
	
	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String deletarEstilo(@PathParam("id") int id) {
		try {
			Estilo estilo = new Estilo();
			estilo.setId(id);
			return this.buildResponse(estiloServico.excluirEstilo(estilo));

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}


}
