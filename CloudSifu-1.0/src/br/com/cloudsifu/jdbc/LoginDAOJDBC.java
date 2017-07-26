package br.com.cloudsifu.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.objects.Escola;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.EnderecoServico;
import br.com.cloudsifu.services.EscolaServico;
import br.com.cloudsifu.test.JDBCTeste;

public class LoginDAOJDBC implements LoginDAO{
	
	private AcessaDB acessaDB;
	private EnderecoServico enderecoServico;
	private EscolaServico escolaServico;
	private JDBCTeste   jdbcTeste;
	
	public	LoginDAOJDBC(){
		acessaDB = new AcessaDB();
		enderecoServico = new EnderecoServico();
		jdbcTeste = new JDBCTeste();
	}
	
	private Usuario montarUsuarioExistente(Usuario usuario, String metodo) throws GlobalException, ParseException{
		usuario.setEndereco(enderecoServico.buscarEnderecoId(usuario.getEndereco()));
		
	if(usuario.getEscola() != null){			
 	//	usuario.setEscola(escolaServico.buscarEscolaId(usuario.getEscola()));
 	}
	java.util.Date novaData = new java.util.Date(usuario.getNascimento().getTime());	
	usuario.setNascimento(novaData);
 	return usuario;
}

public Usuario validarLogin(Usuario login) throws GlobalException{
 
	Usuario usuario = null;
	String comando = "SELECT * FROM Usuario WHERE Usuario.usuario='"+login.getUsuario()+"' "
			+" AND Usuario.senha='"+login.getSenha()+"'";
	jdbcTeste.mostraQuerie(comando, "LoginJDBC", "validarLogin");
	
	try {
		
		ResultSet rs = acessaDB.consulta(comando);
		while (rs.next()) {
			usuario  = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSobrenome(rs.getString("sobrenome"));
			usuario.setNascimento(Date.valueOf(rs.getString("nascimento")));
			usuario.setEmail(rs.getString("email"));
			usuario.setRg(rs.getLong("rg"));
			usuario.setCpf(rs.getLong("cpf"));
			usuario.setGenero(rs.getString("genero"));
			usuario.setTipoConta(rs.getInt("tipoConta"));
			usuario.setCelular(rs.getLong("celular"));
			usuario.setTelefone(rs.getLong("telefone"));
			usuario.setContato(rs.getString("contato"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setProfissao(rs.getString("profissao"));
			usuario.setTipoSangue(rs.getString("tipoSangue"));
			usuario.setDescricao(rs.getString("descricao"));
			usuario.setEndereco(new Endereco());
			usuario.getEndereco().setId(rs.getInt("endereco"));
			if(rs.getInt("escola") != 0){
				usuario.setEscola(new Escola());
				usuario.getEscola().setId(rs.getInt("escola"));
			}else{
				usuario.setEscola(null);
			}
			
		}
		acessaDB.fechaConexao();
		if(usuario != null){
			return montarUsuarioExistente(usuario, "buscarUsuarioLogin");
		}else{
			return usuario;
		}

	} catch (Throwable e) {

		e.printStackTrace();
		throw new GlobalException("validar login", e);
	} 
	
	
}
 

}


