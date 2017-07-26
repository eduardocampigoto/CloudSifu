package br.com.cloudsifu.exception;


public class GlobalException extends Exception{
	
private static final long serialVersionUID = 1L;

	public GlobalException(String msg){		
			super(msg);
	}

	public GlobalException(String msg, Throwable cause){		
			super("Ocorreu um erro ao realizar a operação "+msg, cause);
	}


}