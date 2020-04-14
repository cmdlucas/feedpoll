package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.domain.job.FeedPoller;
import cmd.lucas.feedpoll.domain.service.NewsApi;
import cmd.lucas.feedpoll.util.ApiKeys;
import cmd.lucas.feedpoll.util.apirequest.newsapi.EverythingQuery;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;
import cmd.lucas.feedpoll.util.opsresponse.ResponseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEvent;

import javax.validation.UnexpectedTypeException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PollerAppTests {

    @Autowired
    private PollerApp pollerApp;

    @MockBean
    private HttpRestRequestObject httpRestRequestObject;

    @MockBean
    private NewsApi newsApi;

    @Test
    void pollerAppShouldRunGivenNumberOfTasksOnAppStart() {
        pollerApp.start();
        assertThat(pollerApp.getExecutorService().shutdownNow().size())
                .isEqualTo(pollerApp.getKeywords().length);
    }

    @Test
    void feedPollerShouldMakeHttpRequestOnlyOnceIfSuccess(){
        GeneralResponse successfulResponse = new GeneralResponse(ResponseType.SUCCESS, "Done!");
        when(httpRestRequestObject.get()).thenReturn(Optional.of(successfulResponse));
        new FeedPoller(httpRestRequestObject).run();
        verify(httpRestRequestObject, atMostOnce()).get();
    }

    @Test
    void feedPollerShouldMakeHttpRequestThriceFromFirstFailure(){
        GeneralResponse failureResponse = new GeneralResponse(ResponseType.FAILED, "Failed!");
        when(httpRestRequestObject.get()).thenReturn(Optional.of(failureResponse));
        new FeedPoller(httpRestRequestObject).run();
        verify(httpRestRequestObject, times(3)).get();
    }

    @Test
    void newsApiRequestShouldFailWhenQueryIsNotDefined() {
        Optional<GeneralResponse> response = httpRestRequestObject.get();
        response.ifPresent(generalResponse ->
                assertThat(generalResponse.getType()).isEqualTo(ResponseType.FAILED));
    }

    @Test
    void newsApiRequestShouldSucceedWhenQueryIsDefined() {
        httpRestRequestObject.setQuery(
                new EverythingQuery(ApiKeys.NEWS_API_DOT_ORG, "covid", 2, 1));

        Optional<GeneralResponse> response = httpRestRequestObject.get();

        response.ifPresent(generalResponse ->
                assertThat(generalResponse.getType()).isEqualTo(ResponseType.SUCCESS));
    }
}
