package com.sa.repositoryfinder.github.client;

import com.sa.repositoryfinder.common.ProgrammingLanguageEnum;
import com.sa.repositoryfinder.github.client.dto.GithubResponse;
import com.sa.repositoryfinder.github.client.dto.GithubResponseRepositoryItem;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GithubClientIntegrationTest {
    private static final String FROM_DATE = "2023-01-01";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public static MockWebServer mockBackEnd;

    private GithubClient githubClient;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void initialize() {
        githubClient = new GithubClient();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void getHighestRatedRepositoriesFromDateShouldSuccessfullyApplyTheLimitAndReturnGivenNumberOfRepositories() {
        int limit = 10;
        Mono<GithubResponse> result = githubClient.getHighestRatedRepositoriesFromDate(FROM_DATE, limit, null);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getRepositories().size() == limit)
                .verifyComplete();
    }

    @Test
    void getHighestRatedRepositoriesFromDateShouldReturnOnlyRepositoriesFilteredByProgrammingLanguage() {
        ProgrammingLanguageEnum programmingLanguage = ProgrammingLanguageEnum.JAVA;
        Mono<GithubResponse> result =
                githubClient.getHighestRatedRepositoriesFromDate(FROM_DATE, 10, programmingLanguage.name());

        StepVerifier.create(result)
                .expectNextMatches(response -> areAllRepositoriesFilteredByLanguage(response.getRepositories()))
                .verifyComplete();
    }

    @Test
    void getHighestRatedRepositoriesFromDateShouldNotReturnMoreThan100Repositories() {
        int tooHighLimit = 199;
        Mono<GithubResponse> result =
                githubClient.getHighestRatedRepositoriesFromDate(FROM_DATE, tooHighLimit, null);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getRepositories().size() == 100)
                .verifyComplete();
    }

    @Test
    void getHighestRatedRepositoriesFromDateShouldReturnRepositoriesNewerThanGivenDateAndOrderedByStars() {
        Mono<GithubResponse> result =
                githubClient.getHighestRatedRepositoriesFromDate(FROM_DATE, null, null);

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        isOrderedByStars(response.getRepositories()) &&
                        areAllRepositoriesOlderThanGivenDate(response.getRepositories()))
                .verifyComplete();
    }

    private boolean areAllRepositoriesFilteredByLanguage(List<GithubResponseRepositoryItem> repositories) {
        return repositories.stream()
                .allMatch(repository -> ProgrammingLanguageEnum.JAVA.name().equalsIgnoreCase(repository.getLanguage()));
    }

    private boolean areAllRepositoriesOlderThanGivenDate(List<GithubResponseRepositoryItem> repositories) {
        return repositories.stream()
                .allMatch(repository -> {
                    try {
                        return repository.getCreatedAt().isAfter(new SimpleDateFormat(DATE_PATTERN).parse(FROM_DATE).toInstant());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private boolean isOrderedByStars(List<GithubResponseRepositoryItem> repositories) {
        int size = repositories.size();
        if (size <= 1) {
            return true;
        }

        for (int i = 0; i < size - 1; i++) {
            GithubResponseRepositoryItem current = repositories.get(i);
            GithubResponseRepositoryItem next = repositories.get(i + 1);

            if (current.getStars() < next.getStars()) {
                return false;
            }
        }

        return true;
    }
}
