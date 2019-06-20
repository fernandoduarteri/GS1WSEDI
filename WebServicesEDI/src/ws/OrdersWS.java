package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Empresa;
import model.ObjectReturn;
import model.Order;
import services.EmpresaServices;
import services.OrderServices;

@Path("Orden")
public class OrdersWS {
	
	//COmentario
	
	@Path("/Crear")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String CrearOrden(String strData) {
		ObjectReturn objReturn = new ObjectReturn();
		String resultado = "";
		Gson objJSON = new Gson();
		JsonParser objJsonParser = new JsonParser();
		JsonObject objJsonObject = objJsonParser.parse(strData).getAsJsonObject();
		try {
			Order objOrden = new Order();
			OrderServices objEmpresaService = new OrderServices();
			objReturn.setData(objOrden);
			objEmpresaService.crearOrden(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}
			
			resultado = objJSON.toJson(objReturn);
			return resultado;
		} catch (Exception e) {
			return resultado;
		}

	}
	

}
