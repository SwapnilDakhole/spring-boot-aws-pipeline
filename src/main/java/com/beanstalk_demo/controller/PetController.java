package com.beanstalk_demo.controller;

import com.beanstalk_demo.models.Order;
import com.beanstalk_demo.models.Pet;
import com.beanstalk_demo.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PetController {


    @Autowired
    private PetService petService;

    @GetMapping("/pet/{petId}")
    public Mono<Pet> getPetByPetId(@PathVariable Long petId) {
        return petService.getPetByPetId(petId);
    }

    @GetMapping("/order/{id}")
    public Mono<Order> getOrderById(@PathVariable Long id) {
        return petService.getOrderById(id);
    }

    @GetMapping("/inventory")
    public Mono<String> getStoreInventory() {
        return petService.getStoreInventory();
    }
}
