package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {
    @GetMapping("/teste-api")
    public String teste () { return "Sucesso"; }

    @GetMapping("/teste-api-bem-vindo")
    public String testeBemVindo(@RequestParam(name = "name") String name) {
        return "Olá " + name + ", Seja muito bem vindo! ";
    }
}
