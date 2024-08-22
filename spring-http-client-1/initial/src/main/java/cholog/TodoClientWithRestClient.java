package cholog;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

public class TodoClientWithRestClient {
    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        return restClient.get()
                .uri("/todos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Todo>>(){});
    }

    public Todo getTodoById(Long id) {
        return restClient.get()
                .uri("/todos/{todoId}", id)
                .retrieve()
                .body(Todo.class);
    }
}
