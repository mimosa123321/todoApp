import java.util.ArrayList;

public final class StaffList {
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();
    public static ArrayList<Staff> getStaffs() { return staffs;}
    public static Staff getStaff(String staffId) {
        int len = staffs.size();
        for(int i = 0; i < len; i++) {
            Staff staff = staffs.get( i );
            if(staff.getStaffId().equals(staffId)) { return staff; }
        }
        return null;
    }
}
