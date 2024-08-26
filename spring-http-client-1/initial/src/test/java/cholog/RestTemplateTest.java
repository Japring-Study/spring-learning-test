package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private TodoClientWithRestTemplate todoClient;

    @Test
    public void testGetTodoWithId() {
        Todo todo = todoClient.getTodoById(1L);
        assertThat(todo.getTitle()).isNotEmpty();
    }

    @Test
    public void testGetTodoWithNonExistentId() {
        Long nonExistentId = 9999L;

        assertThatThrownBy(() -> todoClient.getTodoById(nonExistentId))
                .isInstanceOf(TodoException.NotFound.class);
    }

    @Test
    public void testCreateTodo() {
        Todo newTodo = new Todo(2L, 2L, "New Todo", false);
        Todo createdTodo = todoClient.createTodo(newTodo);

        assertThat(createdTodo).isNotNull();
        assertThat(createdTodo.getTitle()).isEqualTo(newTodo.getTitle());
    }

    @Test
    public void testUpdateTodo() {
        testCreateTodo();

        Todo updatedTodo = new Todo(2L, 2L, "Updated Todo", true);
        Todo resultTodo = todoClient.updateTodo(2L, updatedTodo);

        assertThat(resultTodo).isNotNull();
        assertThat(resultTodo.getTitle()).isEqualTo("Updated Todo");
    }

    @Test
    public void testDeleteTodo() {
        // DELETE 요청을 보내고 상태 코드 확인
        HttpStatusCode deleteStatus = todoClient.deleteTodo(3L);
        assertThat(deleteStatus).isEqualTo(HttpStatus.OK);  // 204 No Content 확인
    }
}
