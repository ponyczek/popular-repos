package com.sa.repositoryfinder.github.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GithubResponse {
    @JsonAlias(value = "items")
    private List<GithubResponseRepositoryItem> repositories;
}
