package com.bookpalace.service;

import com.bookpalace.converter.CustomerConverter;
import com.bookpalace.dto.request.CustomerChangeAccountTypeRequest;
import com.bookpalace.dto.request.CustomerUpdateRequest;
import com.bookpalace.dto.response.CustomerResponse;
import com.bookpalace.exception.ExceptionMessages;
import com.bookpalace.exception.KitapYurdumException;
import com.bookpalace.model.Customer;
import com.bookpalace.repository.CustomerRepository;
import com.bookpalace.dto.request.CustomerSaveRequest;
import com.bookpalace.model.enums.AccountType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository; //yönetim artık spring'te

    public CustomerResponse save(CustomerSaveRequest request) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(request.getEmail());

        if (foundCustomer.isPresent()) {
            log.error(ExceptionMessages.EMAIL_ALREADY_EXIST);
            throw new KitapYurdumException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }

        Customer customer = CustomerConverter.toCustomer(request);
        customerRepository.createCustomer(customer);

        log.info("customer created. {}", customer.getEmail());
        return CustomerConverter.toResponse(customer);
    }

    public CustomerResponse update(CustomerUpdateRequest request) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(request.getCurrentEmail());
        if (foundCustomer.isEmpty()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_FOUND);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Optional<Customer> foundExistingCustomer = customerRepository.findByEmail(request.getDesiredEmail());
        if(foundExistingCustomer.isPresent()) {
            log.error(ExceptionMessages.EMAIL_ALREADY_EXIST);
            throw new KitapYurdumException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }
        Customer customer = foundCustomer.get();
        customer.setEmail(request.getDesiredEmail());
        customer.setAddress(request.getAddress());

        return CustomerConverter.toResponse(customer);


    }

    public List<CustomerResponse> getCustomerList() {
        return CustomerConverter.toResponse(customerRepository.getCustomerList());

    }

    public void changeAccountType(String email, AccountType accountType) {

        Optional<Customer> foundCustomer = customerRepository.getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            foundCustomer.get().setAccountType(accountType);
        }
    }



    public CustomerResponse getById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        if (foundCustomer.isEmpty()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_FOUND);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        return CustomerConverter.toResponse(foundCustomer.get());
    }

    public CustomerResponse getByEmail(String email) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (!foundCustomer.get().getIsActive()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
        }

        return CustomerConverter.toResponse(foundCustomer.get());
    }
    public Customer getCustomerByEmail(String email) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (!foundCustomer.get().getIsActive()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
        }

        return foundCustomer.get();
    }

    public CustomerResponse changeAccountTypeByCredit(CustomerChangeAccountTypeRequest request) { //ödev

        Map<AccountType,Integer> requiredCredits = new HashMap<>();
        requiredCredits.put(AccountType.SILVER,1000);
        requiredCredits.put(AccountType.GOLD,2000);
        requiredCredits.put(AccountType.PLATINUM,4000);

        Optional<Customer> foundCustomer = customerRepository.getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(request.getEmail()))
                .findFirst();
        if(foundCustomer.isPresent()) {
            Customer customer = foundCustomer.get();
            if(customer.getCredit()>=requiredCredits.get(AccountType.valueOf(request.getAccountType()))){
                customer.setAccountType(AccountType.valueOf(request.getAccountType()));
                customer.setCredit(customer.getCredit()-requiredCredits.get(AccountType.valueOf(request.getAccountType())));
                return CustomerConverter.toResponse(customer);
            }
            else{
                log.error(ExceptionMessages.NOT_ENOUGH_CREDITS);
                throw new KitapYurdumException(ExceptionMessages.NOT_ENOUGH_CREDITS);

            }
        }
        else{
            log.error(ExceptionMessages.CUSTOMER_NOT_FOUND);
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }


    }
}
