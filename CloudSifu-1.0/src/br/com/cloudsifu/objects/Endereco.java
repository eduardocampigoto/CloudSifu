package br.com.cloudsifu.objects;

public class Endereco {
	
	private int id;
	private int cep;
	private Bairro bairro;
	private int logradouro;
	private String nome;
	private int numero;
	private String referencia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public int getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(int logradouro) {
		this.logradouro = logradouro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
		
}
