package com.advertiser.rest.api.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.advertiser.rest.api.entity.Advertiser;

@RunWith(SpringRunner.class)
@MybatisTest
public class AdvertiserRepositoryTests {
	
	@Autowired
	private AdvertiserMapper advertiserMapper;

	@Test
	public void createAdvertiserTest(){
		Advertiser advertiser = new Advertiser("Kamila", "Myers", 7500);
		advertiserMapper.createAdvertiser(advertiser);
		
		Advertiser otherAdvertiser = advertiserMapper.getAdvertiserById(advertiser.getId());
		assertEquals("Kamila", otherAdvertiser.getFirstName());
		assertEquals("Myers", otherAdvertiser.getLastName());
			
	}
	
	@Test
	public void getAdvertiserByIdTest() {
		Advertiser advertiser =  advertiserMapper.getAdvertiserById((long) 10001);
		assertThat(advertiser.getContactName()).isEqualTo("Cesar Noriega");
		assertThat(advertiser.getCreditLimit()).isEqualTo(8500);
	}
	
	@Test
	public void updateAdvertiserTest(){
		Advertiser advertiser =  advertiserMapper.getAdvertiserById(10001L);
		advertiser.setCreditLimit(10000);
		advertiserMapper.updateAdvertiser(advertiser);
		Advertiser updatedAdvertiser = advertiserMapper.getAdvertiserById(10001L);
		assertEquals(10000, updatedAdvertiser.getCreditLimit(), 0.001);
		
	}
	
	@Test
	public void deleteAdvertiserTest(){
		Advertiser advertiser =  advertiserMapper.getAdvertiserById((long) 10001);
		advertiserMapper.deleteAdvertiser(advertiser.getId());
		Advertiser deletedAdvertiser = advertiserMapper.getAdvertiserById((long) 10001);
		assertNull(deletedAdvertiser);
	}
	
}
