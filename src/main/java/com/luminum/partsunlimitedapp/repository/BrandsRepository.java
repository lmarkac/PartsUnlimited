package com.luminum.partsunlimitedapp.repository;

import com.luminum.partsunlimitedapp.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("brandsRepositoryBean")
public interface BrandsRepository extends CrudRepository<Brand, Integer> {



}
