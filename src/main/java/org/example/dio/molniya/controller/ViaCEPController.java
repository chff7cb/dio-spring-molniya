package org.example.dio.molniya.controller;

import com.google.gson.Gson;
import org.example.dio.molniya.domain.ConsultaCEP;
import org.example.dio.molniya.services.ViaCEPService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cep")
public class ViaCEPController {
    private final ViaCEPService viaCEPService;

    public ViaCEPController(ViaCEPService viaCEPService) {
        this.viaCEPService = viaCEPService;
    }

    @GetMapping("/{cep}")
    public ConsultaCEP getCEP(@PathVariable(name = "cep") String CEP) {
        return viaCEPService.getCEP(CEP);
    }
}
