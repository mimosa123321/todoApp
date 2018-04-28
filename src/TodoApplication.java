import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApplication {
    public static void main(String[] args) {
        new MyFrame("My Frame");
    }
}
class MyFrame{
    public ArrayList<Task> tasks = new ArrayList<Task>();
    public MyFrame(String title){
        final Frame f = new Frame();
        final Panel topP = new Panel();
        final Panel loginP = new Panel();
        final Panel commandP = new Panel();
        final Panel table = new Panel();
        final Panel taskCreateP = new Panel();
        final Panel taskListP = new Panel();
        initTopP(f, topP, loginP, commandP, table);
        initCommandP(f, commandP, table, taskCreateP, taskListP);
        initTableP(f, commandP, table, taskCreateP, taskListP);
        f.setLocation(200,200);
        f.pack();
        f.setVisible(true);

        this.tasks.add(new Task(1, "please blablabla"));
        this.tasks.add(new Task(2, "please hahahaha"));
    }

    private void initTopP(Frame f, Panel topP, Panel loginP, Panel commandP, Panel table) {
        //login btn
        TextField tf1, tf2;
        tf1 = new TextField("Staff id",20);
        tf2 = new TextField("Password", 20);
        Button loginBtn = new Button("Login");
        loginP.add(tf1);
        loginP.add(tf2);
        loginP.add(loginBtn);

        //logout btn
        Button logOutBtn = new Button("Logout");

        loginBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                topP.remove(loginP);
                topP.add(commandP);
                topP.add(logOutBtn,BorderLayout.WEST);
                f.validate();
            }
        });
        logOutBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                topP.remove(logOutBtn);
                topP.remove(commandP);
                topP.add(loginP);
                f.pack();
                f.validate();
            }
        });
        topP.add(loginP, BorderLayout.CENTER);

        f.setBackground(new Color(255,255,255));
        f.add(topP, BorderLayout.NORTH);
    }
    private void initCommandP(Frame f, Panel commandP, Panel table, Panel taskCreateP, Panel taskListP) {
        GridLayout l = new GridLayout(1, 2);
        Button createTaskBtn = new Button("Create Task");
        Button checkTaskBtn = new Button("Check Task");
        commandP.setLayout(l);
        commandP.add(createTaskBtn);
        commandP.add(checkTaskBtn);

        createTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreateP.removeAll();
                TextField task_tf1;
                task_tf1 = new TextField("Type Your Task Description here",30);
                Choice      choice      = new Choice() ;
                // fill the Choice
                choice.addItem( "Clinton" ) ;
                choice.addItem( "Dole" ) ;
                choice.addItem( "Perot" ) ;
                choice.addItem( "Browne" ) ;
                choice.addItem( "Nader" ) ;
                Button confirmBtn = new Button("Confirm");
                taskCreateP.add(task_tf1);
                taskCreateP.add(choice) ;
                taskCreateP.add(confirmBtn);
                taskCreateP.setVisible(true);
                taskListP.setVisible(false);
                f.pack();
                f.validate();
                confirmBtn.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        taskCreateP.removeAll();
                        Label updateLabel = new Label("  Task table is updated.");
                        taskCreateP.add(updateLabel, BorderLayout.WEST);
                        f.validate();
                    }
                });
            }
        });
        checkTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreateP.setVisible(false);
                taskListP.setVisible(true);
                loadTaskList(taskListP);
                f.validate();
            }
        });
    }
    private void initTableP(Frame f, Panel commandP, Panel table, Panel taskCreateP, Panel taskListP) {
        GridLayout l = new GridLayout(20, 1);
        taskListP.setLayout(l);
//        table.setVisible(false);
        table.add(taskCreateP);
        table.add(taskListP, BorderLayout.CENTER);

//        f.add(table, BorderLayout.CENTER);
    }

    private void loadTaskList(Panel taskListP) {
        taskListP.removeAll();
        for(int i=0; i<tasks.size(); i++) {
            Panel row = new Panel();
            GridLayout experimentLayout = new GridLayout(1,2);

            row.setLayout(experimentLayout);
            row.setBackground(new Color(255,255,255));
            experimentLayout.setHgap(40);
            experimentLayout.setVgap(100);
            Label id = new Label(Integer.toString(tasks.get(i).getTaskId()));
            Label desc = new Label(tasks.get(i).getDesc());

            row.add(id);
            row.add(desc);
            taskListP.add(row);
        }
    }
}

