package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.UsuarioDAO;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.objects.Escola;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.EnderecoServico;
//import br.com.cloudsifu.safe.CriptoDecode;
//import br.com.cloudsifu.safe.CriptoEncode;
import br.com.cloudsifu.services.EscolaServico;
import br.com.cloudsifu.test.JDBCTeste;
//import br.com.cloudsifu.test.UsuarioTeste;

public class UsuarioDAOJDBC implements UsuarioDAO {

	private Connection connect;
	private AcessaDB acessaDB;
	private EnderecoServico enderecoServico;
	private EscolaServico escolaServico;
	private	JDBCTeste	jdbcTeste;
	private DateConvert convert;
	public UsuarioDAOJDBC() {
		acessaDB = new AcessaDB();
		convert = new DateConvert();
		connect = acessaDB.criaConexao();
		enderecoServico = new EnderecoServico();
		escolaServico = new EscolaServico();
		jdbcTeste = new JDBCTeste();
		
	}
	
	private Usuario montarNovoUsuario(Usuario usuario, String metodo) throws GlobalException{
	 if(usuario != null){
		 	usuario.setEndereco(enderecoServico.buscarEnderecoId(usuario.getEndereco()));
		 if (usuario.getEscola() != null) { 
			escolaServico.adicionarEscola(usuario.getEscola());
			usuario.setEscola(escolaServico.buscarEscola(usuario.getEscola()));
		}
	 }
	 	return usuario;
	} 
	
	private Usuario montarUsuarioExistente(Usuario usuario, String metodo) throws GlobalException, ParseException{
			usuario.setEndereco(enderecoServico.buscarEnderecoId(usuario.getEndereco()));
			
		if(usuario.getEscola() != null){			
	 		usuario.setEscola(escolaServico.buscarEscolaId(usuario.getEscola()));
	 	}
		java.util.Date novaData = new java.util.Date(usuario.getNascimento().getTime());	
		usuario.setNascimento(novaData);
	 	return usuario;
	}

