package br.com.cloudsifu.jdbc;

import java.sql.Date;

public class DateConvert {

	public Date converteParaSqlDate(java.util.Date convert) {
		Date sqlDate = new java.sql.Date(convert.getTime());
		return sqlDate;

	}
	
	public java.util.Date convertParaUtilDate(java.sql.Date convert){
		java.util.Date utilDate = new java.util.Date(convert.getTime());
		return utilDate;
	}
	
	

}
