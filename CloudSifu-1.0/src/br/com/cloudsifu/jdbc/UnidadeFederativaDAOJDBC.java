package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.UnidadeFederativa;

public class UnidadeFederativaDAOJDBC implements UnidadeFederativaDAO {

	private Connection connect;
	private AcessaDB acessaDB;

	public UnidadeFederativaDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
	}

	public void adicionarUF(UnidadeFederativa uf)
			throws GlobalException {
		PreparedStatement p;
		String comando = "INSERT INTO `UnidadeFederativa` " + "(sigla, nome)"
				+ "VALUES(?,?)";

		try {
			p = connect.prepareStatement(comando);
			p.setString(1, uf.getSigla());
			p.setString(2, uf.getNome());
			p.execute();
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("adicionar unidade federativa", e);
		}

	}

	public UnidadeFederativa buscarUnidadeFederativa(UnidadeFederativa busca)
			throws GlobalException {
		UnidadeFederativa uf = null;
		String comando = " SELECT * FROM UnidadeFederativa" + " WHERE"
				+ " UnidadeFederativa.nome ='" + busca.getNome() + "'";
		try {

			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				uf = new UnidadeFederativa();
				uf.setId(rs.getInt("id"));
				uf.setSigla(rs.getString("sigla"));
				uf.setNome(rs.getString("nome"));
			}
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("buscar unidade federativa", e);
		}

		return uf;

	}

	public boolean excluirUF(UnidadeFederativa uf) throws GlobalException {

		String comando = "DELETE  FROM UnidadeFederativa WHERE id ="
				+ uf.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("excluir unidade federativa", e);
		}

		return true;

	}

	public boolean editarUF(UnidadeFederativa uf) throws GlobalException {

		String comando = "UPDATE UnidadeFederativa SET nome=? WHERE id="
				+ uf.getId();
		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setString(1, uf.getNome());
			p.execute();
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("editar unidade federativa", e);
		}

		return true;

	}

	public UnidadeFederativa buscarUFId(UnidadeFederativa busca)
			throws GlobalException {
		UnidadeFederativa uf = null;
		String comando = "SELECT * FROM UnidadeFederativa WHERE id = '"
				+ busca.getId() + "'";

		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				uf = new UnidadeFederativa();
				uf.setSigla(rs.getString("sigla"));
				uf.setNome(rs.getString("nome"));
			}
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("buscar unidade federativa por ID", e);
		}

		return uf;

	}

}
