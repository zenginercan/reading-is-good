/*
package com.getir.readingisgood.config;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService {

    //private static final Logger log = LoggerFactory.getLogger(CustomerUserDetailService.class);

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = (Customer)this.customerRepository.findByPhoneNumber(Long.valueOf(userName));
        if(customer == null){
            new UsernameNotFoundException("Customer not found with phone number: " + userName);
        }
        return createPrincipal(customer);
    }

    private UserDetails createPrincipal(Customer customer) {
        UserPrinciple userPrinciple = UserPrinciple.constructUserPrincipal(customer);
        return (UserDetails)userPrinciple;
    }

    public UserPrinciple loadUserByPhoneNumber(Long phoneNumber) {
        Customer customer = (Customer)this.customerRepository.findByPhoneNumber(phoneNumber);
        if(customer == null){
            new UsernameNotFoundException("User not found!");
        }
        return UserPrinciple.constructUserPrincipal(customer);
    }
}
*/
