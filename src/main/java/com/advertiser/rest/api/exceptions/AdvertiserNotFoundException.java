package com.advertiser.rest.api.exceptions;

@SuppressWarnings("serial")
public class AdvertiserNotFoundException extends RuntimeException {

	public AdvertiserNotFoundException(Long id){
		super("The advertiser with id " + id + "is not found");
	}
}
