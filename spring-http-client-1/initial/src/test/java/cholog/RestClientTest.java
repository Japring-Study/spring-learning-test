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
public class RestClientTest {

    @Autowired
    private TodoClientWithRestClient todoClient;

    @Test
    public void testGetTodos() {
        List<Todo> todos = todoClient.getTodos();
        assertThat(todos).isNotEmpty();
    }

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
        todoClient.deleteTodo(2L);

        //삭제된 후, 해당 id로 getTodoById를 호출하면 TodoException.NotFound 예외가 발생하는지를 검증
        // 예외를 발생하는지 -> NotFound class 인지
//        assertThatThrownBy(() -> todoClient.getTodoById(2L)).isInstanceOf(TodoException.NotFound.class);
    }

}
