package com.sa.repositoryfinder.github.service;

import com.sa.repositoryfinder.common.ProgrammingLanguageEnum;
import com.sa.repositoryfinder.github.repository.GithubRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GithubRepositoryServiceTest {
    private static final String FROM_DATE = "2021-09-01";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    @Mock
    private GithubRepository githubRepository;

    @InjectMocks
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getHighestRatedRepositoriesFromDateShouldCallTheGithubRepositoryToGetTheData() throws ParseException {
        Date fromDate = new SimpleDateFormat(DATE_FORMAT).parse(FROM_DATE);
        ProgrammingLanguageEnum programmingLanguage = ProgrammingLanguageEnum.JAVA;
        Integer limit = 10;

        githubRepositoryService.getHighestRatedRepositoriesFromDate(fromDate, limit, programmingLanguage);

        verify(githubRepository).getHighestRatedRepositoriesFromDate(FROM_DATE, limit, programmingLanguage.name());
    }
}
