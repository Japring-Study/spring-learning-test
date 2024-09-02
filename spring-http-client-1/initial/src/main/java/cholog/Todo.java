package cholog;

public class Todo {

    // TODO: Todo 객체가 가지는 필드들을 정의
    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;

    public Long getUserId() {
        return userId;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Boolean getCompleted() {
        return completed;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
