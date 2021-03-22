package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.dto.CountResponseDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.model.Part;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PartsDaoService {

    Part addPart(PartDto part);

    void deletePart(int id);

    List<Part> searchPartsByCarId(int id);

    List<Part> searchPartsByDate(LocalDate date);

    List<CountResponseDto> listAndCountPartsByBrandAndModel();

    List<CountResponseDto> listAndCountPartsByCertainBrandAndModel(String modelName, String brandName);

    List<Part> findAll();

    Optional<Part> findById(int id);

    List<Part> findAllByBrandAndModel(String brandName, String modelName);

}
