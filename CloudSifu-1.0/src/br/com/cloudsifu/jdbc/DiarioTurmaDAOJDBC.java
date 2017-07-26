package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.jdbc.DiarioTurmaDAO;
import br.com.cloudsifu.objects.DiarioTurma;
import br.com.cloudsifu.objects.Turma;
import br.com.cloudsifu.services.TurmaServico;

public class DiarioTurmaDAOJDBC implements DiarioTurmaDAO {

	private AcessaDB acessaDB;
	private Connection connect;
	private DateConvert convert;
	private TurmaServico turmaServico;

	public DiarioTurmaDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		turmaServico = new TurmaServico();
		convert = new DateConvert();

	}

	public void adicionarDiarioTurma(DiarioTurma diarioTurma)
			throws GlobalException {
		String comando = "";
		Date sqlDate = convert.converteParaSqlDate(diarioTurma.getData());
		if(diarioTurma.getId() == 0){
			comando = "INSERT INTO `DiarioTurma` (data,turma, observacoes) VALUES(?,?,?)";
		}else{
			comando = "UPDATE DiarioTurma SET data=?, turma=?, observacoes=? WHERE id='"
					+ diarioTurma.getId()+"'"
					+ "AND data='"+sqlDate+"'";
		}
		
		PreparedStatement p;
		try {
			
			p = connect.prepareStatement(comando);
			p.setDate(1, sqlDate);
			p.setInt(2, diarioTurma.getTurma().getId());
			p.setString(3, diarioTurma.getObservacoes());
			p.execute();
		
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar diario de Turma", e);
		}
		acessaDB.fechaConexao();
	}

	public boolean editarDiarioTurma(DiarioTurma diarioTurma)
			throws GlobalException {
		String comando = "UPDATE DiarioTurma SET turma=?, data=?, observacoes=? WHERE id="
				+ diarioTurma.getId();
		
		DateConvert convert = new DateConvert();
	 	Date sqlDate = convert.converteParaSqlDate(diarioTurma.getData());
		PreparedStatement p;
		try {
			p = connect.prepareStatement(comando);
			p.setInt(1, diarioTurma.getTurma().getId());
			p.setDate(2, sqlDate);
			p.setString(3, diarioTurma.getObservacoes());
			p.execute();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar diario de Turma", e);
		}
		acessaDB.fechaConexao();
		return true;
	}

	public DiarioTurma buscarDiarioTurmaId(DiarioTurma busca)
			throws GlobalException {

		String comando = "SELECT * FROM DiarioTurma WHERE id =" + busca.getId();
		DiarioTurma diarioTurma = new DiarioTurma();
		
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				diarioTurma.setId(rs.getInt("id"));				
				diarioTurma.setData(convert.convertParaUtilDate(rs.getDate("data")));
				diarioTurma.setTurma(new Turma());
				diarioTurma.getTurma().setId(rs.getInt("turma"));
				diarioTurma.setTurma(turmaServico.buscarTurmaId(diarioTurma.getTurma()));
				diarioTurma.setObservacoes(rs.getString("observacoes"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar diario de Turma por id", e);
		}
		acessaDB.fechaConexao();
		return diarioTurma;
	}

	public DiarioTurma buscarDiarioTurma(DiarioTurma busca)
			throws GlobalException {
		DiarioTurma diarioTurma = new DiarioTurma();
		String comando = "SELECT * FROM DiarioTurma";
		comando += " WHERE id =" + busca.getId();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				diarioTurma.setId(rs.getInt("id"));				
				diarioTurma.setData(convert.convertParaUtilDate(rs.getDate("data")));
				diarioTurma.setTurma(new Turma());
				diarioTurma.getTurma().setId(rs.getInt("turma"));
				diarioTurma.setTurma(turmaServico.buscarTurmaId(diarioTurma.getTurma()));
				diarioTurma.setObservacoes(rs.getString("observacoes"));
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar diario de Turma", e);
		}
		acessaDB.fechaConexao();
		return diarioTurma;

	}

	public DiarioTurma buscarDiarioTurmaData(DiarioTurma busca)
			throws GlobalException {
		DiarioTurma diarioTurma = new DiarioTurma();
		if (busca != null && !busca.equals("")) {
		 	Date sqlDate = convert.converteParaSqlDate(busca.getData());
			String comando = "SELECT * FROM DiarioTurma";		
			comando += " WHERE data='" +sqlDate+"'"
						+" AND turma='"+busca.getTurma().getId()+"'";
			
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {				
				diarioTurma.setId(rs.getInt("id"));				
				diarioTurma.setData(convert.convertParaUtilDate(rs.getDate("data")));
				diarioTurma.setTurma(new Turma());
				diarioTurma.getTurma().setId(rs.getInt("turma"));
				diarioTurma.setTurma(turmaServico.buscarTurmaId(diarioTurma.getTurma()));
				diarioTurma.setObservacoes(rs.getString("observacoes"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar diario de Turma por data", e);
		}
		acessaDB.fechaConexao();
		}
		return diarioTurma;
	}

	public boolean excluirDiarioTurma(DiarioTurma exclui)
			throws GlobalException {
		String comando = "DELETE  FROM DiarioTurma WHERE id =" + exclui.getId();
		Statement p;
		try {
			p = connect.createStatement();
			p.execute(comando);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir diario de Turma", e);
		}
		acessaDB.fechaConexao();
		return true;
	}
}
