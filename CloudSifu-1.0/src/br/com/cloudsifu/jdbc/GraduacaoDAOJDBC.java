package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.GraduacaoDAO;
import br.com.cloudsifu.objects.Graduacao;

public class GraduacaoDAOJDBC implements GraduacaoDAO {

	private Connection connect;
	private AcessaDB acessaDB;

	public GraduacaoDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
	}

	public boolean adicionarGraduacao(Graduacao graduacao)
			throws GlobalException {

		String comando = "INSERT INTO `Graduacao` " + "(nome,descricao)"
				+ "VALUES(?,?)";
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setString(1, graduacao.getNome());
			p.setString(2, graduacao.getDescricao());
			p.execute();
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar graduacao", e);
		}
		acessaDB.fechaConexao();
		return true;
	}

	public List<Graduacao> buscarGraduacao(String busca) throws GlobalException {
		Graduacao graduacao;
		List<Graduacao> listaGraduacao = new ArrayList<Graduacao>();
		String comando = "SELECT * FROM Graduacao";
		if (!busca.equals("null") && !busca.equals("") && !busca.equals("todas")) {
			comando += " WHERE nome LIKE '%" + busca + "%'";
		}
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				graduacao = new Graduacao();
				graduacao.setId(rs.getInt("id"));
				graduacao.setNome(rs.getString("nome"));
				graduacao.setDescricao(rs.getString("descricao"));
				listaGraduacao.add(graduacao);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar graduacao", e);
		}
		acessaDB.fechaConexao();
		return listaGraduacao;
	}
	
	
	public List<Graduacao> buscarGraduacaoNome(Graduacao busca) throws GlobalException {
		Graduacao graduacao;
		List<Graduacao> listaGraduacao = new ArrayList<Graduacao>();
		String comando = "SELECT * FROM Graduacao";
		if (!busca.equals("null") && !busca.equals("") && !busca.getNome().equals("todas")) {
			comando += " WHERE nome LIKE '%" + busca.getNome() + "%'";
		}
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				graduacao = new Graduacao();
				graduacao.setId(rs.getInt("id"));
				graduacao.setNome(rs.getString("nome"));
				graduacao.setDescricao(rs.getString("descricao"));
				listaGraduacao.add(graduacao);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar graduacao", e);
		}
		acessaDB.fechaConexao();
		return listaGraduacao;
	}

	public boolean excluirGraduacao(Graduacao graduacao) throws GlobalException {
		String comando = "DELETE  FROM Graduacao WHERE id ="
				+ graduacao.getId();
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" excluir graduacao", e);
		}
		acessaDB.fechaConexao();
		return true;
	}

	public boolean editarGraduacao(Graduacao graduacao) throws GlobalException {
		String comando = "UPDATE Graduacao SET nome=?, descricao=?  WHERE id="
				+ graduacao.getId();
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setString(1, graduacao.getNome());
			p.setString(2, graduacao.getDescricao());
			p.execute();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" editar graduacao", e);
		}
		acessaDB.fechaConexao();
		return true;
	}

	public Graduacao buscarGraduacaoId(Graduacao busca) throws GlobalException {
		String comando = "SELECT * FROM Graduacao WHERE id =" + busca.getId();
		Graduacao graduacao = new Graduacao();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				graduacao.setId(rs.getInt("id"));
				graduacao.setNome(rs.getString("nome"));
				graduacao.setDescricao(rs.getString("descricao"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" buscar graduacao por ID", e);
		}
		acessaDB.fechaConexao();
		return graduacao;
	}

}
