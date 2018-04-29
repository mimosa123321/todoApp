public class Manager extends Staff {
    public Manager(String staffId, String name, String role){
        super(staffId, name, role);
    }
    public void createTask(String desc, String assignee) {
        int taskId = TaskList.getTasks().size() + 1;
        Task task = new Task( taskId, desc, assignee);
        TaskList.add(task);
    }

    public void deleteTask(int taskId) {
        Task task = TaskList.getTask(taskId);
        TaskList.remove(task);
    }
}
