package dev.danvega.jwt.bootstrap;

import dev.danvega.jwt.repository.security.AuthorityRepository;
import dev.danvega.jwt.repository.security.RoleRepository;
import dev.danvega.jwt.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DLoader implements CommandLineRunner {
//    private final BreweryRepository breweryRepository;
//    private final BeerRepository beerRepository;
//    private final BeerInventoryRepository beerInventoryRepository;
//    private final BeerOrderRepository beerOrderRepository;
//    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

    }
}
