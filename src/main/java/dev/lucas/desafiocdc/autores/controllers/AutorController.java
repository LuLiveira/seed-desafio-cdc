package dev.lucas.desafiocdc.autores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lucas.desafiocdc.autores.controllers.request.AutorRequest;
import dev.lucas.desafiocdc.autores.controllers.response.AutorResponse;
import dev.lucas.desafiocdc.autores.services.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping("/novo")
    public ResponseEntity<AutorResponse> novo(@Valid @RequestBody AutorRequest request){
        var autor = request.toAutor();
        var novoAutor = service.novoAutor(autor);
        var response = AutorResponse.fromAutor(novoAutor);

        return ResponseEntity.ok(response);
    }

}
