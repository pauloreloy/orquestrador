package br.dev.pre.orquestrador.adapter.out.controlador;

import br.dev.pre.orquestrador.adapter.out.controlador.interfaces.ControladorClient;
import br.dev.pre.orquestrador.domain.entities.PortabilidadeEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;


@Service
public class ControladorClientImpl implements ControladorClient {

    private final RestClient restClient;

    @Value("${controlador.url}")
    private String controlador_url;

    public ControladorClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void salvarPortabilidade(PortabilidadeEntity portabilidade) {
        ResponseEntity<Void> resposta = restClient
                .post()
                .uri(controlador_url)
                .body(Mono.just(portabilidade)) // Wrap with Mono and provide class
                .retrieve()
                .toBodilessEntity();

        System.out.println(resposta);
    }
}