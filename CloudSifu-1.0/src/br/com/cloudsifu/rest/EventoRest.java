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
import br.com.cloudsifu.objects.Evento;
import br.com.cloudsifu.services.EventoServico;

@Path("/evento")
public class EventoRest extends UtilRest {
	
	private EventoServico eventoServico;
	
	public EventoRest(){
		eventoServico = new EventoServico();
	}
	
	@POST
	@Consumes("application/*")
	public String novoEvento(String eventoParam) throws GlobalException,
			JsonParseException, JsonMappingException, IOException {
		try {
			Evento evento = getObjectMapper().readValue(eventoParam,
					Evento.class);
			eventoServico.adicionarEvento(evento);
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
		return this.buildResponse("Evento adicionado");
	}

	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarEventoId(@PathParam("id") int id) throws GlobalException {
		try {
			Evento busca = new Evento();
			busca.setId(id);
			return this.buildResponse(eventoServico.buscarEventoId(busca));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}	
	
	@GET
	@Path("/data/{data}")
	@Produces({MediaType.APPLICATION_JSON })
	public String buscarEventoData(@PathParam("data") String datastr) throws GlobalException {
		try {
					
			return this.buildResponse(eventoServico.buscarEventoData(datastr));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
		
	}

	@GET
	@Path("/titulo/{titulo}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarEventoTitulo(@PathParam("titulo") String titulo)throws GlobalException {
		try {
			Evento busca = new Evento();
			busca.setTitulo(titulo);
			return this.buildResponse(eventoServico.buscarEvento(busca));
		}catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}	
	
	@PUT
	@Consumes("application/*")
	public String editarEvento(String eventoEdit) throws JsonParseException,
			JsonMappingException, IOException, GlobalException {

		try {
			Evento evento = getObjectMapper().readValue(eventoEdit,
					Evento.class);
			return this.buildResponse(eventoServico.editarEvento(evento));
			
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}

	}
	
	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public String excluirEvento(@PathParam("id") int id) throws GlobalException {
		try {
			Evento evento = new Evento();
			evento.setId(id);
			return this.buildResponse(eventoServico.excluirEvento(evento));
		} catch (Throwable e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

	
}


