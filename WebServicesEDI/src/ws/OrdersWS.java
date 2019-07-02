package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Utilidades.Constantes;
import model.ObjectReturn;
import services.OrderServices;

@Path("Orden")
public class OrdersWS {
	
	
	@HeaderParam ("content-type")
	String strContentType;
	@HeaderParam ("autorizacion")
	String strAutorizacion;
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
		objReturn.setData(objJsonObject);
		try {
			OrderServices objOrderService = new OrderServices();
			objOrderService.crear(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}
			
			resultado = objJSON.toJson(objReturn);
			return resultado;
		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
			resultado = objJSON.toJson(objReturn);
			return resultado;
		}

	}
	

}
