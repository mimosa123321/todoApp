public class Worker extends Staff {
    public Worker(String staffId, String name, String role){
        super(staffId, name, role);
    }
    public void completeTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        task.complete();
    }
}
