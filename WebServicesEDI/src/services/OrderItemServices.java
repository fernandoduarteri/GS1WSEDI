package services;

import dao.OrderItemDAO;
import model.ObjectReturn;
import model.OrderItem;

public class OrderItemServices {
OrderItemDAO objOrderItemDao = new OrderItemDAO(OrderItem.class);
	
	public void getall(ObjectReturn objReturn) throws Exception{
		objOrderItemDao.getall(objReturn);
	}

	public void salvar(ObjectReturn objReturn) throws Exception{
		objOrderItemDao.salvar(objReturn);
		
	}

	public void actualizar(ObjectReturn objReturn) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	

}
