package com.sa.repositoryfinder.github.service;

import com.sa.repositoryfinder.common.ProgrammingLanguageEnum;
import com.sa.repositoryfinder.github.client.dto.GithubResponse;
import com.sa.repositoryfinder.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GithubRepositoryService {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private final GithubRepository githubRepository;

    public Mono<GithubResponse> getHighestRatedRepositoriesFromDate(Date fromDate, Integer limit, ProgrammingLanguageEnum language) {
        return githubRepository.getHighestRatedRepositoriesFromDate(getDateInValidFormat(fromDate), limit, getProgrammingLanguage(language));
    }

    private String getProgrammingLanguage(ProgrammingLanguageEnum languageEnum) {
        return languageEnum != null ? languageEnum.name() : null;
    }

    private String getDateInValidFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        return formatter.format(date);
    }
}
