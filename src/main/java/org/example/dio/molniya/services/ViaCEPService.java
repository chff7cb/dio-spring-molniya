package org.example.dio.molniya.services;

import org.example.dio.molniya.domain.ConsultaCEP;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@EnableFeignClients
public class ViaCEPService {
    private ViaCEPClient client;

    private final ValueOperations<String, ConsultaCEP> cacheValues;

    public ViaCEPService(ViaCEPClient client, ValueOperations<String, ConsultaCEP> cacheValues) {
        this.client = client;
        this.cacheValues = cacheValues;
    }

    public ConsultaCEP getCEP(String CEP) {
        return Optional.ofNullable(cacheValues.get(CEP))
                .orElseGet(() -> fetchAndPersistCEP(CEP));
    }

    protected ConsultaCEP fetchAndPersistCEP(String CEP) {
        ConsultaCEP result = client.getCEPJson(CEP);
        cacheValues.set(CEP, result, 1L, TimeUnit.MINUTES);
        return result;
    }

    @FeignClient(name = "ViaCEPService", url = "https://viacep.com.br")
    interface ViaCEPClient {
        @GetMapping("/ws/{cep}/json")
        ConsultaCEP getCEPJson(@PathVariable("cep") String CEP);
    }
}
