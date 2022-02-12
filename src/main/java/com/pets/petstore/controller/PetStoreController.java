package com.pets.petstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets.petstore.model.PetStoreModel;
import com.pets.petstore.service.PetStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/pets")
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;
	
	@GetMapping()
	public List<PetStoreModel> get() {
		return petStoreService.getAllPets();
	}

	@PostMapping()
	public PetStoreModel create(@RequestBody PetStoreModel petStoreRequest) {
		
		log.info("petStoreRequest ::"+ petStoreRequest);
		if(petStoreRequest.getId() == null) {
			List<PetStoreModel> pets = petStoreService.getAllPets();
			int id = pets.size()+1;
			petStoreRequest.setId(id);
		}
		
		return petStoreService.create(petStoreRequest);

	}
	
	@GetMapping("/{id}")
	public PetStoreModel get(@PathVariable("id") Integer id) {
		return petStoreService.getPetsById(id);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable("id") Integer id) {
		petStoreService.deletePet(id);
	}
}
