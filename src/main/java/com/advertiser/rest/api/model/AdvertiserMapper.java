/**
 * 
 */
package com.advertiser.rest.api.model;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.advertiser.rest.api.entity.Advertiser;

/**
 * @author cnoriega
 *
 */
@Mapper
public interface AdvertiserMapper {

	@Insert("INSERT INTO advertiser(firstName, lastName, creditLimit) VALUES (#{firstName}, #{lastName}, #{creditLimit})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="ID")
	public int createAdvertiser(Advertiser advertiser);
	
	@Update("UPDATE advertiser SET firstName = #{firstName}, lastName = #{lastName}, creditLimit = #{creditLimit} WHERE id = #{id}")
	public void updateAdvertiser(Advertiser advertiser);

	@Select("SELECT * FROM advertiser")
	List<Advertiser> getAllAdvertisers();
	
	@Select("SELECT * FROM advertiser WHERE id = #{id}") 
	public Advertiser getAdvertiserById(Long id);
	
	@Delete("DELETE FROM advertiser WHERE id = #{id}")
	public void deleteAdvertiser(Long id);
	
}
	