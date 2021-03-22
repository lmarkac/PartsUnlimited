package com.luminum.partsunlimitedapp.restcontrollers;

import com.luminum.partsunlimitedapp.dto.ActionDto;
import com.luminum.partsunlimitedapp.service.ActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actions")
public class ActionsRestController {

    final ActionsService actionsService;

    @Autowired
    public ActionsRestController(ActionsService actionsService) {
        this.actionsService = actionsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAction(@RequestBody ActionDto actionDto){
        return new ResponseEntity<>(actionsService.addAction(actionDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteAction(@RequestParam Integer id){
        actionsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getActions(){
        return new ResponseEntity<>(actionsService.findAll(), HttpStatus.OK);
    }
}
