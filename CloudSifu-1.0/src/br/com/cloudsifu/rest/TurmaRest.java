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

import org.codehaus.jackson.map.JsonMappingException;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Turma;
import br.com.cloudsifu.services.TurmaServico;

import com.google.gson.JsonParseException;

@Path("/turma")
public class TurmaRest extends UtilRest {

	private TurmaServico turmaServico;
	
	public TurmaRest(){
		turmaServico = new TurmaServico();
	}
	
	@POST
	@Consumes("application/*")
	public String novaTurma(String turmaParam) throws GlobalException,
			org.codehaus.jackson.JsonParseException, JsonMappingException,
			IOException {
		try {
			Turma turma = getObjectMapper().readValue(turmaParam, Turma.class);
			turmaServico.adicionarTurma(turma);
			return this.buildResponse("Turma "+turma.getNome()+" adicionada com sucesso!");
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@GET
	@Path("/nome/{nome}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarTurmaNome(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Turma busca = new Turma();
			busca.setNome(nome);
			return this.buildResponse(turmaServico.buscarTurmaNome(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarTurmaId(@PathParam("id") int id) throws GlobalException {
		try {
			
			Turma busca = new Turma();
			busca.setId(id);
			return this.buildResponse(turmaServico.buscarTurmaId(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}
	
	
	@GET
	@Path("/horario/{horario}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarTurmaHorario(@PathParam("horario") String horario)
			throws GlobalException {
		try {
			Turma turma = new Turma();
			turma.setHorario(horario);		
			
			return this.buildResponse(turmaServico.buscarTurmaHorario(turma));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}
		
	@GET
	@Path("/diaSemana/{diaSemana}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarTurmaDiaSemana(@PathParam("diaSemana") String diaSemana)
			throws GlobalException {
		try {
			Turma busca = new Turma();
			busca.setDiaSemana(diaSemana);
			return this.buildResponse(turmaServico.buscarTurmaDiaSemana(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	@PUT
	@Consumes("application/*")
	public String editarTurma(String turmaEdit) throws JsonParseException,
			JsonMappingException, IOException, GlobalException {
		try {
			Turma turma = getObjectMapper().readValue(turmaEdit, Turma.class);
			return this.buildResponse(turmaServico.editarTurma(turma));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String deletarTurma(@PathParam("id") int id) throws GlobalException {
		try {
			Turma busca = new Turma();
			busca.setId(id);
			return this.buildResponse(turmaServico.excluirTurma(busca));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

}
