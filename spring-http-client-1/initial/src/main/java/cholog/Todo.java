package cholog;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data               // getter, setter
//@NoArgsConstructor  // 기본 생성자 생성
//@AllArgsConstructor   // 모든 필드를 포함하는 생성자 생성
public class Todo {

    private Long userId;
    private Long id;
    private String title;
    private boolean completed;

    public Todo(Long userId, Long id, String title, boolean completed) { // -> AllArgsConstructor (Lombok)
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getUserId() { return userId; }

    public Long getId() { return id; }

    public String getTitle() {
        return title;
    }

    public boolean getCompleted() { return completed; }
}
