package com.sa.repositoryfinder.github.client;

import com.sa.repositoryfinder.github.client.dto.GithubResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GithubClient {
    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final int RESPONSE_IN_MEMORY_BUFFER_LIMIT = 1024 * 1024;
    private static final String DEFAULT_SORT = "desc";
    private static final String DEFAULT_ORDER_BY = "stars"; //TODO check if stars or stargazers_count
    private final WebClient webClient = WebClient.builder()
            .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(RESPONSE_IN_MEMORY_BUFFER_LIMIT))
            .build();

    public Mono<GithubResponse> getHighestRatedRepositoriesFromDate(String fromDate, Integer limit, String language) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(GITHUB_API_BASE_URL + "/search/repositories?")
                .queryParam("q", getQuery(fromDate, language))
                .queryParam("sort", DEFAULT_SORT)
                .queryParam("order", DEFAULT_ORDER_BY)
                .queryParamIfPresent("language:", Optional.ofNullable(language))
                .queryParamIfPresent("per_page", Optional.ofNullable(limit));

        return webClient.get()
                .uri(uriBuilder.build().toUri())
                .retrieve()
                .bodyToMono(GithubResponse.class)
                .doOnError(WebClientResponseException.class,
                        error -> System.err.println("Error: " + error.getStatusCode() + " - " + error.getStatusText()))
                .doOnSuccess(responseBody -> System.out.println("Response Body: " + responseBody));
    }

    private String getQuery(String fromDate, String language) {
        return language == null ? "created:>" + fromDate : "created:>" + fromDate + " language:" + language;
    }
}
