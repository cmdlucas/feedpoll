package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.controller.RestApiController;
import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import cmd.lucas.feedpoll.domain.model.NewsArticle;
import cmd.lucas.feedpoll.service.FeedService;
import cmd.lucas.feedpoll.controller.Mappings;
import cmd.lucas.feedpoll.utils.TestResponses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(RestApiController.class)
public class RestApiControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedService<NewsArticleDto> newsArticleFeedService;

    @Test
    public void newsEndpointShouldReturnEmptyJsonArrayWhenNoDataSet() throws Exception {
        when(newsArticleFeedService.fetchLatestTen()).thenReturn(TestResponses.noNewsArticleObject());
        this.mockMvc.perform(get("/" + Mappings.NEWS)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(TestResponses.NO_NEWS_ARTICLE_STRING));
    }

    @Test
    public void newsEndpointShouldReturnJsonArrayWithContentWhenDataSet() throws Exception {
        when(newsArticleFeedService.fetchLatestTen()).thenReturn(TestResponses.oneNewsArticleList());
        this.mockMvc.perform(get("/" + Mappings.NEWS)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(TestResponses.ONE_NEWS_ARTICLE_STRING));
    }

}
