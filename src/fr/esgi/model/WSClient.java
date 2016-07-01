package fr.esgi.model;



import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

public class WSClient {
	public List<User> getAllUser() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		HttpGet getRequest = new HttpGet("http://totot.api.fr/user");
		
		getRequest.addHeader("accept", "application/json");
		
		List<User> userList = null;
		
		try{
			HttpResponse response = httpClient.execute(getRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " +  response.getStatusLine().getStatusCode());
			}
			
			InputStream content = response.getEntity().getContent();
			
			ObjectMapper mapper = new ObjectMapper();
			User[] users = mapper.readValue(content, User[].class);
			userList = Arrays.asList(users);
			
			httpClient.getConnectionManager().shutdown();
			
			
		}catch (IOException e){
			e.printStackTrace();
		}
		return userList;
	}
}
