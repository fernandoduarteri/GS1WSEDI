package services;

import dao.EmpresaDAO;
import model.ObjectReturn;

public class EmpresaServices {

	public void getall(ObjectReturn objReturn) throws Exception{
		EmpresaDAO objEmpresaDao = new EmpresaDAO();
		objEmpresaDao.getall(objReturn);
		
	}

	public void crearEmpresa(ObjectReturn objReturn) throws Exception{
		// TODO Auto-generated method stub
		
	}

	public void actualizarEmpresa(ObjectReturn objReturn) throws Exception{
		// TODO Auto-generated method stub
		
	}

}
