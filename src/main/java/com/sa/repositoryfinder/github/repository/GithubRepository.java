package com.sa.repositoryfinder.github.repository;

import com.sa.repositoryfinder.github.client.GithubClient;
import com.sa.repositoryfinder.github.client.dto.GithubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class GithubRepository {
    private final GithubClient client;

    public Mono<GithubResponse> getHighestRatedRepositoriesFromDate(String date, Integer limit, String language) {
        return client.getHighestRatedRepositoriesFromDate(date, limit, language);
    }
}
