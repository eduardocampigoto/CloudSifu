package br.com.cloudsifu.rest;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.cloudsifu.exception.GlobalException;


public class UtilRest {

		public ObjectMapper getObjectMapper() throws GlobalException{
			try{
				ObjectMapper mapper = new ObjectMapper();
				mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
				return mapper;
							
			}catch(Throwable e){
				e.printStackTrace();
				return null;	
			}
			
		
		}
		
		
		public String buildResponse(Object	result){
			StringWriter fw = new StringWriter();
			try{

				ObjectMapper mapper = new ObjectMapper();
				mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));				
				mapper.writeValue(fw, result);				
				return fw.toString();
							
				
			}catch(Exception e){

				ResponseBuilder rb = Response.serverError();
				return rb.toString();

			}// END try


		}//END buildResponse
		
		
		
		public Date getDate(String dtstr) throws ParseException{			
			SimpleDateFormat dtft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date data = dtft.parse(dtstr);
			return data;
		}



}
