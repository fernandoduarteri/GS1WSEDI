package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import Utilidades.Constantes;
import Utilidades.GnuPG;
import dao.OrdenDAO;
import model.Empresa;
import model.ObjectReturn;
import model.Order;
import model.OrderItem;


public class OrderServices {

	private JsonObject objJsonObject;
	Order objOrder;
	JsonArray arrayItems;
	List<OrderItem> listOrderItems = new ArrayList<OrderItem>();
	private String jndiName = "java:comp/env/mail/GS1Nicaragua";
	OrdenDAO objOrdenDao = new OrdenDAO(Order.class);
	OrderItemServices objOrderItemServices = new OrderItemServices();
	private Logger log = Logger.getLogger(getClass().getName());

	public JsonObject getObjJsonObject() {
		return objJsonObject;
	}

	public void setObjJsonObject(JsonObject objJsonObject) {
		this.objJsonObject = objJsonObject;
	}
	

	public void crear(ObjectReturn objReturn) throws Exception {
		setObjJsonObject((JsonObject) objReturn.getData());
		

		try {
			SimpleDateFormat objDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			objOrder = new Order(((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("cliente")).get("cedula").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("cliente")).get("contacto­depto").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("cliente")).get("contacto­empleado").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("cliente")).get("telefono").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("cliente")).get("nombre").getAsString(), "PENDIENTE",
					new java.sql.Timestamp(objDateFormat.parse(objJsonObject.get("fecha­entrega").getAsString()).getTime()),
					new java.sql.Timestamp(objDateFormat.parse(objJsonObject.get("fecha­intercambio").getAsString()).getTime()),
					new java.sql.Timestamp(objDateFormat.parse(objJsonObject.get("fecha­orden").getAsString()).getTime()), objJsonObject.get("gln­proveedor").getAsString(),
					objJsonObject.get("gln­cliente").getAsString(), objJsonObject.get("numero­orden").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("calle").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("cedula").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("ciudad").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("gln").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("nombre").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("lugar­entrega")).get("telefono").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("proveedor")).get("cedula").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("proveedor")).get("contacto­depto").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("proveedor")).get("contacto­empleado").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("proveedor")).get("telefono").getAsString(),
					((JsonObject) ((JsonObject) objJsonObject.get("direcciones")).get("proveedor")).get("nombre").getAsString(), objJsonObject.get("seq­intercambio").getAsString(),
					((JsonObject) objJsonObject.get("detalle")).get("total").getAsString(), ((JsonObject) objJsonObject.get("detalle")).get("total­descuento").getAsString(),
					((JsonObject) objJsonObject.get("detalle")).get("total­items").getAsString(), ((JsonObject) objJsonObject.get("detalle")).get("total­iva").getAsString(),
					((JsonObject) objJsonObject.get("detalle")).get("total­paquetes").getAsString());
			
			objReturn.setData(objOrder);
			objOrdenDao.salvar(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}

			arrayItems = (((JsonArray) ((JsonObject) objJsonObject.get("detalle")).getAsJsonArray("items")));
			ObjectReturn objReturnAux = new ObjectReturn();
			for (int i = 0; i < arrayItems.size(); i++) {
				JsonObject objJsonObjectItem = arrayItems.get(i).getAsJsonObject();
				OrderItem objOrderItem = new OrderItem();
				objOrderItem.setCodigo(objJsonObjectItem.get("gtin").getAsString());
				objOrderItem.setCantidad(objJsonObjectItem.get("cantidad").getAsString());
				objOrderItem.setPrecio(objJsonObjectItem.get("precio").getAsString());
				objOrderItem.setMontoMonetario(objJsonObjectItem.get("monto­monetario").getAsString());
				objOrderItem.setDescuentoPorciento(objJsonObjectItem.get("descuento­porciento").getAsString());
				objOrderItem.setDescuentoMonto(objJsonObjectItem.get("descuento­monto").getAsString());
				objOrderItem.setIvaPorciento(objJsonObjectItem.get("iva­porciento").getAsString());
				objOrderItem.setIvaMonto(objJsonObjectItem.get("iva­monto").getAsString());
				objOrderItem.setGlnCliente(objJsonObject.get("gln­cliente").getAsString());
				objOrderItem.setNumeroOrden(objJsonObject.get("numero­orden").getAsString());
				listOrderItems.add(objOrderItem);
				objReturnAux.setData(objOrderItem);
				objOrderItemServices.salvar(objReturnAux);
			}

			this.enviarEmail();

		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
			log.info("Metodo crear Exception: " + e);
		}

	}

	private void enviarEmail() throws Exception {
		EmpresaServices objEmpresaServices = new EmpresaServices();
		ObjectReturn objReturnAux1 = new ObjectReturn();
		objReturnAux1.setData(objOrder.getGlnProveedor());
		objEmpresaServices.getone(objReturnAux1);
		Empresa objEmpresa = (Empresa) ((List<Empresa>)objReturnAux1.getData()).get(0);
		Context initialContext = new InitialContext();
		Session session = (Session) initialContext.lookup(jndiName);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("Fernando Duarte <fduarte@gs1ni.org>"));
			message.setSubject("ORDERS-" + objOrder.getIdKey().getGlnCliente() + "-" + objOrder.getIdKey().getNumeroOrden());
			message.addHeader("Disposition-Notification-To", "Fernando Duarte <fduarte@gs1ni.org>");
			message.setSentDate(new Date());
			message.addRecipients(Message.RecipientType.TO, objEmpresa.getCorreoAsig());
			Multipart objMultipart = new MimeMultipart();
			MimeBodyPart objMimeBodyPart = new MimeBodyPart();
			objMimeBodyPart.setFileName("eancom-orders.txt");
			String strContenido = this.traducirOrden();
			if (objEmpresa.getUsaPGP()) {
				GnuPG pgp = new GnuPG();
				String keyID = objEmpresa.getLlavePGP();
				boolean result;
				result = pgp.encrypt(strContenido, keyID);
				if (result) {
					strContenido = pgp.getResult();
				}
			}
			objMimeBodyPart.setText(strContenido);
			objMultipart.addBodyPart(objMimeBodyPart);
			message.setContent(objMultipart);
			Transport.send(message);
		} catch (Exception e) {
			log.info("Metodo enviarEmail Exception: " + e);
			throw new Exception(e.getMessage());
		}

	}

	private String traducirOrden() throws Exception {

		String strEAN = "";
		try {
			if (this.objOrder != null) {
				strEAN += this.header();
				strEAN += this.items();
				if (this.arrayItems != null)
					strEAN += this.aggregation(arrayItems.size());
			}
		} catch (Exception e) {
			log.info("Metodo traducirOrden Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return strEAN;
	}

	private String header() throws Exception {
		String formatted;
		String template = "UNB+UNOA:3+%s:14+%s:14+%s:%s+%s'" + "UNH+%s+ORDERS:D:95A:UN'BGM+105+%s+9+AB'" + "DTM+137:%s:203'DTM+2:%s:203'"
				+ "FTX+AAI+1+055::9'NAD+BY+%s::9++%s:::%s::1+++10101++NI'" + "CTA+OC+%s:%s'COM+%s:TE'" + "NAD+SE+%s::9++%s:::%s::1+++10101++NI'" + "CTA+OC+%s:%s'COM+%s:TE'"
				+ "NAD+DP+%s::9++%s:::" + "%s::1+++10101++NI'";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat shf = new SimpleDateFormat("HHmm");
			SimpleDateFormat sdhf = new SimpleDateFormat("yyyyMMddHHmm");
			String fechaIntercambio = sdf.format(objOrder.getFechaIntercambio());
			String fechaOrden = sdhf.format(objOrder.getFechaOrden());
			String fechaEntrega = sdhf.format(objOrder.getFechaEntrega());
			String horaIntercambio = shf.format(objOrder.getFechaIntercambio());
			String consecutivoMensaje = objOrder.getSeqIntercambio();
			formatted = String.format(template, objOrder.getIdKey().getGlnCliente(), objOrder.getGlnProveedor(), fechaIntercambio, horaIntercambio, objOrder.getSeqIntercambio(),
					consecutivoMensaje, objOrder.getIdKey().getNumeroOrden(), fechaOrden, fechaEntrega, objOrder.getIdKey().getGlnCliente(), objOrder.getClienteCedula(),
					objOrder.getClienteNombre(), objOrder.getClienteContactoDepto(), objOrder.getClienteContactoEmpleado(), objOrder.getClienteContactoTelefono(),
					objOrder.getGlnProveedor(), objOrder.getProveedorCedula(), objOrder.getProveedorNombre(), objOrder.getProveedorContactoDepto(),
					objOrder.getProveedorContactoEmpleado(), objOrder.getProveedorContactoTelefono(), objOrder.getLugarEntregaGlnCliente(), objOrder.getLugarEntregaCedula(),
					objOrder.getLugarEntregaNombre());
		} catch (Exception e) {
			log.info("Metodo header Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return formatted;
	}

	private String items() throws Exception {
		int i = 1;
		String items = "";
		try {
			if (this.arrayItems != null) {
				for (OrderItem orderItem : listOrderItems) {
					items += this.item(orderItem, i);
					i++;
				}
			}
		} catch (Exception e) {
			log.info("Metodo header Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return items;
	}

	private String item(OrderItem item, int i) throws Exception {
		String formatted;
		String lineas = "LIN+%s++%s:UP'QTY+21:%s'" + "MOA+203:%s'PRI+CAL:%s:PC::%s'" + "TAX+7+LOC+++:::%s:2+S'" + "MOA+120:%s'" + "ALC+H++2+1'" + "MOA+204:%s'";
		try {
			formatted = String.format(lineas, i, item.getCodigo(), item.getCantidad(), item.getMontoMonetario(), item.getPrecio(), item.getPrecio(), item.getIvaPorciento(),
					item.getIvaMonto(), item.getDescuentoMonto());
		} catch (Exception e) {
			log.info("Metodo item Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return formatted;
	}

	private String aggregation(int itemCount) throws Exception {
		String formatted;
		String resumen = "UNS+S'MOA+124:%s'" + "MOA+138:%s'MOA+116:%s'" + "CNT+2:%s'CNT+11:%s'" + "UNT+%s+%s'" + "UNZ+1+%s'";
		try {
			int segmentos = 19 + (itemCount * 8);
			formatted = String.format(resumen, objOrder.getTotalIva(), objOrder.getTotalDescuento(), objOrder.getTotal(), itemCount, objOrder.getTotalPaquetes(), segmentos,
					objOrder.getSeqIntercambio(), objOrder.getSeqIntercambio());
		} catch (Exception e) {
			log.info("Metodo item Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return formatted;
	}

}
