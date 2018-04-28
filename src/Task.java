public class Task {
    private int taskId;
    private String desc;
    private String assigneeId;
    private boolean completed = false;

    public Task(int taskId, String desc) {
        this.taskId = taskId;
        this.desc = desc;
    }

    public void complete() {
        this.completed = true;
    }
    public int getTaskId() { return this.taskId; }
    public String getDesc() { return this.desc; }
}
