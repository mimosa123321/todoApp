public class Manager extends Staff {
    public Manager(String staffId, String name, String role, String password){
        super(staffId, name, role, password);
    }
    public void createTask(String desc, String assignee) {
        int taskId = TaskList.getTasks().size() + 1;
        Task task = new Task( taskId, desc, assignee);
        TaskList.add(task);
    }
}
