package br.com.cloudsifu.jdbc;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.DiarioUsuario;

public interface DiarioUsuarioDAO {
	
	public void adicionarListaDiarioUsuario(List<DiarioUsuario> listaAdicionar) throws GlobalException;		
	public boolean editarListaDiarioUsuario(List<DiarioUsuario> listaEditar) throws GlobalException;
	public DiarioUsuario buscarDiarioUsuarioId(DiarioUsuario diarioUsuario) throws GlobalException;
	public List<DiarioUsuario> buscarListaDiarioUsuario(DiarioUsuario diarioUsuario) throws GlobalException;
	public boolean excluirDiarioUsuario(DiarioUsuario diarioUsuario) throws GlobalException;

}
