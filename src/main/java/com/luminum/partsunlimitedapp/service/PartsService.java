package com.luminum.partsunlimitedapp.service;

import com.luminum.partsunlimitedapp.dto.CountResponseDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.model.Part;
import com.luminum.partsunlimitedapp.service.dao.PartsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("partsService")
public class PartsService {

    private final ArticlesService articlesService;
    private final PartsDaoService partsDaoService;

    @Autowired
    public PartsService(ArticlesService articlesService, PartsDaoService partsDaoService) {
        this.articlesService = articlesService;
        this.partsDaoService = partsDaoService;
    }

    public PartDto addPart(PartDto part) {
        return toDto(partsDaoService.addPart(part));
    }

    public Optional<PartDto> findById(Integer id) {
        return partsDaoService.findById(id)
                .map(this::toDto);
    }

    public void deletePartById(int id) {
        articlesService.deleteAllByPartId(id);
        partsDaoService.deletePart(id);
    }

    private List<PartDto> searchPartsByCarId(int id) {
        return partsDaoService.searchPartsByCarId(id).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private List<PartDto> searchPartsByDate(LocalDate date) {
        return partsDaoService.searchPartsByDate(date).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PartDto toDto(Part part) {
        PartDto partDto = new PartDto();

        partDto.setId(part.getId());
        partDto.setPartName(part.getPartName());
        partDto.setDateManufactured(part.getDateManufactured());
        partDto.setIdCar(part.getCar().getId());

        return partDto;
    }

    public List<CountResponseDto> listAndCountPartsByBrandAndModel() {
        return partsDaoService.listAndCountPartsByBrandAndModel();
    }

    public List<CountResponseDto> listAndCountPartsByCertainBrandAndModel(String brandAndModel) {
        String[] splitString = brandAndModel.split(" ");

        String brandName = splitString[0];
        String modelName = splitString[1];

        return partsDaoService.listAndCountPartsByCertainBrandAndModel(brandName, modelName);
    }

    public List<PartDto> findAll() {
        return partsDaoService.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PartDto> search(Optional<Integer> idCar, Optional<LocalDate> dateManufactured, Optional<String> brandAndModel) {

        if (idCar.isPresent()) {
            return searchPartsByCarId(idCar.get());
        }

        if (dateManufactured.isPresent()) {
            return searchPartsByDate(dateManufactured.get());
        }

        if (brandAndModel.isPresent()) {
            String[] splitString = brandAndModel.get().split(" ");

            String brandName = splitString[0];
            String modelName = splitString[1];

            return partsDaoService.findAllByBrandAndModel(brandName, modelName).stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        return findAll();
    }

    public void delete(Integer id) {
        deletePartById(id);
    }
}