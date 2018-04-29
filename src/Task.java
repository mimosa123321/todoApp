public class Task {
    private int taskId;
    private String desc;
    private String assignee;
    private boolean completed = false;

    public Task(int taskId, String desc, String assignee) {
        this.taskId = taskId;
        this.desc = desc;
        this.assignee = assignee;
    }

    public int getTaskId() { return this.taskId; }
    public String getDesc() { return this.desc; }
    public String getAssignee() { return this.assignee; }
    public Boolean getCompleted() { return this.completed; }
    public void complete() {
        this.completed = true;
    }

}
