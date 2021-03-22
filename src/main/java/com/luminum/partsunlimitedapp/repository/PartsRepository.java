package com.luminum.partsunlimitedapp.repository;

import com.luminum.partsunlimitedapp.dto.CountResponseDto;
import com.luminum.partsunlimitedapp.model.Part;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("partsRepositoryBean")
@Transactional
public interface PartsRepository extends CrudRepository<Part, Integer> {

    List<Part> findAllByDateManufactured(LocalDate dateManufactured);

    List<Part> findAllByCarId(int id);

    long countAllByCar_Brand_BrandNameAndCar_ModelName(String brandName, String modelName);

    List<Part> findAllByCar_Brand_BrandNameAndCar_ModelName(String brandName, String modelName);

    @Query("SELECT new com.luminum.partsunlimitedapp.dto.CountResponseDto(p.car.brand.brandName, p.car.modelName, COUNT(p.id)) "
            + "FROM Part AS p GROUP BY p.car.brand.brandName, p.car.modelName order by p.car.brand.brandName, p.car.modelName asc")
    List<CountResponseDto> countByBrandAndCar();

    Optional<Part> findById(int id);
}
