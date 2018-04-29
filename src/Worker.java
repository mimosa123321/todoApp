public class Worker extends Staff {
    public Worker(String staffId, String name, String role, String password){
        super(staffId, name, role, password);
    }
    public void completeTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        task.isCompleted(true);
    }
    public void undoTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        task.isCompleted(false);
    }
}
