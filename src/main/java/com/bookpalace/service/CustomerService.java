package com.bookpalace.service;

import com.bookpalace.converter.CustomerConverter;
import com.bookpalace.exception.ExceptionMessages;
import com.bookpalace.exception.KitapYurdumException;
import com.bookpalace.model.Customer;
import com.bookpalace.repository.CustomerRepository;
import com.bookpalace.dto.request.CustomerSaveRequest;
import com.bookpalace.model.enums.AccountType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository; //yönetim artık spring'te

    public void save(CustomerSaveRequest request) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(request.getEmail());

        if (foundCustomer.isPresent()) {
            log.error(ExceptionMessages.EMAIL_ALREADY_EXIST);
            throw new KitapYurdumException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }

        Customer customer = CustomerConverter.toCustomer(request);

        customerRepository.createCustomer(customer);

        log.info("customer created. {}", customer.getEmail());
    }

    public List<Customer> getCustomerList() {
        return customerRepository.getCustomerList();
    }

    public void changeAccountType(String email, AccountType accountType) {

        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            foundCustomer.get().setAccountType(accountType);
        }
    }

    public void changeAccountTypeByCredit(String email, AccountType accountType) { //ödev

    }

    public Customer getById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        if (foundCustomer.isEmpty()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_FOUND);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        return foundCustomer.get();
    }

    public Customer getByEmail(String email) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (!foundCustomer.get().getIsActive()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
        }

        return foundCustomer.get();
    }
}
