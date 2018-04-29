public class Staff {
    protected String staffId;
    protected String name;
    protected String role;
    protected String password;
    public Staff(){}
    public Staff(String staffId, String name, String role, String password){
        if (!setStaffId(staffId)) throw new RuntimeException("Wrong staff id!");
        if (!setName(name)) throw new RuntimeException("Wrong name!");
        if (!setRole(role)) throw new RuntimeException("Wrong role!");
        if (!setPassword(password)) throw new RuntimeException("Wrong password!");
    }
    public boolean setStaffId(String staffId){
        if (staffId != null){
            this.staffId = staffId;
            return true;
        }else return false;
    }
    public boolean setName(String name){
        if (name != null){
            this.name = name;
            return true;
        }else return false;
    }
    public boolean setRole(String role){
        if (role != null){
            this.role = role;
            return true;
        }else return false;
    }
    public boolean setPassword(String password){
        if (password != null){
            this.password = password;
            return true;
        }else return false;
    }
    public String getName() { return name; }
    public String getStaffId() { return staffId; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
}
