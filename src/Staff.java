public class Staff {
    protected String name;
    protected String staffId;
    protected String role;
    public Staff(){}
    public Staff(String staffId, String name, String role){
        if (!setStaffId(staffId)) throw new RuntimeException("Wrong staff id!");
        if (!setName(name)) throw new RuntimeException("Wrong name!");
        if (!setRole(role)) throw new RuntimeException("Wrong role!");
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
}
