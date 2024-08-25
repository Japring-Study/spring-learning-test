package cholog;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
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
			.body(Todo.class);
		// TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐

	}
}
