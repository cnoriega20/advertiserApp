package com.advertiser.rest.api.resources;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.advertiser.rest.api.controller.AdvertiserController;
import com.advertiser.rest.api.entity.Advertiser;

@Component
public class AdvertiserResourceAssembler implements ResourceAssembler<Advertiser, Resource<Advertiser>> {

	
	@Override
	public Resource<Advertiser> toResource(Advertiser advertiser) {
		// TODO Auto-generated method stub
		return new Resource<>(advertiser,
				linkTo(methodOn(AdvertiserController.class).getAdvertiser(advertiser.getId())).withSelfRel(),
				linkTo(methodOn(AdvertiserController.class).getAllAdvertisers()).withRel("advertisers"));
	}

}
