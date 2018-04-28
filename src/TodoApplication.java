import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApplication {
    public static void main(String[] args) {
        new MyFrame("My Frame");
    }
}
class MyFrame extends Frame{
    public String role;
    public ArrayList<Task> tasks = new ArrayList<Task>();
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
        this.tasks.add(new Task(1, "please blablabla"));
        this.tasks.add(new Task(2, "please hahahaha"));
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
                if(tf1.getText().equals("manager") && tf2.getText().equals("manager")) role = "manager";
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
        if(role == "manager") { menuP.add(createTaskBtn); }
        menuP.add(checkTaskBtn);

        createTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreateP.removeAll();
                TextField task_tf1;
                task_tf1 = new TextField("Type Your Task Description here",30);
                Choice choice = new Choice() ;
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
                pack();
                validate();
                confirmBtn.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        taskCreateP.removeAll();
                        Label updateLabel = new Label("  Task table is updated.");
                        taskCreateP.add(updateLabel, BorderLayout.WEST);
                        validate();
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
        for(int i=0; i<tasks.size(); i++) {
            Panel row = new Panel();
//            if() {
//
//            }

            row.setLayout(new GridLayout(1,2));
            row.setBackground(new Color(255,255,255));
            Label id = new Label(Integer.toString(tasks.get(i).getTaskId()));
            Label desc = new Label(tasks.get(i).getDesc());

            row.add(id);
            row.add(desc);
            taskListP.add(row);
        }
    }

    private void checkRole(String role) {
        if(role == "manager") {

        }
    }
}

