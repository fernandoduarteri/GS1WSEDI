package dao;

import java.util.logging.Logger;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.OrderItem;
import persist.JPAEntity;

public class OrderItemDAO extends JPAEntity<OrderItem> {

	public OrderItemDAO(Class<OrderItem> entityClass) {
		super(entityClass);
	}

	private Logger log = Logger.getLogger(getClass().getName());

	public void getall(ObjectReturn objReturn) {
		try {
			objReturn.setData(super.findAll());
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(super.count());
		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
		}

	}
	
	public void salvar(ObjectReturn objReturn) throws Exception {
		try {
			super.create((OrderItem) objReturn.getData());
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(1);
		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
			log.info("Metodo salvar Exception: " + e);
		}
	}


}
