import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApplication {
    public static void main(String[] args) {
        new UIFrame();
        StaffList.getStaffs().add(new Staff("000000","Chi Shin Wong","manager","000000"));
        StaffList.getStaffs().add(new Staff("999999","Jan","worker","999999"));
        StaffList.getStaffs().add(new Staff("888888","Gloria","worker","888888"));
    }
}
class UIFrame extends Frame{
    private Staff me = new Staff();
    public UIFrame(){
        Panel login = new Panel();
        Panel header = new Panel();
        Panel menu = new Panel();
        Panel body = new Panel();
        Panel taskCreate = new Panel();
        Panel taskList = new Panel();
        initHeader(header, login, menu, body, taskCreate, taskList);
        initBody(body, taskCreate, taskList);
        this.setLocation(200,200);
        this.pack();
        this.setVisible(true);
    }
    private void initHeader(Panel header, Panel login, Panel menu, Panel body, Panel taskCreate, Panel taskList) {
        TextField tf_id, tf_pw;
        tf_id = new TextField("Staff id",20);
        tf_pw = new TextField("Password", 20);
        Button loginBtn = new Button("Login");
        login.add(tf_id);
        login.add(tf_pw);
        login.add(loginBtn);
        Button logOutBtn = new Button("Logout");
        loginBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (!validateLogin(tf_id.getText(), tf_pw.getText())) {
                    System.out.println("Wrong username or password");
                    return;
                }
                Staff staff = StaffList.getStaff(tf_id.getText());
                if(staff.getRole().equals("manager")) {
                    me = new Manager(staff.getStaffId(), staff.getName(),staff.getRole(), staff.getPassword());
                } else {
                    me = new Worker(staff.getStaffId(), staff.getName(),staff.getRole(), staff.getPassword());
                }
                remove(login);
                initMenu(menu, taskCreate, taskList);
                header.add(menu);
                header.add(logOutBtn,BorderLayout.WEST);
                add(body, BorderLayout.CENTER);
                validate();
            }
        });
        logOutBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                header.removeAll();
                menu.removeAll();
                taskCreate.removeAll();
                taskList.removeAll();
                remove(body);
                add(login);
                pack();
                validate();
            }
        });
        add(header, BorderLayout.NORTH);
        add(login, BorderLayout.CENTER);

    }
    private Boolean validateLogin(String id, String pw) {
        if(id == null || pw == null) return false;
        if(StaffList.getStaff(id) != null) {
            if(StaffList.getStaff(id).getPassword().equals(pw)) {return true;}
        }
        return false;
    }
    private void initMenu(Panel menu, Panel taskCreate, Panel taskList) {
        Button createTaskBtn = new Button("Create Task");
        Button checkTaskBtn = new Button("Check Task");
        if(me.role.equals("manager")) menu.add(createTaskBtn);
        menu.add(checkTaskBtn);
        createTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreate.removeAll();
                TextField task_desc;
                task_desc = new TextField("Type Your Task Description here",30);
                Choice choice = new Choice() ;
                choice.addItem( "Jan" ) ;
                choice.addItem( "Gloria" ) ;
                Button confirmBtn = new Button("Confirm");
                taskCreate.add(task_desc);
                taskCreate.add(choice) ;
                taskCreate.add(confirmBtn);
                taskCreate.setVisible(true);
                taskList.setVisible(false);
                pack();
                validate();
                confirmBtn.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        taskCreate.removeAll();
                        Label updateLabel = new Label("  Task table is updated.");
                        taskCreate.add(updateLabel, BorderLayout.WEST);
                        validate();
                        ((Manager)me).createTask(task_desc.getText(), choice.getSelectedItem());
                    }
                });
            }
        });
        checkTaskBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                taskCreate.setVisible(false);
                taskList.setVisible(true);
                loadTaskList(taskList);
                pack();
                validate();
            }
        });
    }
    private void initBody(Panel body, Panel taskCreate, Panel taskList) {
        GridLayout l = new GridLayout(0, 1);
        taskList.setLayout(l);
        body.add(taskCreate, BorderLayout.CENTER);
        body.add(taskList, BorderLayout.CENTER);
    }
    private void loadTaskList(Panel taskList) {
        taskList.removeAll();
        ArrayList<Task> tasks = TaskList.getTasks();
        for(int i=0; i<tasks.size(); i++) {
            if(me.role.equals("worker")) {
                if(!tasks.get(i).getAssignee().equals(me.getName())) { continue; }
            }
            Panel row = new Panel();
            row.setLayout(new GridLayout(1,4));
            Label id = new Label(Integer.toString(tasks.get(i).getTaskId()));
            Label desc = new Label(tasks.get(i).getDesc());
            Label completed = new Label(tasks.get(i).getCompleted().toString());
            Label assignee = new Label(tasks.get(i).getAssignee());
            Checkbox checkbox = new Checkbox( "" );
            checkbox.addItemListener(new ItemListener() {
                @Override public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        ((Worker)me).completeTask(Integer.parseInt(id.getText()));
                    }else {
                        ((Worker)me).undoTask(Integer.parseInt(id.getText()));
                    }
                }
            } );
            id.setAlignment(Label.LEFT);
            assignee.setAlignment(Label.CENTER);
            completed.setAlignment(Label.RIGHT);
            row.add(id);
            row.add(desc);
            row.add(assignee);
            if(me.role.equals("worker")) {
                row.add(checkbox);
                checkbox.setState(tasks.get(i).getCompleted());
            }else {
                row.add(completed);
            }
            taskList.add(row);
        }
    }
}

