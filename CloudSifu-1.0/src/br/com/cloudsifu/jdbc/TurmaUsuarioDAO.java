package br.com.cloudsifu.jdbc;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.TurmaUsuario;

public interface TurmaUsuarioDAO {

	public boolean adicionarTurmaUsuario(TurmaUsuario turmaUsuario)throws GlobalException;	
	public boolean editarTurmaUsuario(TurmaUsuario turmaUsuario)throws GlobalException;
	public List<TurmaUsuario> buscarTurmaUsuarios(TurmaUsuario turmaUsuario)throws GlobalException;
	public boolean excluirTurmaUsuario(TurmaUsuario turmaUsuario)throws GlobalException;
}
