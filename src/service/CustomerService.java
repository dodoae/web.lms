package service;

import java.util.List;

import entity.CustomerEntity;
import vo.BoardVO;
import vo.CustomerVO;

public class CustomerService {
	private CustomerEntity ce;
	
	public CustomerService() {
		ce = new CustomerEntity();
	}
	
	public void insertCustomer(CustomerVO vo) {
		ce.insertCustomer(vo);
	}

	public List getArticles(int startRow, int endRow) throws Exception {
		return ce.getArticles(startRow, endRow);
	}
	public CustomerVO getCustomer(CustomerVO vo) throws Exception {
		return ce.getCustomer(vo);
	}
	/*public CustomerVO updateGetCustomer(CustomerVO vo) throws Exception {
		return ce.updateGetCustomer(vo);
	}*/
	public void updateCustomer(CustomerVO vo) throws Exception {
		ce.updateCustomer(vo);
	}
	public void deleteCustomer(CustomerVO vo) {
		ce.deleteCustomer(vo);
	}

	public int getCustomerCount() {
		return ce.getCustomerCount();
	}
}
