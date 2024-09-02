package cholog;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TodoClientWithRestClient {
    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        Todo[] result = restClient.get()
                .uri("http://jsonplaceholder.typicode.com/todos")
                .retrieve()
                .body(Todo[].class);

        return Arrays.asList(result);
    }

    public Todo getTodoById(Long id) {

        Todo result = restClient.get()
                .uri("http://jsonplaceholder.typicode.com/todos/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                    throw new TodoException.NotFound(id);
                })
                .body(Todo.class);

        return result;
    }
}
