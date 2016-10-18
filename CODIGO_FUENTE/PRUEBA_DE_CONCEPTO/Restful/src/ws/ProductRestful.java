package ws;

import datos.*;
import java.util.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/producto")
public class ProductRestful {
	
	@GET
	@Path("/enlistar")
	@Produces(MediaType.APPLICATION_XML)
	public List<Product> findAll(){
		List<Product> result = new ArrayList<Product>();
		result.add(new Product("ho", 1));
		result.add(new Product("ho", 2));
		System.out.println("Exito");
		return result;
	}
	
}