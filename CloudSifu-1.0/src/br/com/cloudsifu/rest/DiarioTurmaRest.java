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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.DiarioTurma;
import br.com.cloudsifu.services.DiarioTurmaServico;

@Path("/diarioTurma")
public class DiarioTurmaRest extends UtilRest {
	 private DiarioTurmaServico diarioTurmaServico;
	 
	public DiarioTurmaRest() {
		diarioTurmaServico = new DiarioTurmaServico();

	}

	@POST
	@Consumes("application/*")
	public void novoDiarioTurma(String diarioTurmaParam)throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			DiarioTurma diarioTurma = getObjectMapper().readValue(diarioTurmaParam, DiarioTurma.class);
			diarioTurmaServico.adicionarDiarioTurma(diarioTurma);
						
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@POST
	@Path("/data/")
	@Consumes("application/*")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarDiarioTurmaData(String diarioTurmaParam)throws GlobalException {
		try {
			DiarioTurma diarioTurma = getObjectMapper().readValue(diarioTurmaParam, DiarioTurma.class);
			return this.buildResponse(diarioTurmaServico.buscarDiarioTurmaData(diarioTurma));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String excluirDiarioTurma(@PathParam("id") int id)
			throws GlobalException {
		try {
			DiarioTurma busca = new DiarioTurma();
			busca.setId(id);
			return this.buildResponse(diarioTurmaServico.excluirDiarioTurma(busca));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarDiarioTurma(@PathParam("id") int id)
			throws GlobalException {
		try {
			DiarioTurma busca = new DiarioTurma();
			busca.setId(id);
			return this.buildResponse(diarioTurmaServico.buscarDiarioTurmaId(busca));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@PUT
	@Consumes("application/*")
	public String editarDiarioTurma(String diarioTurmaEdit)
			throws GlobalException, JsonParseException, JsonMappingException,
			IOException {
		try {
			DiarioTurma diarioTurma = getObjectMapper().readValue(
					diarioTurmaEdit, DiarioTurma.class);
			return this.buildResponse(diarioTurmaServico.editarDiarioTurma(diarioTurma));
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}
}
