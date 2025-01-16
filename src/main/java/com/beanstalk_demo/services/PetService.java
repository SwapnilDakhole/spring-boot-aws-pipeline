package com.beanstalk_demo.services;


import com.beanstalk_demo.constant.ApiEndpoint;
import com.beanstalk_demo.models.Order;
import com.beanstalk_demo.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PetService {


//    @Autowired
//    private  RestTemplate restTemplate;
//
//
//    public ResponseEntity<Pet> fetchDataFromApi() {
//        // Assuming the endpoint is a GET request, and you need to hit the Swagger endpoint
//        ResponseEntity<Pet> response = restTemplate.getForEntity(ApiEndpoint.API_URL + "pet/findPetsByStatus", Pet.class);
//        return response;  // Return the data from the API response
//    }

    private final WebClient webClient;

    public PetService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiEndpoint.API_URL).build();
    }


    public Mono<Pet> getPetByPetId(Long petId) {
        return webClient.get()
                .uri("pet/{petId}", petId)
                .retrieve()
                .bodyToMono(Pet.class);
    }

    public Mono<Order> getOrderById(Long id) {
        return webClient.get()
                .uri("store/order/{orderId}", id)
                .retrieve()
                .bodyToMono(Order.class);
    }

    public Mono<String> getStoreInventory() {
        return webClient.get()
                .uri("store/inventory")
                .retrieve()
                .bodyToMono(String.class);
    }
}
