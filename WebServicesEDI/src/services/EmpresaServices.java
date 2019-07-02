package services;

import dao.EmpresaDAO;
import model.Empresa;
import model.ObjectReturn;

public class EmpresaServices {
	EmpresaDAO objEmpresaDao = new EmpresaDAO(Empresa.class);
	
	public void getall(ObjectReturn objReturn) throws Exception{
		objEmpresaDao.getall(objReturn);
	}

	public void crear(ObjectReturn objReturn) throws Exception{
		// TODO Auto-generated method stub
		
	}

	public void actualizar(ObjectReturn objReturn) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	public void getone(ObjectReturn objReturn) throws Exception{
		objEmpresaDao.getOne(objReturn);
	}

}
