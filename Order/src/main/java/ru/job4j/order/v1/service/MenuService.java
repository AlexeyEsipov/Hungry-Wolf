package ru.job4j.order.v1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.order.v1.model.dto.DishDto;
import ru.job4j.order.v1.model.status.MenuStatus;

import java.util.Collections;
import java.util.List;

@Service
public class MenuService {
    @Value("${dish-api-url}")
    private String url;
    private final RestTemplate client;

    public MenuService(RestTemplate client) {
        this.client = client;
    }

    public boolean isAvailableByName(String name) {
        return Boolean.TRUE.equals(client.getForObject("%s/exist/%s"
                        .formatted(url, name),
                Boolean.class));
    }

    public List<DishDto> findByStatus(MenuStatus status) {
        List<DishDto> body = client.exchange(
                "%s/available".formatted(url),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DishDto>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
