import java.util.ArrayList;

public final class TaskList {
    
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    
    public static void add(Task task) {
        tasks.add(task);
    }
    public static void remove(Task task) {
        tasks.remove(task);
    }
    public static Task getTask(int taskId) {
        int len = tasks.size();
        for(int i = 0; i < len; i++) {
            Task task = tasks.get( i );
            if(task.getTaskId() == taskId) {
                return tasks.get( i );
            }
        }
        return null;
    }
    public static ArrayList getTasks() {
        return tasks;
    }
}
