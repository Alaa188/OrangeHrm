package Data;

public class Data {
    //for open source
   // public static String username = "Admin";
   // public static String password="admin123";

    //for local
    public static String username = "alaasaeed";
    public static String password="Alaa@1222004";


    public static String uniquePassword="AbCdefgh@123455";
    public static String uniqueUsername = "Ccccccccccccccccccccc";
    public static String uniqueUsername1 = "AlaaAuto3";
    public static String firstName = "Alaa";
    public static String middleName = "Saeed";
    public static String lastName = "Taha";
    public static String employeeName = "Shaima a Saeed";
    public static String employeeName1 = "Alaa a Saeed";

    public static String userRoleAdmin="Admin"; //Admin or Ess
    public static String userRoleEss="ESS"; //Admin or Ess
    public static String statusD="disabled"; //Enabled or disabled
    public static String statusE="Enabled"; //Enabled or disabled

    //for open source
        //public static String URL="https://opensource-demo.orangehrmlive.com/web/index.php";
    //for local
    public static String URL="http://localhost/orangehrm-5.7/web/index.php";

    public static String savedSuccessMSG="Successfully Saved";
    public static String updatedSuccessMSG="Successfully Updated";
    public static String deletedSuccessMSG="Successfully Deleted";

    public static String getAdditionPageURLforUsers(){
        return URL+"/admin/saveSystemUser";
    }
    public static String getUsersURL(){
        return URL+"/admin/viewSystemUsers";
    }

}
