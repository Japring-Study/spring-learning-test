package cholog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
	private static final String TODO_URL = "http://jsonplaceholder.typicode.com/todos";

	private final RestTemplate restTemplate;

	public TodoClientWithRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Todo getTodoById(Long id) {
		try {
			ResponseEntity<Todo> todoResponseEntity = restTemplate.getForEntity(TODO_URL + "/" + id, Todo.class);

			return todoResponseEntity.getBody();
		} catch (RestClientException e) {
			throw new TodoException.NotFound(id);
		}
	}
}
