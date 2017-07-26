package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Bairro;
import br.com.cloudsifu.objects.Cidade;
//import br.com.cloudsifu.test.BairroTeste;
import br.com.cloudsifu.test.JDBCTeste;

public class BairroDAOJDBC implements BairroDAO {

	private AcessaDB acessaDB;
	private Connection connect;
	private JDBCTeste jdbcTeste;
	//private BairroTeste bairroTeste;
	private CidadeDAOJDBC cidadeJdbc;
	
	public BairroDAOJDBC() {
		acessaDB = new AcessaDB();
		//bairroTeste = new BairroTeste(null, null, null);
		connect = acessaDB.criaConexao();
		cidadeJdbc = new CidadeDAOJDBC();
		jdbcTeste = new JDBCTeste();
	}

	private Bairro montarBairro(Bairro bairro) throws GlobalException{
		if(bairro != null){
		 	bairro.setCidade(cidadeJdbc.buscarCidadeId(bairro.getCidade()));
		}
		return bairro;
	}
	public void adicionarBairro(Bairro bairro) throws GlobalException {
		
		PreparedStatement p;
		String comando = "INSERT INTO `Bairro` " + "(nome, cidade)"
				+ "VALUES(?,?)";
		jdbcTeste.mostraQuerie(comando, "BairroJDBC", "adicionarBairro");
		//bairroTeste.mostraBairro(bairro, "BairroDAOJDBC", "adicionarBairro");
		try {
			bairro.setCidade(cidadeJdbc.buscarCidade(bairro.getCidade()));
			p = connect.prepareStatement(comando);
			p.setString(1, bairro.getNome());
			p.setInt(2, bairro.getCidade().getId());
			p.execute();
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("adicionar bairro", e);
		}
		
	}

	public boolean excluirBairro(Bairro bairro) throws GlobalException {

		String comando = "DELETE  FROM Bairro WHERE id =" + bairro.getId();
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("buscar bairro por ID", e);
		}
		jdbcTeste.mostraQuerie(comando, "BairroJDBC", "excluirBairro");
		//bairroTeste.mostraBairro(bairro, "BairroDAOJDBC", "excluirBairro");
		return true;

	}

	public boolean editarBairro(Bairro bairro) throws GlobalException {
				String comando = "UPDATE bairro SET nome=? WHERE id=" + bairro.getId();
		
		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setString(1, bairro.getNome());
			p.setObject(2, bairro.getCidade());
			p.execute();
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("editar bairro", e);
		}
		jdbcTeste.mostraQuerie(comando, "BairroJDBC", "editarBairro");
		//bairroTeste.mostraBairro(bairro, "BairroDAOJDBC", "editarBairro");
		return true;
	}

	public Bairro buscarBairroId(Bairro busca) throws GlobalException {
		Bairro bairro = null;
		String comando = "SELECT * FROM Bairro WHERE id = " + busca.getId();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				bairro = new Bairro();
				bairro.setId(bairro.getId());
				bairro.setNome(rs.getString("nome"));
				bairro.setCidade(new Cidade());
				bairro.getCidade().setId(rs.getInt("cidade"));
			}
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("buscar bairro por ID", e);
		}
		
		jdbcTeste.mostraQuerie(comando, "BairroJDBC", "buscarBairroId");
		//bairroTeste.mostraBairro(bairro, "BairroDAOJDBC", "buscarBairroId");
		return montarBairro(bairro);
	}

	public Bairro buscarBairro(Bairro busca) throws GlobalException {
		Bairro bairro = null;
		String comando = " SELECT * FROM Bairro" + " WHERE "
				+ " Bairro.nome = '" + busca.getNome() + "'";
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				bairro = new Bairro();
				bairro.setId(rs.getInt("id"));
				bairro.setNome(rs.getString("nome"));
				bairro.setCidade(new Cidade());
				bairro.getCidade().setId(rs.getInt("cidade"));
			}
			acessaDB.fechaConexao();
			
			
		} catch (Exception e) {
			throw new GlobalException("buscar bairro", e);
		}
		jdbcTeste.mostraQuerie(comando, "BairroJDBC", "buscarBairro");
		//bairroTeste.mostraBairro(bairro, "BairroDAOJDBC", "buscarBairro");
		return montarBairro(bairro);
	}

}
