package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Cidade;
import br.com.cloudsifu.objects.UnidadeFederativa;
import br.com.cloudsifu.services.UnidadeFederativaServico;

public class CidadeDAOJDBC implements CidadeDAO {

	private AcessaDB acessaDB;
	private Connection connect;
	private UnidadeFederativaServico ufServico;

	public CidadeDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		ufServico = new UnidadeFederativaServico();
	}
	
	private Cidade montarCidade(Cidade cidade) throws GlobalException{
		if(cidade != null){
		 	cidade.setUnidadeFederativa((ufServico.buscarUnidadeFederativaId(cidade.getUnidadeFederativa())));
		}
		return cidade;
	}

	public void adicionarCidade(Cidade cidade) throws GlobalException {
		PreparedStatement p;
		String comando = "INSERT INTO `Cidade` " + "(nome, unidadeFederativa)"
				+ "VALUES(?,?)";
			UnidadeFederativa  uf = ufServico.buscarUnidadeFederativa(cidade.getUnidadeFederativa());
		if(uf == null){
			ufServico.adicionarUnidadeFederativa(cidade.getUnidadeFederativa());
			cidade.setUnidadeFederativa(ufServico.buscarUnidadeFederativa(cidade.getUnidadeFederativa()));	
		}else{
			cidade.setUnidadeFederativa(uf);
		}
		try {
						
			p = connect.prepareStatement(comando);
			p.setString(1, cidade.getNome());
			p.setInt(2, cidade.getUnidadeFederativa().getId());
			p.execute();
			acessaDB.fechaConexao();
		} catch (Exception e) {

			throw new GlobalException("adicionar cidade", e);
		}
	}

	public Cidade buscarCidade(Cidade busca) throws GlobalException {
		Cidade cidade = null;
		String comando = " SELECT * FROM Cidade" + " WHERE" + " Cidade.nome ='"
				+ busca.getNome() + "'";
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setId(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.setUnidadeFederativa(new UnidadeFederativa());
				cidade.getUnidadeFederativa().setId((rs.getInt("unidadeFederativa")));
			}
			acessaDB.fechaConexao();

		} catch (Exception e) {

			throw new GlobalException("buscar cidade", e);

		}
		return montarCidade(cidade);

	}

	public boolean excluirCidade(Cidade cidade) throws GlobalException {

		String comando = "DELETE  FROM Cidade WHERE id =" + cidade.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();

		} catch (Exception e) {

			throw new GlobalException("excluir cidade", e);

		}
		return true;

	}

	public boolean editarCidade(Cidade cidade) throws GlobalException {
		String comando = "UPDATE cidade SET nome=? WHERE id=" + cidade.getId();
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setString(1, cidade.getNome());
			p.setInt(2, cidade.getUnidadeFederativa().getId());
			p.execute();
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("editar cidade", e);
		}
		return true;

	}

	public Cidade buscarCidadeId(Cidade busca) throws GlobalException {
		Cidade cidade = null;
		String comando = "SELECT * FROM Cidade WHERE id =" + busca.getId();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				cidade = new Cidade();
				cidade.setId(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.setUnidadeFederativa(new UnidadeFederativa());
				cidade.getUnidadeFederativa().setId((rs.getInt("unidadeFederativa")));
			}
			acessaDB.fechaConexao();

		} catch (Exception e) {

			throw new GlobalException("buscar cidade por ID", e);
		}
		return montarCidade(cidade);

	}

}
