package cholog;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(Long id) {
        String resourceUrl = "http://jsonplaceholder.typicode.com/todos/{id}";
//        String resourceUrl = "http://jsonplaceholder.typicode.com/todos/" + id;
        try {
            ResponseEntity<Todo> response = restTemplate.getForEntity(resourceUrl, Todo.class, id);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException.NotFound(id);
            } else {
                throw new TodoException("다른 에러 : " + e.getMessage());
            }
        }
    }

    public Todo createTodo(Todo newTodo) {
        String resourceUrl = "http://jsonplaceholder.typicode.com/todos";
        try {
            ResponseEntity<Todo> response = restTemplate.postForEntity(resourceUrl, newTodo, Todo.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new TodoException("Todo 생성 중 에러 발생: " + e.getMessage());
        }
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        String resourceUrl = "http://jsonplaceholder.typicode.com/todos/{id}";
        try {
            HttpEntity<Todo> requestUpdate = new HttpEntity<>(updatedTodo);
            ResponseEntity<Todo> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Todo.class, id);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException.NotFound(id);
            } else {
                throw new TodoException("Todo 업데이트 중 에러 발생: " + e.getMessage());
            }
        }
    }

    public HttpStatusCode deleteTodo(Long id) {
        try {
            String resourceUrl = "http://jsonplaceholder.typicode.com/todos/{id}";
            restTemplate.delete(resourceUrl, id);
//            ResponseEntity<Void> response = restTemplate.exchange(resourceUrl, HttpMethod.DELETE, null, Void.class, id);
//            return response.getStatusCode();
            return HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException.NotFound(id);
            } else {
                throw new TodoException("Todo 삭제 중 에러 발생: " + e.getMessage());
            }
        }
    }
}
