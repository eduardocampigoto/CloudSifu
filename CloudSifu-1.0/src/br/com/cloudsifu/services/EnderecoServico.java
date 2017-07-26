package br.com.cloudsifu.services;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EnderecoDAOJDBC;
import br.com.cloudsifu.objects.Endereco;


public class EnderecoServico {
	
	private EnderecoDAOJDBC jdbcEndereco;
	private BairroServico bairroServico;
	private CidadeServico cidadeServico;
	private UnidadeFederativaServico ufServico;
	
	public EnderecoServico(){
		jdbcEndereco = new EnderecoDAOJDBC();
		bairroServico = new BairroServico();
		cidadeServico = new CidadeServico();
		ufServico = new UnidadeFederativaServico();
	}

	public void adicionarEndereco(Endereco endereco) throws GlobalException {
		ufServico.adicionarUnidadeFederativa(endereco.getBairro().getCidade().getUnidadeFederativa());
		cidadeServico.adicionarCidade(endereco.getBairro().getCidade());
		bairroServico.adicionarBairro(endereco.getBairro());
		jdbcEndereco.adicionarEndereco(endereco);
	}

	public List<Endereco> buscarEnderecoLista(Endereco endereco)throws GlobalException {
		return jdbcEndereco.buscarEnderecoLista(endereco);
	}

	public Endereco buscarEnderecoId(Endereco endereco)throws GlobalException{
		return jdbcEndereco.buscarEnderecoId(endereco);
	}

	public Endereco buscarEndereco(Endereco endereco)throws GlobalException{
		return jdbcEndereco.buscarEndereco(endereco);
	}

	public boolean editarEndereco(Endereco endereco)throws GlobalException {
		return jdbcEndereco.editarEndereco(endereco);
	}

	public boolean excluirEndereco(Endereco endereco)throws GlobalException {
		return jdbcEndereco.excluirEndereco(endereco);
	}

}
