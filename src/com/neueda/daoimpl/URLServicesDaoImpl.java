package com.neueda.daoimpl;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neueda.dao.DBservicesDao;
import com.neueda.dao.URLServicesDao;

public class URLServicesDaoImpl implements URLServicesDao {
	DBservicesDao dbServices;
	public URLServicesDaoImpl() {
		dbServices=new DBServicesDaoImpl();
	}
	//Get insert url in database and get generated id encode it to create short url
	@Override
	public String getShortenedURL(String longURL,int userId) throws SQLException {
		int id=dbServices.insertURL(longURL,userId);
		if(id!=0) {
		return encode(String.valueOf(id));}
		else return "NOT INSERTED";
	}

	//Get long url from database
	@Override
	public String getLongerURL(String encodedId)  throws SQLException{
		
		String id=decode(encodedId);
		return dbServices.getLongerURL(id);
		
	}
	
	//Validation of url
	public boolean isValidURL(String url)
    {
       
        String regex = "((http|https|Http|Https)://)(www.)?"
              + "[a-zA-Z0-9@:%._\\+~#?&//=]"
              + "{2,256}\\.[a-z]"
              + "{2,6}\\b([-a-zA-Z0-9@:%"
              + "._\\+~#?&//=]*)";
 
        
        Pattern p = Pattern.compile(regex);
 
        if (url == null) {
            return false;
        }
        Matcher m = p.matcher(url);
 
        return m.matches();
    }
	
	//Encode the id
	public String encode(String id)
	{
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString( 
		        id.getBytes(StandardCharsets.UTF_8) );
	}
	
	//Decode the id 
	public String decode(String encodedId)
	{  try {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedByteArray = decoder.decode(encodedId);
		return new String(decodedByteArray);}
		catch (IllegalArgumentException ia)
		{
			return "Not Found";
		}
	}
}
