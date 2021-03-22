package com.luminum.partsunlimitedapp.repository;

import com.luminum.partsunlimitedapp.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("carsRepositoryBean")
public interface CarsRepository extends CrudRepository<Car, Integer> {

    Car findAllById(int id);

}
