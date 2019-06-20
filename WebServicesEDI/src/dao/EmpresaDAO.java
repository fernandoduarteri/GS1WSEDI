package dao;

import Utilidades.Constantes;
import model.ObjectReturn;
import persist.JPAEntity;

public class EmpresaDAO extends JPAEntity {

	public void getall(ObjectReturn objReturn) throws Exception{
		try {
		objReturn.setData(super.findAll("Empresa"));
		objReturn.setMensaje("Exito");
		objReturn.setExito(Constantes.FLAG_EXITO_EXITO);
		objReturn.setTotal(super.findAll("Empresa").size());
		}catch(Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
		}
	}

}
