package br.com.cloudsifu.jdbc;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Cidade;


public interface CidadeDAO {

	public void adicionarCidade(Cidade cidade) throws GlobalException;
	public Cidade buscarCidade(Cidade cidade) throws GlobalException;
	public Cidade buscarCidadeId(Cidade cidade) throws GlobalException;
	public boolean editarCidade(Cidade cidade) throws GlobalException;
	public boolean excluirCidade(Cidade cidade) throws GlobalException;
	
}
