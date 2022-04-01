package com.gl.customerManagement;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(int theId);

	public void saveCustomer(Customer theCustomer);

	public void deleteById(int theId);
}
