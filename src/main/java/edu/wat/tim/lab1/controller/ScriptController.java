package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService){
        this.scriptService = scriptService;
    }
    @PutMapping()
    public ResponseEntity<String> execScript(@RequestBody String script){
        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK);
    }
}
