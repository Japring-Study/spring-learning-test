package cholog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class TodoClientWithRestTemplate {
    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(Long id) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://jsonplaceholder.typicode.com")
                .path("/todos/{todoId}")
                .build(id);

        try {
            return restTemplate.getForEntity(uri, Todo.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new TodoException.NotFound(id);
        }
    }
}
