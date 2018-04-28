public class Worker extends Staff {
    public void completeTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        task.complete();
    }
}
