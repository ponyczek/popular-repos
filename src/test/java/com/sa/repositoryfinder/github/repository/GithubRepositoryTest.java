package com.sa.repositoryfinder.github.repository;

import com.sa.repositoryfinder.github.client.GithubClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GithubRepositoryTest {
    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubRepository githubRepository;

    @Test
    public void getHighestRatedRepositoriesFromDateShouldCallTheGithubApiClientToGetTheData() {
        String date = "2021-09-01";
        Integer limit = 10;
        String language = "JAVA";

        githubRepository.getHighestRatedRepositoriesFromDate(date, limit, language);

        verify(githubClient).getHighestRatedRepositoriesFromDate(date, limit, language);
    }
}