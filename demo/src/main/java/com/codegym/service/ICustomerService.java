package com.codegym.service;


import com.codegym.model.Customer;

import java.util.Optional;

public interface ICustomerService {
Optional<Customer> findCustomerByUsername(String username);

}
