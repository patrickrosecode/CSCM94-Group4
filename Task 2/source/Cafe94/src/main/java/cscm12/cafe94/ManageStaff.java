package cscm12.cafe94;

/**[ManageStaff]
 * Manage staff class is responsible for creating and assigning staffs to hours and roles.
 * @author Sumi Sunuwar
 * @version 1.0
 */
public class ManageStaff{
    private String staffFName;
    private String staffLName;
    private String staffType;
    private Integer hoursToWork;
    private String staffUsername;
    private String staffPassword;

    /**[ManageStaff]
     * Constructor to create staff objects.
     */
    public ManageStaff(String staffFName, String staffLName, String staffType, int hoursToWork,
                       String staffUsername, String staffPassword) {
        this.staffUsername = staffUsername;
        this.staffType = staffType;
        this.staffFName = staffFName;
        this.staffLName = staffLName;
        this.hoursToWork = hoursToWork;
        this.staffPassword = staffPassword;
    }

    public String getStaffFName() {
        return staffFName;
    }

    public void setStaffFName(String staffFName) {
        this.staffFName = staffFName;
    }

    public String getStaffLName() {
        return staffLName;
    }

    public void setStaffLName(String staffLName) {
        this.staffLName = staffLName;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public Integer getHoursToWork() {
        return hoursToWork;
    }

    public void setHoursToWork(Integer hoursToWork) {
        this.hoursToWork = hoursToWork;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
}
