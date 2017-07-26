package br.com.cloudsifu.jdbc;

import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.EstiloGraduacaoUsuario;

public interface EstiloGraduacaoUsuarioDAO {
	
	public boolean adicionarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario)throws GlobalException;
	public List<EstiloGraduacaoUsuario> buscarEstiloGraduacaoUsuarioId(EstiloGraduacaoUsuario estiloGraduacaoUsuario)throws GlobalException;
	public EstiloGraduacaoUsuario buscarEstiloGraduacaoUsuarioNome(EstiloGraduacaoUsuario estiloGraduacaoUsuario)throws GlobalException;
	public boolean editarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario)throws GlobalException;
	public boolean excluirEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario)throws GlobalException;

}
