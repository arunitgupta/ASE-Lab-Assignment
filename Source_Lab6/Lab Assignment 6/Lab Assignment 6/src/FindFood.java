import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
@Path("/foodlove")
public class FindFood {
	@GET
	  @Produces("application/json")
	public Response findFood() throws JSONException {
		 String output = null;
		  try {
			  
				Client client = Client.create();
				WebResource web = client.resource("https://api.foursquare.com/v2/venues/search" +
                    "?client_id=PK5QOKFKNE13PVSIYHIPGFZ5DWCCVEEEKPL1JQZESURVXEU2" +
                    "&client_secret=LTIJDAUL0GVMNF4BIPMJKH3TTGCKDMJ2UVM33ZVYZQRL1WMR" +
                    "&v=20160215&limit=5"+"&near=city" +
                    "&query=Restaurant");
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
