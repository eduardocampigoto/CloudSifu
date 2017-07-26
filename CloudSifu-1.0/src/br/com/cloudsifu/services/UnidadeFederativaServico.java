package br.com.cloudsifu.services;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.UnidadeFederativaDAOJDBC;
import br.com.cloudsifu.objects.UnidadeFederativa;

public class UnidadeFederativaServico {

	private UnidadeFederativaDAOJDBC jdbcUnidadeFederativa;
	
	public UnidadeFederativaServico() {
		jdbcUnidadeFederativa = new UnidadeFederativaDAOJDBC();
	}

	public void adicionarUnidadeFederativa(UnidadeFederativa uf)
			throws GlobalException {

			jdbcUnidadeFederativa.adicionarUF(uf);
	}

	public UnidadeFederativa buscarUnidadeFederativa(UnidadeFederativa uf)
			throws GlobalException {
		return jdbcUnidadeFederativa.buscarUnidadeFederativa(uf);
	}

	public UnidadeFederativa buscarUnidadeFederativaId(UnidadeFederativa uf)
			throws GlobalException {
		return jdbcUnidadeFederativa.buscarUFId(uf);
	}

	public boolean excluirUF(UnidadeFederativa uf) throws GlobalException {
		return jdbcUnidadeFederativa.excluirUF(uf);
	}

	public boolean editarUF(UnidadeFederativa uf) throws GlobalException {
		return jdbcUnidadeFederativa.editarUF(uf);
	}

}
