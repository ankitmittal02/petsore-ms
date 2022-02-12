package com.pets.petstore.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pets.petstore.model.PetStoreModel;
import com.pets.petstore.service.PetStoreService;
 
@ExtendWith(MockitoExtension.class)
public class PetStoreControllerTest {

	@InjectMocks
	private PetStoreController petStoreController;

    @Mock
    PetStoreModel petStoreModel;
    
    @Mock
    PetStoreService petStoreService;
    
    @Test
    public void testFindAll() 
	{
		// given
		PetStoreModel pet1 = new PetStoreModel(1, "Dog", 2.45f);
		PetStoreModel pet2 = new PetStoreModel(2, "Cat", 1.00f);
		List<PetStoreModel> pets = new ArrayList<>();
		pets.add(pet1);
		pets.add(pet2);

		when(petStoreService.getAllPets()).thenReturn(pets);

		// when
		List<PetStoreModel> result = petStoreController.get();

		// then
		assertThat(result.size()).isEqualTo(2);

		assertThat(result.get(0).getType()).isEqualTo(pet1.getType());

		assertThat(result.get(1).getType()).isEqualTo(pet2.getType());
	}
    
    
    @Test
    public void addPet() 
	{
		// given
		PetStoreModel pet3 = new PetStoreModel(3, "fish", 0.99f);

		// when
		when(petStoreService.create(pet3)).thenReturn(pet3);

		PetStoreModel result = petStoreController.create(pet3);
		
		// then
		assertThat(result.getType()).isEqualTo(pet3.getType());

		assertThat(result.getPrice()).isEqualTo(pet3.getPrice());
	}
    
    @Test
    public void addPetWithNullId() 
	{
		// given
    	PetStoreModel pet1 = new PetStoreModel(1, "Dog", 2.45f);
		PetStoreModel pet2 = new PetStoreModel(2, "Cat", 1.00f);
		PetStoreModel pet3 = new PetStoreModel(null, "fish", 0.99f);
		List<PetStoreModel> pets = new ArrayList<>();
		pets.add(pet1);
		pets.add(pet2);

		// when
		when(petStoreService.getAllPets()).thenReturn(pets);	
		when(petStoreService.create(pet3)).thenReturn(pet3);

		PetStoreModel result = petStoreController.create(pet3);
		
		// then
		assertThat(result.getType()).isEqualTo(pet3.getType());

		assertThat(result.getPrice()).isEqualTo(pet3.getPrice());
	}
    
    @Test
    public void testFindById() 
	{
		// given
		PetStoreModel pet1 = new PetStoreModel(1, "Dog", 2.45f);

		when(petStoreService.getPetsById(1)).thenReturn(pet1);

		// when
		PetStoreModel result = petStoreController.get(1);

		// then

		assertThat(result.getType()).isEqualTo(pet1.getType());

		assertThat(result.getPrice()).isEqualTo(pet1.getPrice());
	}
    

}
