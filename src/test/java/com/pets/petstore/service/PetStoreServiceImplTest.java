package com.pets.petstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pets.petstore.model.PetStoreModel;

public class PetStoreServiceImplTest {

	@InjectMocks
	private PetStoreService petStoreService;
	
	@Mock
    PetStoreModel petStoreModel;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Mock
	private Environment environment;  
	  
	@Test
	public void testGetAllPets() {

		// given
				PetStoreModel pet1 = new PetStoreModel(1, "Dog", 2.45f);
				PetStoreModel pet2 = new PetStoreModel(2, "Cat", 1.00f);
				List<PetStoreModel> pets = new ArrayList<>();
				pets.add(pet1);
				pets.add(pet2);
				
		when(environment.getProperty("microserviceUrl.petStoreMicroServiceUrl")).thenReturn("test");
				
        when(restTemplate.exchange(environment.getProperty("microserviceUrl.petStoreMicroServiceUrl"),HttpMethod.GET, null,	PetStoreModel.class))
          .thenReturn(new ResponseEntity(pets, HttpStatus.OK));

        List<PetStoreModel> result =  petStoreService.getAllPets();
       
        assertThat(result.size()).isEqualTo(2);
        
    }
	
}
