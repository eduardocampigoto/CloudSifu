package br.com.cloudsifu.jdbc;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Escola;

public interface EscolaDAO {
	
	public void adicionarEscola(Escola escola) throws GlobalException;	
	public boolean editarEscola(Escola escola) throws GlobalException;
	public Escola buscarEscola(Escola busca) throws GlobalException;
	public List<Escola> buscarEscolaLista(Escola busca) throws GlobalException;
	public Escola buscarEscolaId(Escola busca) throws GlobalException;	
	public boolean excluirEscola(Escola busca) throws GlobalException;
	
}
