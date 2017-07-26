package br.com.cloudsifu.services;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.BairroDAOJDBC;
import br.com.cloudsifu.objects.Bairro;

public class BairroServico {
	private BairroDAOJDBC jdbcBairro;
	
	public BairroServico(){
		jdbcBairro = new BairroDAOJDBC();
	}
	
	public void adicionarBairro(Bairro bairro) throws GlobalException{
		Bairro busca = buscarBairro(bairro);
		if( busca == null){
			jdbcBairro.adicionarBairro(bairro);
		}
	}

	public Bairro buscarBairro(Bairro bairro) throws GlobalException{
		return jdbcBairro.buscarBairro(bairro);
	}
	
	public Bairro buscarBairroId(Bairro bairro) throws GlobalException{
		return jdbcBairro.buscarBairroId(bairro);
	}

	public boolean editarBairro(Bairro bairro)throws GlobalException{
		return jdbcBairro.editarBairro(bairro);
	}

	public boolean excluirBairro(Bairro bairro)throws GlobalException{
		return jdbcBairro.excluirBairro(bairro);
	}

}
