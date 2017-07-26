package br.com.cloudsifu.objects;
import java.util.Date;

import br.com.cloudsifu.objects.Endereco;

public class Usuario {
	
private int id;
private String nome;
private String sobrenome;
private Date nascimento;
private String genero;
private String email;
private String profissao;
private long rg;
private long cpf;
private String tipoSangue;
private int tipoConta;
private long telefone;
private long celular;
private String contato;
private String usuario;
private String senha;
private String descricao;
private Endereco  endereco;
private Escola escola;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getSobrenome() {
	return sobrenome;
}
public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
}
public Date getNascimento() {
	return nascimento;
}
public void setNascimento(Date nascimento) {
	this.nascimento = nascimento;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getProfissao() {
	return profissao;
}
public void setProfissao(String profissao) {
	this.profissao = profissao;
}
public long getRg() {
	return rg;
}
public void setRg(long rg) {
	this.rg = rg;
}
public long getCpf() {
	return cpf;
}
public void setCpf(long cpf) {
	this.cpf = cpf;
}
public String getTipoSangue() {
	return tipoSangue;
}
public void setTipoSangue(String tipoSangue) {
	this.tipoSangue = tipoSangue;
}
public int getTipoConta() {
	return tipoConta;
}
public void setTipoConta(int tipoConta) {
	this.tipoConta = tipoConta;
}
public long getTelefone() {
	return telefone;
}
public void setTelefone(long telefone) {
	this.telefone = telefone;
}
public long getCelular() {
	return celular;
}
public void setCelular(long celular) {
	this.celular = celular;
}
public String getContato() {
	return contato;
}
public void setContato(String contato) {
	this.contato = contato;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public Endereco getEndereco() {
	return endereco;
}
public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
}
public Escola getEscola() {
	return escola;
}
public void setEscola(Escola escola) {
	this.escola = escola;
}


}