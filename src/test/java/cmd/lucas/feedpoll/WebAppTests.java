package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.util.Responses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebAppTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void webAppShouldRespondToWebRequests() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains(Responses.LANDING_PAGE);
	}
}