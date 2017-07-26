package br.com.cloudsifu.jdbc;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Usuario;

public interface LoginDAO {

		public Usuario validarLogin(Usuario usuario) throws GlobalException;

}