	public void adicionarUsuario(Usuario usuario) throws GlobalException {
		enderecoServico.adicionarEndereco(usuario.getEndereco());
		usuario.setEndereco(enderecoServico.buscarEndereco(usuario.getEndereco()));
		montarNovoUsuario(usuario, "adicionarUsuario");
	 	
		String comando = "INSERT INTO `Usuario` "
				+ "(tipoconta, nome, sobrenome, cpf, rg,"
				+ " tipoSangue, nascimento, email, telefone, celular, contato, usuario, senha, genero,"
				+ " profissao, descricao, endereco";
		comando += usuario.getEscola() == null ? "" : ", escola";
		comando += ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		comando += usuario.getEscola() == null ? "" : ",?";
		comando += ")";
		jdbcTeste.mostraQuerie(comando, "UsuarioJDBC", "adicionarUsuario");
		Date sqlDate = convert.converteParaSqlDate(usuario.getNascimento());
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setInt(1, usuario.getTipoConta());
			p.setString(2, usuario.getNome());
			p.setString(3, usuario.getSobrenome());
			p.setLong(4, usuario.getCpf());
			p.setLong(5, usuario.getRg());
			p.setString(6, usuario.getTipoSangue());
			p.setDate(7, sqlDate);
			p.setString(8, usuario.getEmail());
			p.setLong(9, usuario.getTelefone());
			p.setLong(10, usuario.getCelular());
			p.setString(11, usuario.getContato());
			p.setString(12, usuario.getUsuario());
			p.setString(13, usuario.getSenha());
			p.setString(14, usuario.getGenero());
			p.setString(15, usuario.getProfissao());
			p.setString(16, usuario.getDescricao());
			p.setInt(17, usuario.getEndereco().getId());
			if (usuario.getEscola() != null) {
				p.setInt(18, usuario.getEscola().getId());
			}
			
			p.execute();
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" ao adicionar usuario", e);

		}
	}

	public Usuario buscarUsuarioId(Usuario busca) throws GlobalException {
		Usuario usuario = null;
		String comando = "SELECT * FROM Usuario WHERE id ='" + busca.getId()+"'";
		jdbcTeste.mostraQuerie(comando, "UsuarioJDBC", "buscarUsuarioId");
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				usuario = new Usuario();
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
				usuario.setSenha(rs.getString("senha"));
				usuario.setProfissao(rs.getString("profissao"));
				usuario.setTipoSangue(rs.getString("tipoSangue"));
				usuario.setDescricao(rs.getString("descricao"));
				usuario.setEndereco(new Endereco());
				usuario.getEndereco().setId(rs.getInt("endereco"));
				if(rs.getInt("escola") != 0){ 
					usuario.setEscola(new Escola());
					usuario.getEscola().setId(rs.getInt("escola"));
				}
				
			}
			
			acessaDB.fechaConexao();
			return montarUsuarioExistente(usuario, "buscarUsuarioId");
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar usuário por id.", e);
		}
	}

	public Usuario buscarUsuario(Usuario busca) throws GlobalException {
		Usuario usuario = null;
		String comando = "SELECT * FROM Usuario WHERE Usuario.nome LIKE '"
				+ busca.getNome() + "%'";
		comando += " OR Usuario.sobrenome LIKE '" + busca.getSobrenome() + "%'";
		comando += " OR  Usuario.cpf LIKE '" + busca.getCpf() + "%'";
		comando += " OR Usuario.rg LIKE '" + busca.getRg() + "%'";
		jdbcTeste.mostraQuerie(comando, "UsuarioJDBC", "buscarUsuario");
		
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
				usuario.setSenha(rs.getString("senha"));
				usuario.setProfissao(rs.getString("profissao"));
				usuario.setTipoSangue(rs.getString("tipoSangue"));
				usuario.setDescricao(rs.getString("descricao"));
				usuario.setEndereco(new Endereco());
				usuario.getEndereco().setId(rs.getInt("endereco"));
				if(rs.getInt("escola") != 0){
					usuario.setEscola(new Escola());
					usuario.getEscola().setId(rs.getInt("escola"));
				}
				
			}

			return montarUsuarioExistente(usuario, "buscarUsuario");

		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException("buscar usuário por id.", e);
		} finally {
			acessaDB.fechaConexao();
		}
	}


		
	public List<Usuario> buscarUsuarioNome(Usuario usuario)
			throws GlobalException {
		
		List<Usuario> usuarioLista = null;
		String comando = "SELECT * FROM Usuario";
		if (!usuario.equals("null") && !usuario.equals("")) {
			comando += " WHERE tipoConta LIKE '%" + usuario.getNome() + "%' "
					+ "OR nome LIKE '%" + usuario.getNome() + "%' "
					+ "OR sobrenome LIKE '%" + usuario.getNome() + "%' ";
			jdbcTeste.mostraQuerie(comando, "UsuarioJDBC", "buscarUsuarioNome");
		}

		try {
			
			ResultSet rs = acessaDB.consulta(comando);
			if(!rs.wasNull()){
				usuarioLista = new ArrayList<Usuario>();
			}
			while (rs.next()) {
				usuario = new Usuario();
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
				usuario.setSenha(rs.getString("senha"));
				usuario.setProfissao(rs.getString("profissao"));
				usuario.setTipoSangue(rs.getString("tipoSangue"));
				usuario.setDescricao(rs.getString("descricao"));
				usuario.setEndereco(new Endereco());
				usuario.getEndereco().setId(rs.getInt("endereco"));
				if(rs.getInt("escola") != 0){
					usuario.setEscola(new Escola());
					usuario.getEscola().setId(rs.getInt("escola"));
				}
				usuarioLista.add(montarUsuarioExistente(usuario, "buscarUsuarioNome"));
				
			}
			acessaDB.fechaConexao();
			return usuarioLista;

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar usuário por nome.", e);
		}

	}

	public boolean removerUsuario(Usuario usuario) throws GlobalException {

		String comando = "DELETE  FROM Usuario WHERE id =" + usuario.getId();
		jdbcTeste.mostraQuerie(comando, "UsuarioJDBC", "removerUsuario");
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException("remover usuário.", e);
		}
		return true;
	}

	public boolean editarUsuario(Usuario usuario) throws GlobalException {
		String comando = "UPDATE Usuario SET tipoConta=?, nome=?, sobrenome=?, cpf=?, rg=?, "
				+"tipoSangue=?, nascimento=?, email=?, telefone=?, celular=?, contato=?, usuario=?, "
				+"senha=?, genero=?, profissao=?, descricao=?, endereco=?";
				if(usuario.getEscola() != null){
					comando = comando+", escola=? ";
				}
				comando = comando+" WHERE id = "+ usuario.getId();
		jdbcTeste.mostraQuerie(comando, "UsuarioDAOJDBC", "editarUsuario");
		enderecoServico.editarEndereco(usuario.getEndereco());		
		usuario.setEndereco(enderecoServico.buscarEndereco(usuario.getEndereco()));
		if(usuario.getEscola() != null){
			Escola busca = escolaServico.buscarEscola(usuario.getEscola());
			if( busca != null){
				escolaServico.editarEscola(usuario.getEscola());
			}else{
				escolaServico.adicionarEscola(usuario.getEscola());
				usuario.setEscola(escolaServico.buscarEscola(usuario.getEscola()));
			}
		}
		Date sqlDate = convert.converteParaSqlDate(usuario.getNascimento());
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setInt(1, usuario.getTipoConta());
			p.setString(2, usuario.getNome());
			p.setString(3, usuario.getSobrenome());
			p.setLong(4, usuario.getCpf());
			p.setLong(5, usuario.getRg());
			p.setString(6, usuario.getTipoSangue());
			p.setDate(7, sqlDate);
			p.setString(8, usuario.getEmail());
			p.setLong(9, usuario.getTelefone());
			p.setLong(10, usuario.getCelular());
			p.setString(11, usuario.getContato());
			p.setString(12, usuario.getUsuario());
			p.setString(13, usuario.getSenha());
			p.setString(14, usuario.getGenero());
			p.setString(15, usuario.getProfissao());
			p.setString(16, usuario.getDescricao());
			p.setInt(17, usuario.getEndereco().getId());
			if (usuario.getEscola() != null) {
				p.setInt(18, usuario.getEscola().getId());
			}
			p.execute();
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar usuário.", e);
		}

		return true;

	}
	
	


}// END CLASS

