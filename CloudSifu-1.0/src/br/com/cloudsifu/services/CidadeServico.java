package br.com.cloudsifu.services;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.CidadeDAOJDBC;
import br.com.cloudsifu.objects.Cidade;

public class CidadeServico {
	private CidadeDAOJDBC jdbcCidade;

	public CidadeServico() {
		jdbcCidade = new CidadeDAOJDBC();
	}

	public void adicionarCidade(Cidade cidade) throws GlobalException {
		Cidade busca =  buscarCidade(cidade);
		if (busca == null) {
			jdbcCidade.adicionarCidade(cidade);
		}
	}

	public Cidade buscarCidade(Cidade cidade) throws GlobalException {
		return jdbcCidade.buscarCidade(cidade);
	}

	public Cidade buscarCidadeId(Cidade cidade) throws GlobalException {
		return jdbcCidade.buscarCidadeId(cidade);
	}

	public boolean editarCidade(Cidade cidade) throws GlobalException {
		return jdbcCidade.editarCidade(cidade);
	}

	public boolean excluirCidade(Cidade cidade) throws GlobalException {
		return jdbcCidade.excluirCidade(cidade);
	}

}
