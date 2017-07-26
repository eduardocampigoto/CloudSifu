package br.com.cloudsifu.jdbc;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Bairro;

public interface BairroDAO {

	public void adicionarBairro(Bairro bairro)throws GlobalException;
	public Bairro buscarBairroId(Bairro bairro)throws GlobalException;
	public boolean editarBairro(Bairro bairro)throws GlobalException;
	public boolean excluirBairro(Bairro bairro)throws GlobalException;
	Bairro buscarBairro(Bairro bairro)throws GlobalException;
	
}
