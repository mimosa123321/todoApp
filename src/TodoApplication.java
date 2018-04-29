import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApplication {
    public static void main(String[] args) {
        new MyFrame("My Frame");
    }
}
class MyFrame extends Frame{
    private Staff me = new Staff();

    public MyFrame(String title){
        Panel topP = new Panel();
        Panel loginP = new Panel();
        Panel menuP = new Panel();
        Panel table = new Panel();
        Panel taskCreateP = new Panel();
        Panel taskListP = new Panel();
        initTopP(topP, loginP, menuP, table, taskCreateP, taskListP);
        initTableP(table, taskCreateP, taskListP);
        this.setLocation(200,200);
        this.pack();
        this.setVisible(true);
    }

    private void initTopP(Panel topP, Panel loginP, Panel menuP, Panel table, Panel taskCreateP, Panel taskListP) {
        TextField tf1, tf2;
        tf1 = new TextField("Staff id",20);
        tf2 = new TextField("Password", 20);
        Button loginBtn = new Button("Login");
        loginP.add(tf1);
        loginP.add(tf2);
        loginP.add(loginBtn);
        Button logOutBtn = new Button("Logout");
        loginBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(tf1.getText().equals("000000") && tf2.getText().equals("000000")) {
                    me = new Manager("000000", "manager","manager");
                } else {
                    me = new Worker("999999", "worker","worker");
                }
                remove(loginP);
                initMenuP(menuP, table, taskCreateP, taskListP);
                topP.add(menuP);
                topP.add(logOutBtn,BorderLayout.WEST);
                add(table, BorderLayout.CENTER);
                validate();
            }
        });
        logOutBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                topP.removeAll();
                menuP.removeAll();
                taskCreateP.removeAll();
                taskListP.removeAll();
                remove(table);
                add(loginP);
                pack();
                validate();
            }
        });
        add(loginP, BorderLayout.CENTER);
        add(topP, BorderLayout.NORTH);
    }
    private void initMenuP(Panel menuP, Panel table, Panel taskCreateP, Panel taskListP) {
        GridLayout l = new GridLayout(1, 2);
        Button createTaskBtn = new Button("Create Task");
        Button checkTaskBtn = new Button("Check Task");
        menuP.setLayout(l);
        if(me.role == "manager") menuP.add(createTaskBtn);
        menuP.add(checkTaskBtn);
        createTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreateP.removeAll();
                TextField task_desc;
                task_desc = new TextField("Type Your Task Description here",30);
                Choice choice = new Choice() ;
                choice.addItem( "Jan" ) ;
                choice.addItem( "Gloria" ) ;
                choice.addItem( "Nale" ) ;
                Button confirmBtn = new Button("Confirm");
                taskCreateP.add(task_desc);
                taskCreateP.add(choice) ;
                taskCreateP.add(confirmBtn);
                taskCreateP.setVisible(true);
                taskListP.setVisible(false);
                pack();
                validate();
                confirmBtn.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        taskCreateP.removeAll();
                        Label updateLabel = new Label("  Task table is updated.");
                        taskCreateP.add(updateLabel, BorderLayout.WEST);
                        validate();
                        ((Manager)me).createTask(task_desc.getText(), choice.getSelectedItem());
                    }
                });
            }
        });
        checkTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreateP.setVisible(false);
                taskListP.setVisible(true);
                loadTaskList(taskListP);
                pack();
                validate();
            }
        });
    }
    private void initTableP(Panel table, Panel taskCreateP, Panel taskListP) {
        GridLayout l = new GridLayout(20, 1);
        taskListP.setLayout(l);
        table.add(taskCreateP, BorderLayout.CENTER);
        table.add(taskListP, BorderLayout.CENTER);
    }

    private void loadTaskList(Panel taskListP) {
        taskListP.removeAll();
        ArrayList<Task> tasks = TaskList.getTasks();
        for(int i=0; i<tasks.size(); i++) {
            Panel row = new Panel();
            row.setLayout(new GridLayout(1,4));
            Label id = new Label(Integer.toString(tasks.get(i).getTaskId()));
            Label desc = new Label(tasks.get(i).getDesc());
            Label completed = new Label(tasks.get(i).getCompleted().toString());
            Label assignee = new Label(tasks.get(i).getAssignee());
            Checkbox checkbox = new Checkbox( "" );
            checkbox.addItemListener(new ItemListener() {
                @Override public void itemStateChanged(ItemEvent e) {
                    ((Worker)me).completeTask(Integer.parseInt(id.getText()));
                }
            } );
            completed.setAlignment(Label.RIGHT);
            row.add(id);
            row.add(desc);
            row.add(assignee);
            if(me.role == "worker") {
                row.add(checkbox);
                if(!tasks.get(i).getCompleted()) {
                    checkbox.setState(false);
                }else {
                    checkbox.setState(true);
                }
            }else {
                row.add(completed);
            }
            taskListP.add(row);
        }
    }
}

