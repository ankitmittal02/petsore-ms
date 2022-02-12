package com.pets.petstore.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pets.petstore.model.PetStoreModel;
import com.pets.petstore.service.PetStoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetStoreServiceImpl implements PetStoreService {

	private final RestTemplate restTemplate;

	@Autowired
	private Environment environment;

	public PetStoreServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public List<PetStoreModel> getAllPets() {
		ResponseEntity<PetStoreModel[]> responseEntity = restTemplate.exchange(getMSUrl(), HttpMethod.GET, null,
				PetStoreModel[].class);
		List<PetStoreModel> pets = Arrays.asList(responseEntity.getBody());

		return pets;
	}

	@Override
	public PetStoreModel create(PetStoreModel petStoreModel) {

		System.out.println("petStoreModel :: " + petStoreModel.toString());

		// post using postForEntity()
		ResponseEntity<PetStoreModel> response = restTemplate.postForEntity(getMSUrl(), petStoreModel,
				PetStoreModel.class);
		System.out.println("Response with postForEntity()...");
		System.out.println(response.getBody().toString());
		System.out.println("Response code:: " + response.getStatusCodeValue());
		// post using postForObject()
		PetStoreModel postResponse = null;
		try {
			postResponse = restTemplate.postForObject(getMSUrl(), petStoreModel, PetStoreModel.class);
			System.out.println("\nResponse with postForObject()...");
			System.out.println(postResponse.toString());

		} catch (Exception ex) {
			log.error(" Exception occured!!");
		}

		return postResponse;
	}

	private String getMSUrl() {
		return environment.getProperty("microserviceUrl.petStoreMicroServiceUrl");
	}

	@Override
	public PetStoreModel getPetsById(Integer id) {

		String url = getMSUrl().concat("/" + id);
		log.info(" url for get by Id ::" + url);
		ResponseEntity<PetStoreModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				PetStoreModel.class);

		log.info("responseEntity ::" + responseEntity.getBody().toString());
		return responseEntity.getBody();
	}

	@Override
	public void deletePet(Integer id) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", id);
		String url = getMSUrl().concat("/" + id);
		log.info(" url for get by Id ::" + url);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url);
	}

}
