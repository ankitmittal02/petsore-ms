package com.pets.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pets.petstore.model.PetStoreModel;

@Service
public interface PetStoreService {

	public List<PetStoreModel> getAllPets();
	
	public PetStoreModel create(PetStoreModel petStoreModel);
	
	public PetStoreModel getPetsById(Integer id);
	
	public void deletePet(Integer id);
	
}
