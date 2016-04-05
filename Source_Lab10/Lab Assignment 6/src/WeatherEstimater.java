import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/weatherestimator")
public class WeatherEstimater {

	  @GET
	  @Produces("application/json")

	  public Response getWeather() throws JSONException {
		  
		  String output = null;
		  try {
			  
				Client client = Client.create();
				WebResource web = client.resource("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=cb51ab3ccea43b8f3ef077534b5ff272");
				ClientResponse response = web.accept("application/json").get(ClientResponse.class);
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}
	 
				output = response.getEntity(String.class);
				System.out.println("\n============getFtoCResponse============");
				System.out.println(output);
				//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		  return Response.status(200).entity(output).build();
			
  }
}
