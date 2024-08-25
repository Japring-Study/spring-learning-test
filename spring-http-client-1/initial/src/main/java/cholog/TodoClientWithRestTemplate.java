package cholog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
	private static final String TODO_URL = "http://jsonplaceholder.typicode.com/todos";

	private final RestTemplate restTemplate;

	public TodoClientWithRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Todo getTodoById(Long id) {
		ResponseEntity<Todo> todoResponseEntity = restTemplate.getForEntity(TODO_URL + "/" + id, Todo.class);

		return todoResponseEntity.getBody();
		// TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
	}
}
