package com.gl.customerManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		// get Books from db
		List<Customer> theCustomer = customerService.findAll();
		// add to the spring model
		theModel.addAttribute("Customers", theCustomer);
		return "list-Customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		theCustomer.setId(0);
		theModel.addAttribute("Customer", theCustomer);
		return "Customer-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {
		// get the Book from the service
		Customer theCustomer = customerService.findById(theId);
		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", theCustomer);
		// send over to our form
		return "Customer-form";			
	}
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if(id!=0)
		{
			theCustomer=customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		}
		else
			theCustomer=new Customer(firstName, lastName, email);
		// save the Book
		customerService.saveCustomer(theCustomer);
		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		// delete the Student
		customerService.deleteById(theId);
		return "redirect:/customer/list";

	}
}
