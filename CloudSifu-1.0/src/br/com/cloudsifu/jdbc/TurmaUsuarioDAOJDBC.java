package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.objects.Escola;
import br.com.cloudsifu.objects.Turma;
import br.com.cloudsifu.objects.TurmaUsuario;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.EnderecoServico;
import br.com.cloudsifu.services.EscolaServico;
import br.com.cloudsifu.services.TurmaServico;
import br.com.cloudsifu.services.UsuarioServico;

public class TurmaUsuarioDAOJDBC implements TurmaUsuarioDAO {
	private Connection connect;
	private AcessaDB acessaDB;
	private EnderecoServico enderecoServico;
	private EscolaServico escolaServico;
	private UsuarioServico usuarioServico;
	private TurmaServico turmaServico;

	public TurmaUsuarioDAOJDBC() {
		
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		enderecoServico = new EnderecoServico();
		escolaServico = new EscolaServico();
		usuarioServico = new UsuarioServico();
		turmaServico = new TurmaServico();
	}

	private Usuario montarUsuarioExistente(Usuario usuario, String metodo)
			throws GlobalException, ParseException {
		usuario.setEndereco(enderecoServico.buscarEnderecoId(usuario
				.getEndereco()));

		if (usuario.getEscola() != null) {
			usuario.setEscola(escolaServico.buscarEscolaId(usuario.getEscola()));
		}
		java.util.Date novaData = new java.util.Date(usuario.getNascimento()
				.getTime());
		usuario.setNascimento(novaData);
		return usuario;
	}
	public boolean adicionarTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {

		try {
			String comando = "INSERT INTO `TurmaUsuario` "
					+ "(turma, usuario) VALUES(?,?)";
			PreparedStatement p;
			p = connect.prepareStatement(comando);
			p.setInt(1, turmaUsuario.getTurma().getId());
			p.setInt(2, turmaUsuario.getUsuario().getId());
			p.execute();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar turmaUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;
		

	}

	public boolean excluirTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {
		try {
			String comando = "DELETE  FROM TurmaUsuario WHERE id ="
					+ turmaUsuario.getId();
			Statement p;
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir turmaUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public boolean editarTurmaUsuario(TurmaUsuario turmaUsuario)
			throws GlobalException {

		try {
			String comando = "UPDATE TurmaUsuario SET turma=?, usuario=? WHERE id="
					+ turmaUsuario.getId();

			PreparedStatement p;

			p = connect.prepareStatement(comando);
			p.setInt(1, turmaUsuario.getId());
			p.setInt(2, turmaUsuario.getTurma().getId());
			p.setInt(3, turmaUsuario.getUsuario().getId());
			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar turmaUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public List<TurmaUsuario> buscarTurmaUsuarios(TurmaUsuario busca)
			throws GlobalException {
		TurmaUsuario turmaUsuario = null;
		List<TurmaUsuario> listaTurmaUsuarios = new ArrayList<TurmaUsuario>();
		try {
			String comando = "SELECT * FROM TurmaUsuario WHERE turma = "
					+ busca.getId();
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {

				turmaUsuario = new TurmaUsuario();
				turmaUsuario.setTurma(new Turma());
				turmaUsuario.setUsuario(new Usuario());
				turmaUsuario.setId(rs.getInt("id"));
				turmaUsuario.getTurma().setId(rs.getInt("turma"));
				turmaUsuario.getUsuario().setId(rs.getInt("usuario"));
				turmaUsuario.setUsuario(usuarioServico.buscarUsuarioId(turmaUsuario.getUsuario()));
				turmaUsuario.setTurma(turmaServico.buscarTurmaId(turmaUsuario.getTurma()));
				listaTurmaUsuarios.add(turmaUsuario);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turmaUsuarios ", e);
		}
		acessaDB.fechaConexao();
		return listaTurmaUsuarios;
	}
	
	
	
	
	
	public List<Usuario> buscarListaUsuarios(TurmaUsuario busca)
			throws GlobalException {
		Usuario usuario = null;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String comando = "SELECT * FROM TurmaUsuario, Usuario WHERE TurmaUsuario.turma="+busca.getTurma().getId()
		+" AND Usuario.id = TurmaUsuario.usuario";
		System.out.println(comando);
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
				listaUsuarios.add(montarUsuarioExistente(usuario, "buscarUsuarioId"));
			}
			
			acessaDB.fechaConexao();
			
			return listaUsuarios;
			
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException("buscar usuário por id.", e);
		}
	}

	public TurmaUsuario buscarTurma(TurmaUsuario busca)
			throws GlobalException {
		TurmaUsuario turmaUsuario = null;
		try {
			String comando = "SELECT * FROM TurmaUsuario WHERE turma = "
					+ busca.getTurma().getId();
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turmaUsuario = new TurmaUsuario();
				turmaUsuario.setTurma(new Turma());
				turmaUsuario.setUsuario(new Usuario());
				turmaUsuario.getTurma().setId(rs.getInt("turma"));
				turmaUsuario.getUsuario().setId(rs.getInt("usuario"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turmaUsuario por ID", e);
		}
		acessaDB.fechaConexao();
		return turmaUsuario;
	}
	
	
	
	public TurmaUsuario buscarUsuario(TurmaUsuario busca)
			throws GlobalException {
		TurmaUsuario turmaUsuario = null;
		try {
			String comando = "SELECT * FROM TurmaUsuario WHERE usuario = "
					+ busca.getUsuario().getId();
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				turmaUsuario = new TurmaUsuario();
				turmaUsuario.setTurma(new Turma());
				turmaUsuario.setUsuario(new Usuario());
				turmaUsuario.setId(rs.getInt("id"));
				turmaUsuario.getTurma().setId(rs.getInt("turma"));
				turmaUsuario.getUsuario().setId(rs.getInt("usuario"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar turmaUsuario por ID", e);
		}
		acessaDB.fechaConexao();
		return turmaUsuario;
	}
}
