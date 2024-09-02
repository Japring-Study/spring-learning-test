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
        try{
            String Url = "http://jsonplaceholder.typicode.com/todos/1";
            ResponseEntity<Todo> result
                    = restTemplate.getForEntity(Url+"/{id}", Todo.class);
            return result.getBody();
        } catch (HttpClientErrorException e){
            if (e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new TodoException.NotFound(id);
            }
            throw new TodoException("Todo with id: " + id + "not found");

        }

    }
}
