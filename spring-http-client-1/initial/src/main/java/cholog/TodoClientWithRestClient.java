package cholog;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
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
			.onStatus(HttpStatus.NOT_FOUND::equals, (request, response) -> {
				throw new TodoException.NotFound(id);
			})
			.body(Todo.class);
	}
}
