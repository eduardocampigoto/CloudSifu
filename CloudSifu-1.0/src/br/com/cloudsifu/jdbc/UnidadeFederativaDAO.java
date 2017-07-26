package br.com.cloudsifu.jdbc;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.UnidadeFederativa;

public interface UnidadeFederativaDAO {

	public void adicionarUF(UnidadeFederativa uf) throws GlobalException;
	public UnidadeFederativa buscarUnidadeFederativa(UnidadeFederativa uf) throws GlobalException;
	public UnidadeFederativa buscarUFId(UnidadeFederativa uf) throws GlobalException;
	public boolean editarUF(UnidadeFederativa uf)  throws GlobalException;
	public boolean excluirUF(UnidadeFederativa uf) throws GlobalException;
	
}
