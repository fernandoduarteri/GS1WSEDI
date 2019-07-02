package dao;

import java.util.logging.Logger;

import Utilidades.Constantes;
import model.ObjectReturn;
import model.Order;
import persist.JPAEntity;

public class OrdenDAO extends JPAEntity<Order> {

	public OrdenDAO(Class<Order> entityClass) {
		super(entityClass);
	}
//
	private Logger log = Logger.getLogger(getClass().getName());

	public void salvar(ObjectReturn objReturn) throws Exception {
		try {
			super.create((Order) objReturn.getData());
			objReturn.setMensaje("Exito");
			objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
			objReturn.setTotal(1);
		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
			log.info("Metodo salvar de la Clase OrdenDAO Exception: " + e);
		}
	}

}
