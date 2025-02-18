package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    public static Map<String, Customer> customersMap = new HashMap<>();
    public static void addCustomer(String email, String firstName, String lastName) {
        customersMap.put(email, new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String customerEmail) {
        return customersMap.getOrDefault(customerEmail, null);
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        for(String email: customersMap.keySet()) {
            customerList.add(customersMap.get(email));
        }
        return customerList;
    }
}
