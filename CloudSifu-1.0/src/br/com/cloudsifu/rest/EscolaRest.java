package br.com.cloudsifu.rest;

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

import org.codehaus.jackson.map.ObjectMapper;

import br.com.cloudsifu.objects.Escola;
import br.com.cloudsifu.services.EscolaServico;

@Path("/escola")
public class EscolaRest extends UtilRest {

	public EscolaRest() {

	}

	@POST
	@Consumes("application/*")
	public String novaEscola(String escolaParam) {
		try {
			Escola escola = new ObjectMapper().readValue(escolaParam,
					Escola.class);
			EscolaServico escolaServico = new EscolaServico();
			escolaServico.adicionarEscola(escola);
			return this.buildResponse("Escola adicionada");
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEscola(@PathParam("nome") String nome) {

		Escola busca = new Escola();
		busca.setNome(nome);

		try {
			EscolaServico escolaServico = new EscolaServico();
			Escola escola = escolaServico.buscarEscola(busca);

			return this.buildResponse(escola);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Path("/lista/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEscolaLista(@PathParam("nome") String nome) {

		Escola escola = new Escola();
		escola.setNome(nome);

		try {
			EscolaServico escolaServico = new EscolaServico();
			List<Escola> listaEscolas = escolaServico.buscarEscolaLista(escola);

			return this.buildResponse(listaEscolas);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String deletarEscola(@PathParam("id") int id) {
		Escola escola = new Escola();
		escola.setId(id);
		try {

			EscolaServico escolaServico = new EscolaServico();
			boolean excluirEscola = escolaServico.excluirEscola(escola);

			return this.buildResponse(excluirEscola);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEscola(@PathParam("id") int id) {
		Escola escola = new Escola();
		escola.setId(id);
		try {

			EscolaServico escolaServico = new EscolaServico();
			Escola buscarEscolaId = escolaServico.buscarEscolaId(escola);

			return this.buildResponse(buscarEscolaId);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

	@PUT
	@Path("/{escolaEdit}")
	@Consumes("application/*")
	public String editarEscola(String escolaEdit) {

		try {
			Escola escola = new ObjectMapper().readValue(escolaEdit,
					Escola.class);
			EscolaServico escolaServico = new EscolaServico();
			boolean editarEscola = escolaServico.editarEscola(escola);
			return this.buildResponse(editarEscola);

		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}

}
