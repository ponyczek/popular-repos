package com.sa.repositoryfinder.github.api;

import com.sa.repositoryfinder.common.ProgrammingLanguageEnum;
import com.sa.repositoryfinder.github.client.dto.GithubResponse;
import com.sa.repositoryfinder.github.service.GithubRepositoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/top-repository/github", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class GithubRepositoryApi {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private final GithubRepositoryService githubRepositoryService;

    @GetMapping
    public Mono<GithubResponse> getHighestRatedRepositoriesFromDate(
            @RequestParam(value = "fromDate") @DateTimeFormat(pattern = DATE_FORMAT) Date fromDate,
            @RequestParam(value = "limit", required = false) @Valid @Min(1) @Max(100) Integer limit,
            @RequestParam(value = "language", required = false) ProgrammingLanguageEnum language) {
        return githubRepositoryService.getHighestRatedRepositoriesFromDate(fromDate, limit, language);
    }
}
