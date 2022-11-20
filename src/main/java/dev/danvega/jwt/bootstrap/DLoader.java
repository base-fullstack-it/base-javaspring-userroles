package dev.danvega.jwt.bootstrap;

import dev.danvega.jwt.domain.*;
import dev.danvega.jwt.domain.security.Authority;
import dev.danvega.jwt.domain.security.Role;
import dev.danvega.jwt.domain.security.User;
import dev.danvega.jwt.enums.OrderStatusEnum;
import dev.danvega.jwt.repository.*;
import dev.danvega.jwt.repository.security.AuthorityRepository;
import dev.danvega.jwt.repository.security.RoleRepository;
import dev.danvega.jwt.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class DLoader implements CommandLineRunner {
    private final BreweryRepository breweryRepository;
    private final BeerRepository beerRepository;
    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerOrderRepository beerOrderRepository;
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String ST_PETE_DISTRIBUTING = "St Pete Distributing";
    public static final String DUNEDIN_DISTRIBUTING = "Dunedin Distributing";
    public static final String KEY_WEST_DISTRIBUTORS = "Key West Distributors";
    public static final String STPETE_USER = "stpete";
    public static final String DUNEDIN_USER = "dunedin";
    public static final String KEYWEST_USER = "keywest";

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
//        loadSecurityData();
//        loadBreweryData();
//        loadTastingRoomData();
//        loadCustomerData();
    }

    private void loadSecurityData() {
        if(roleRepository.count() != 0) return;
        //beer auths
        Authority createBeer = authorityRepository.save(Authority.builder().permission("beer.create").build());
        Authority readBeer = authorityRepository.save(Authority.builder().permission("beer.read").build());
        Authority updateBeer = authorityRepository.save(Authority.builder().permission("beer.update").build());
        Authority deleteBeer = authorityRepository.save(Authority.builder().permission("beer.delete").build());

        //customer auths
        Authority createCustomer = authorityRepository.save(Authority.builder().permission("customer.create").build());
        Authority readCustomer = authorityRepository.save(Authority.builder().permission("customer.read").build());
        Authority updateCustomer = authorityRepository.save(Authority.builder().permission("customer.update").build());
        Authority deleteCustomer = authorityRepository.save(Authority.builder().permission("customer.delete").build());

        //customer brewery
        Authority createBrewery = authorityRepository.save(Authority.builder().permission("brewery.create").build());
        Authority readBrewery = authorityRepository.save(Authority.builder().permission("brewery.read").build());
        Authority updateBrewery = authorityRepository.save(Authority.builder().permission("brewery.update").build());
        Authority deleteBrewery = authorityRepository.save(Authority.builder().permission("brewery.delete").build());

        //beer order
        Authority createOrder = authorityRepository.save(Authority.builder().permission("order.create").build());
        Authority readOrder = authorityRepository.save(Authority.builder().permission("order.read").build());
        Authority updateOrder = authorityRepository.save(Authority.builder().permission("order.update").build());
        Authority deleteOrder = authorityRepository.save(Authority.builder().permission("order.delete").build());
        Authority pickupOrder = authorityRepository.save(Authority.builder().permission("order.pickup").build());
        Authority createOrderCustomer = authorityRepository.save(Authority.builder().permission("customer.order.create").build());
        Authority readOrderCustomer = authorityRepository.save(Authority.builder().permission("customer.order.read").build());
        Authority updateOrderCustomer = authorityRepository.save(Authority.builder().permission("customer.order.update").build());
        Authority deleteOrderCustomer = authorityRepository.save(Authority.builder().permission("customer.order.delete").build());
        Authority pickupOrderCustomer = authorityRepository.save(Authority.builder().permission("customer.order.pickup").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role customerRole = roleRepository.save(Role.builder().name("CUSTOMER").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(createBeer, updateBeer, readBeer, deleteBeer, createCustomer, readCustomer,
                updateCustomer, deleteCustomer, createBrewery, readBrewery, updateBrewery, deleteBrewery,
                createOrder, readOrder, updateOrder, deleteOrder, pickupOrder)));

        customerRole.setAuthorities(new HashSet<>(Set.of(readBeer, readCustomer, readBrewery, createOrderCustomer, readOrderCustomer,
                updateOrderCustomer, deleteOrderCustomer, pickupOrderCustomer)));

        userRole.setAuthorities(new HashSet<>(Set.of(readBeer)));

        roleRepository.saveAll(Arrays.asList(adminRole, customerRole, userRole));
    }
    private void loadBreweryData() {
        if (breweryRepository.count() != 0) return;
            breweryRepository.save(Brewery
                    .builder()
                    .breweryName("Cage Brewings")
                    .build());

            Beer mangoBobs = Beer.builder()
                    .beerName("Mango Bobs")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build();

            beerRepository.save(mangoBobs);
            beerInventoryRepository.save(BeerInventory.builder()
                    .beer(mangoBobs)
                    .quantityOnHand(500)
                    .build());

            Beer galaxyCat = Beer.builder()
                    .beerName("Galaxy Cat")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build();

            beerRepository.save(galaxyCat);
            beerInventoryRepository.save(BeerInventory.builder()
                    .beer(galaxyCat)
                    .quantityOnHand(500)
                    .build());

            Beer pinball = Beer.builder()
                    .beerName("Pinball Porter")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .build();

            beerRepository.save(pinball);
            beerInventoryRepository.save(BeerInventory.builder()
                    .beer(pinball)
                    .quantityOnHand(500)
                    .build());

//        }
    }
    private void loadTastingRoomData() {
        if(beerOrderRepository.count() != 0) return;
        Customer tastingRoom = Customer.builder()
                .name(TASTING_ROOM)
                .build();

        customerRepository.save(tastingRoom);

        beerRepository.findAll().forEach(beer -> {
            beerOrderRepository.save(BeerOrder.builder()
                    .customer(tastingRoom)
                    .orderStatus(OrderStatusEnum.placed)
                    .beerOrderLines(Set.of(BeerOrderLine.builder()
                            .beer(beer)
                            .orderQuantity(2)
                            .build()))
                    .build());
        });
    }
    private void loadCustomerData() {
        Role customerRole = roleRepository.findByName("CUSTOMER").orElseThrow();

        //create customers
        Customer stPeteCustomer = customerRepository.save(Customer.builder()
                .name(ST_PETE_DISTRIBUTING)
//                .apiKey(UUID.randomUUID())
                .build());

        Customer dunedinCustomer = customerRepository.save(Customer.builder()
                .name(DUNEDIN_DISTRIBUTING)
//                .apiKey(UUID.randomUUID())
                .build());

        Customer keyWestCustomer = customerRepository.save(Customer.builder()
                .name(KEY_WEST_DISTRIBUTORS)
//                .apiKey(UUID.randomUUID())
                .build());

        //create users
        User stPeteUser = userRepository.save(User.builder().username(STPETE_USER)
                .password(passwordEncoder.encode("password"))
                .customer(stPeteCustomer)
                .role(customerRole).build());

        User dunedinUser = userRepository.save(User.builder().username(DUNEDIN_USER)
                .password(passwordEncoder.encode("password"))
                .customer(dunedinCustomer)
                .role(customerRole).build());

        User keywest = userRepository.save(User.builder().username(KEYWEST_USER)
                .password(passwordEncoder.encode("password"))
                .customer(keyWestCustomer)
                .role(customerRole).build());

        //create orders
        createOrder(stPeteCustomer);
        createOrder(dunedinCustomer);
        createOrder(keyWestCustomer);

//        log.debug("Orders Loaded: " + beerOrderRepository.count());
    }

    private BeerOrder createOrder(Customer customer) {
        return  beerOrderRepository.save(BeerOrder.builder()
                .customer(customer)
                .orderStatus(OrderStatusEnum.placed)
                .beerOrderLines(Set.of(BeerOrderLine.builder()
                        .beer(beerRepository.findById(1).orElseThrow())
                        .orderQuantity(2)
                        .build()))
                .build());
    }


}
