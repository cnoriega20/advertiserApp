package com.advertiser.rest.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.advertiser.rest.api.entity.Advertiser;
import com.advertiser.rest.api.exceptions.AdvertiserNotFoundException;
import com.advertiser.rest.api.model.AdvertiserMapper;
import com.advertiser.rest.api.resources.AdvertiserResourceAssembler;

@RestController
public class AdvertiserController {

	private AdvertiserResourceAssembler advertiserResourceAssembler;	
	private AdvertiserMapper advertiserMapper;
		

	public AdvertiserController(AdvertiserResourceAssembler advertiserResourceAssembler, AdvertiserMapper advertiserMapper) {
		
		this.advertiserResourceAssembler =  advertiserResourceAssembler;
		this.advertiserMapper = advertiserMapper;
		
	}
	
	@GetMapping("/api/advertisers")
	public Resources<Resource<Advertiser>> getAllAdvertisers(){
		List<Resource<Advertiser>> advertisers = advertiserMapper.getAllAdvertisers().stream()
				.map(advertiserResourceAssembler :: toResource)
				.collect(Collectors.toList());
				
		return new Resources<>(advertisers, 
				linkTo(methodOn(AdvertiserController.class).getAllAdvertisers()).withSelfRel());
	}
	
	@GetMapping("/api/advertisers/{id}")
	public Resource<Advertiser> getAdvertiser( @PathVariable Long id){
		
		Advertiser advertiser = advertiserMapper.getAdvertiserById(id);
		if(advertiser != null){
			return advertiserResourceAssembler.toResource(advertiser);
		}
				//.orElseThrow(() -> new AdvertiserNotFoundException(id));
		else{
			throw new AdvertiserNotFoundException(id);
		}
		
	}
	
	@PostMapping("/api/advertisers")
	public ResponseEntity<Void> createAdvertiser(@RequestBody Advertiser advertiser) {
		
		int advertiserIndicator = advertiserMapper.createAdvertiser(advertiser);
		if(advertiserIndicator == 0) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
				
	}
	
	@PutMapping("/api/advertisers/{id}")
	public ResponseEntity<Object>updateAdvertiser(@RequestBody Advertiser newAdvertiser, @PathVariable Long id) throws URISyntaxException{
		Advertiser existingAdvertiser = advertiserMapper.getAdvertiserById(id);
		if(null == existingAdvertiser){
			return ResponseEntity.notFound().build();
		}
		
		newAdvertiser.setFirstName(existingAdvertiser.getFirstName());
		newAdvertiser.setLastName(existingAdvertiser.getLastName());
		newAdvertiser.setCreditLimit(existingAdvertiser.getCreditLimit());
		
		advertiserMapper.updateAdvertiser(newAdvertiser);
		
		return ResponseEntity.noContent().build();
		
		/*Integer updatedAdvertiser = advertiserMapper.getAdvertiserById(id)
				.map(advertiser -> {
					advertiser.setFirstName(newAdvertiser.getFirstName());
					advertiser.setLastName(newAdvertiser.getLastName());
					advertiser.setCreditLimit(newAdvertiser.getCreditLimit());
					return advertiserMapper.createAdvertiser(advertiser);
				})
				.orElseGet(() -> {
					newAdvertiser.setId(id);
					return advertiserMapper.createAdvertiser(newAdvertiser);
				});
		
		//Resource<Advertiser> resource = advertiserResourceAssembler.toResource(updatedAdvertiser);
		return ResponseEntity
				.created(new URI(resour)))
				*/
	}
	
	@DeleteMapping("/api/advertisers/{id}")
	public ResponseEntity<?> deleteAdvertiser(@PathVariable Long id){
		advertiserMapper.deleteAdvertiser(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
