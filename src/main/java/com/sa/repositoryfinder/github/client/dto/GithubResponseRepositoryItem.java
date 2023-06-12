package com.sa.repositoryfinder.github.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class GithubResponseRepositoryItem {
    @JsonProperty("created_at")
    Instant createdAt;
    String description;
    @JsonProperty("full_name")
    String fullName;
    Long id;
    String language;
    String name;
    @JsonProperty("stargazers_count")
    Integer stars;
    String url;
}
