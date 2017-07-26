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

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.services.EnderecoServico;

@Path("/endereco")
public class EnderecoRest extends UtilRest {

	@POST
	@Consumes("application/*")
	public String novoEndereco(String enderecoParam) throws GlobalException {

		try {
			Endereco endereco = new ObjectMapper().readValue(enderecoParam,
					Endereco.class);
			EnderecoServico enderecoServico = new EnderecoServico();

			enderecoServico.adicionarEndereco(endereco);
			return this.buildResponse("Endereço adicionado com sucesso");

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEnderecoNome(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Endereco endereco = new Endereco();
			EnderecoServico enderecoServico = new EnderecoServico();
			endereco.setNome(nome);
			Endereco busca = enderecoServico.buscarEndereco(endereco);
			return this.buildResponse(busca);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Path("/lista/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEnderecoLista(@PathParam("nome") String nome)
			throws GlobalException {
		try {
			Endereco endereco = new Endereco();
			EnderecoServico enderecoServico = new EnderecoServico();
			endereco.setNome(nome);

			List<Endereco> listaEnderecos = enderecoServico
					.buscarEnderecoLista(endereco);
			return this.buildResponse(listaEnderecos);

		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String deletarEndereco(@PathParam("id") int id)
			throws GlobalException {
		try {
			Endereco endereco = new Endereco();
			EnderecoServico enderecoServico = new EnderecoServico();
			endereco.setId(id);
			boolean excluirEndereco = enderecoServico.excluirEndereco(endereco);
			return this.buildResponse(excluirEndereco);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String buscarEndereco(@PathParam("id") int id)
			throws GlobalException {
		try {
			Endereco endereco = new Endereco();
			EnderecoServico enderecoServico = new EnderecoServico();
			endereco.setId(id);
			Endereco buscarEnderecoId = enderecoServico
					.buscarEndereco(endereco);
			return this.buildResponse(buscarEnderecoId);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(e.getMessage());
		}

	}

	@PUT
	@Path("/{graduacaoEdit}")
	@Consumes("application/*")
	public String editarEndereco(String enderecoEdit) {
		try {
			EnderecoServico enderecoServico = new EnderecoServico();

			Endereco endereco = new ObjectMapper().readValue(enderecoEdit,
					Endereco.class);
			boolean editarEndereco = enderecoServico.editarEndereco(endereco);
			return this.buildResponse(editarEndereco);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildResponse(e.getMessage());
		}
	}

}
