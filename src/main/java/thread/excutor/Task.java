package thread.excutor;

/**
 * @author tangxiaoshuang
 * @date 2018/5/28 15:42
 */
public class Task {

    private Integer id;
    private String context;

    public Task(Integer id, String context) {
        this.id = id;
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", context='" + context + '\'' +
                '}';
    }
}
