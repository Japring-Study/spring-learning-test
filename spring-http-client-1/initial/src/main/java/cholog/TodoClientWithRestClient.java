package cholog;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class TodoClientWithRestClient {
	private final RestClient restClient;

	public TodoClientWithRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public List<Todo> getTodos() {
		return restClient.get()
			.uri("/todos")
			.retrieve()
			.body(new ParameterizedTypeReference<>() {
			});
	}

	public Todo getTodoById(Long id) {
		return restClient.get()
			.uri("/todos/{id}", id)
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
				throw new TodoException.NotFound(id);
			})
			.body(Todo.class);
	}
}
