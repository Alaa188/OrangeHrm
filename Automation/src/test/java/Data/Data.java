package Data;

public class Data {
    public static String username = "Admin";
    public static String password="admin123";
    public static String uniqueUsername = "AlaaAuto1";
    public static String uniquePassword="AbCdefgh@123455";
    public static String userRoleAdmin="Admin"; //Admin or Ess
    public static String userRoleEss="Ess"; //Admin or Ess
    public static String statusD="disabled"; //Enabled or disabled
    public static String statusE="Enabled"; //Enabled or disabled
    public static String URL="https://opensource-demo.orangehrmlive.com/web/index.php";
    public static String savedSuccessMSG="Successfully Saved";
    public static String getAdditionPageURL(){
        return URL+"/admin/saveSystemUser";
    }
    public static String getUsersURL(){
        return URL+"/admin/viewSystemUsers";
    }
}
