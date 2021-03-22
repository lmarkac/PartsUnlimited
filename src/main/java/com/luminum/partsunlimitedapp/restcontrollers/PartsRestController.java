package com.luminum.partsunlimitedapp.restcontrollers;

import com.luminum.partsunlimitedapp.dto.CountResponseDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.service.ArticlesService;
import com.luminum.partsunlimitedapp.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/parts")
public class PartsRestController {

    final PartsService partsService;
    final ArticlesService articlesService;

    @Autowired
    public PartsRestController(PartsService partsService, ArticlesService articlesService) {
        this.partsService = partsService;
        this.articlesService = articlesService;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PartDto> addPart(@RequestBody PartDto part) {
        return new ResponseEntity<>(partsService.addPart(part), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PartDto>> searchParts(
            @RequestParam Optional<Integer> idCar,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dateManufactured,
            @RequestParam Optional<String> brandAndModel) {
        return new ResponseEntity<>(partsService.search(idCar, dateManufactured, brandAndModel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            partsService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CountResponseDto>> count(@RequestParam Optional<String> brandAndModel) {
        return new ResponseEntity<>(brandAndModel
                .map(partsService::listAndCountPartsByCertainBrandAndModel)
                .orElse(partsService.listAndCountPartsByBrandAndModel()), HttpStatus.OK);
    }
}