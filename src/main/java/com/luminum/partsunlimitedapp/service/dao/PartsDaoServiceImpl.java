package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.dto.CountResponseDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.model.Brand;
import com.luminum.partsunlimitedapp.model.Car;
import com.luminum.partsunlimitedapp.model.Part;
import com.luminum.partsunlimitedapp.repository.BrandsRepository;
import com.luminum.partsunlimitedapp.repository.CarsRepository;
import com.luminum.partsunlimitedapp.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("partsBean")
public class PartsDaoServiceImpl implements PartsDaoService {

    private final PartsRepository partsRepository;
    private final CarsRepository carsRepository;
    private final BrandsRepository brandsRepository;

    @Autowired
    public PartsDaoServiceImpl(
            PartsRepository partsRepository,
            CarsRepository carsRepository,
            BrandsRepository brandsRepository) {
        this.partsRepository = partsRepository;
        this.carsRepository = carsRepository;
        this.brandsRepository = brandsRepository;
    }

    @Override
    public Part addPart(PartDto partDto) {
        Part part = new Part();

        part.setPartName(partDto.getPartName());
        part.setDateManufactured(partDto.getDateManufactured());
        part.setCar(carsRepository.findAllById(partDto.getIdCar()));

        return partsRepository.save(part);
    }

    @Override
    public void deletePart(int id) {
        partsRepository.deleteById(id);
    }

    @Override
    public List<Part> searchPartsByCarId(int id) {
        return partsRepository.findAllByCarId(id);
    }

    @Override
    public List<Part> searchPartsByDate(LocalDate date) {
        return partsRepository.findAllByDateManufactured(date);
    }

    @Override
    public List<CountResponseDto> listAndCountPartsByBrandAndModel() {
        return partsRepository.countByBrandAndCar();
    }

    @Override
    public List<CountResponseDto> listAndCountPartsByCertainBrandAndModel(String brandName, String modelName) {

        List<CountResponseDto> countResponsDtos = new ArrayList<>();

        long count = partsRepository.countAllByCar_Brand_BrandNameAndCar_ModelName(brandName, modelName);

        CountResponseDto countResponseDto = new CountResponseDto();

        countResponseDto.setCount((int) count);
        countResponseDto.setBrandAndModel(brandName + " " + modelName);

        countResponsDtos.add(countResponseDto);
        return countResponsDtos;
    }

    @Override
    public List<Part> findAll() {
        return (List<Part>) partsRepository.findAll();
    }

    @Override
    public Optional<Part> findById(int id) {
        return partsRepository.findById(id);
    }

    @Override
    public List<Part> findAllByBrandAndModel(String brandName, String modelName) {
        return partsRepository.findAllByCar_Brand_BrandNameAndCar_ModelName(brandName, modelName);
    }
}
