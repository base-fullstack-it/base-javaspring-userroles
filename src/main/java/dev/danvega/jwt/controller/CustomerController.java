package dev.danvega.jwt.controller;
import dev.danvega.jwt.repository.CustomerRepository;
import dev.danvega.jwt.security.perms.CustomerReadPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/customers")
@Controller
public class CustomerController {

    //ToDO: Add service
    private final CustomerRepository customerRepository;

    @CustomerReadPermission
    @RequestMapping("/find")
    public String findCustomers() {
//        model.addAttribute("customer", Customer.builder().build());
        return "You made it";
    }

}
