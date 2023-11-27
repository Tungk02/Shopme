package com.shopme.admin.setting.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopme.common.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
	
	List<Country> findAllByOrderByNameAsc();

}
