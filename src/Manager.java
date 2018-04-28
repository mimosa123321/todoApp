public class Manager extends Staff {
    public void createTask() {
        int taskId = TaskList.getTasks().size() + 1;
        Task task = new Task( taskId, "" );
        TaskList.add(task);
    }

    public void deleteTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        TaskList.remove(task);
    }
}
