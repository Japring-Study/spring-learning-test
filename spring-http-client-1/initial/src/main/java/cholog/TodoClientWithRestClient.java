package cholog;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

public class TodoClientWithRestClient {
    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        //RestClient.builder().baseUrl("http://jsonplaceholder.typicode.com").build()
//        String uri = "http://jsonplaceholder.typicode.com/todos";
        List<Todo> todos = restClient.get()
                            .uri("/todos")
                            .retrieve()
                            .body(List.class);
        return todos;
    }

    public Todo getTodoById(Long id) {
        Todo todo = restClient.get()
                .uri("/todos/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                    throw new TodoException.NotFound(id);
                })
                .body(Todo.class);
        return todo;
    }

    public Todo createTodo(Todo newTodo) {
        Todo createdTodo = restClient.post()
                .uri("/todos")
                .body(newTodo)
                .retrieve()
                .body(Todo.class);
        return createdTodo;
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo updatedResult = restClient.put()
                .uri("/todos/{id}", id)
                .body(updatedTodo)
                .retrieve()
                .body(Todo.class);
        return updatedResult;
    }

    public void deleteTodo(Long id) {
        restClient.delete()
                .uri("/todos/{id}", id)
                .retrieve()
                .toBodilessEntity(); // DELETE 요청에 대한 응답 본문이 필요 없으므로 toBodilessEntity 사용
    }
}
