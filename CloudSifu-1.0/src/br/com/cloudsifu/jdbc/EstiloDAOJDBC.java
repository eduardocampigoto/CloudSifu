package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.EstiloDAO;
import br.com.cloudsifu.objects.Estilo;

public class EstiloDAOJDBC implements EstiloDAO {

	private Connection connect;
	private AcessaDB acessaDB;

	public EstiloDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
	}

	public boolean adicionarEstilo(Estilo estilo) throws GlobalException {
		String comando = "INSERT INTO `Estilo` " + "(nome,descricao)"
				+ "VALUES(?,?)";
		PreparedStatement p;
		try {

			p = connect.prepareStatement(comando);
			p.setString(1, estilo.getNome());
			p.setString(2, estilo.getDescricao());

			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" adicionar estilo", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public Estilo buscarEstilo(Estilo estilo) throws GlobalException {

		Estilo retorna = new Estilo();
		String comando = "SELECT * FROM Estilo";

		if (!estilo.equals("null") && !estilo.equals("")) {
			comando += " WHERE nome LIKE '" + estilo.getNome() + "'";
		}

		try {
			ResultSet rs = acessaDB.consulta(comando);
			retorna.setId(rs.getInt("id"));
			retorna.setNome(rs.getString("nome"));
			retorna.setDescricao(rs.getString("descricao"));

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" buscar estilo", e);
		}
		acessaDB.fechaConexao();
		return retorna;
	}

	public List<Estilo> buscarEstiloNome(Estilo busca) throws GlobalException {
		List<Estilo> estiloLista = new ArrayList<Estilo>();
		Estilo estilo = null;
		String comando = "SELECT * FROM Estilo";		

		if (!busca.equals("null") && !busca.equals("") && !busca.getNome().equals("todos")) {
			comando += " WHERE nome LIKE '%" + busca.getNome() + "%'";
		}

		try {
			
			ResultSet rs = acessaDB.consulta(comando);

			while (rs.next()) {
				estilo = new Estilo();
				estilo.setId(rs.getInt("id"));
				estilo.setNome(rs.getString("nome"));
				estilo.setDescricao(rs.getString("descricao"));
				estiloLista.add(estilo);
			}
			acessaDB.fechaConexao();
			return estiloLista;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" buscar lista de estilos", e);
		}
		
		
	}

	public boolean excluirEstilo(Estilo estilo) throws GlobalException {
		String comando = "DELETE  FROM Estilo WHERE id =" + estilo.getId();
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" excluir estilo", e);
		}
		acessaDB.fechaConexao();
		return true;
	}

	public boolean editarEstilo(Estilo estilo) throws GlobalException {

		String comando = "UPDATE Estilo SET nome=?, descricao=?  WHERE id="
				+ estilo.getId();

		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setString(1, estilo.getNome());
			p.setString(2, estilo.getDescricao());
			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" editar estilo", e);
		}
		return true;

	}

	public Estilo buscarEstiloId(Estilo estilo) throws GlobalException {
		String comando = "SELECT * FROM Estilo WHERE id =" + estilo.getId();
		Estilo retorna = new Estilo();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				retorna.setId(rs.getInt("id"));
				retorna.setNome(rs.getString("nome"));
				retorna.setDescricao(rs.getString("descricao"));
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" buscar estilo id", e);
		}
		acessaDB.fechaConexao();
		return retorna;
	}

}
