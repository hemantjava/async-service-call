package com.hemant.example.async.controller;

import com.hemant.example.async.model.EmployeeAddresses;
import com.hemant.example.async.model.EmployeeNames;
import com.hemant.example.async.service.AsyncService;
import com.hemant.example.async.model.EmployeePhone;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Log4j2
public class AsyncController {
	private final AsyncService service;

	public AsyncController(AsyncService service) {
		this.service = service;
	}

	@RequestMapping(value = "/testAsync", method = RequestMethod.GET)
	public String testAsynch() throws InterruptedException, ExecutionException
	{
		log.info("testAsync Start");
		CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
		CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
		CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();

		// Wait until they are all done
		CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();
		
		log.info("EmployeeAddress--> " + employeeAddress.get());
		log.info("EmployeeName--> " + employeeName.get());
		log.info("EmployeePhone--> " + employeePhone.get());
		return "Done";
	}
}
