package br.com.cloudsifu.jdbc;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Usuario;

public interface UsuarioDAO {
	public void adicionarUsuario(Usuario usuario)throws GlobalException;
	public Usuario buscarUsuario(Usuario busca) throws GlobalException;
	public List<Usuario> buscarUsuarioNome(Usuario usuario)throws GlobalException;
	public Usuario buscarUsuarioId(Usuario usuario)throws GlobalException;
	public boolean editarUsuario(Usuario usuario)throws GlobalException;	
	public boolean removerUsuario(Usuario usuario)throws GlobalException;

}
