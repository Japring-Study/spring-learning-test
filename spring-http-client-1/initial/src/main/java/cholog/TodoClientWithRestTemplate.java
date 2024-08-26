package cholog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
