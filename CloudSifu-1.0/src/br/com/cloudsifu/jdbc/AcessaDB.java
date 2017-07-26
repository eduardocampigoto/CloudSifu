package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import br.com.cloudsifu.db.CsConnection;

public class AcessaDB {

	private CsConnection csConnect;
	private Connection connect;

	public AcessaDB() {
		this.csConnect = new CsConnection();
	}

	public ResultSet consulta(String comando) throws SQLException {
		this.connect = criaConexao();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		return rs;
	}

	public Connection criaConexao() {
		Connection open = csConnect.openConnection();
		return open;
	}

	public void fechaConexao() {
		this.csConnect.closeConnection();
	}

}
