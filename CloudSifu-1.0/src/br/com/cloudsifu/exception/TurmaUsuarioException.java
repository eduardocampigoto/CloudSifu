package br.com.cloudsifu.exception;



public class TurmaUsuarioException extends Exception{
	
	public TurmaUsuarioException(String msg){
		super(msg);
	}
	
	private static final long serialVersionUID = 1L;

	private String msg;
	
	
	
	public String erroException(){
		
		this.msg = "Valor nulo enviado";
		
		
		return msg;
	}
	
	
	

	
	
}
