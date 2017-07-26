package br.com.cloudsifu.objects;

public class Turma {
	
	private int id;
	private String nome;
	private String horario;
	private String diaSemana;
	private String descricao;
	
	
	public int getId(){
		return id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getHorario(){
		return horario;	
	}
	

	public String getDiaSemana(){
		return diaSemana;
	}
	
	
	public String getDescricao(){
		return descricao;
	}
	
	public void setId(int id){		
		this.id = id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setHorario(String horario){
		this.horario = horario;
	}
	
	public void setDiaSemana(String diaSemana){
		this.diaSemana = diaSemana;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
}
