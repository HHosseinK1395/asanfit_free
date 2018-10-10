package Structures;

/**
 * Created by N550J on 2/15/2018.
 */

public class Url {

    private static String BaseUrl = "http://hemad.halehnouri.com";

    //Account
    public static String ACCOUNTREGISTER = "http://hemad.halehnouri.com/api/services/app/Account/Register";//POST

    //TokenAuth
    public static String TOKENAUTHAUTHENTICATEURL = "http://hemad.halehnouri.com/api/TokenAuth/Authenticate";//POST

    //User
    public static String USERGETPROFILEDATAURL = "http://hemad.halehnouri.com/api/services/app/UserProfile/GetMyProfileData";//GET
    public static String USERUPDATEPROFILEDATA = "http://hemad.halehnouri.com/api/services/app/UserProfile/UpdateMyProfileData";//PUT


    //Daily Record
    public static String GETMYDAILYRECORD = "http://hemad.halehnouri.com/api/services/app/DailyRecord/GetMyDailyRecord";//GET
    public static String GETMYALLDAILYRECORD = "http://hemad.halehnouri.com/api/services/app/DailyRecord/GetAllMyDailyRecords";//GET
    public static String UPDATEMYDAILYRECORD = "http://hemad.halehnouri.com/api/services/app/DailyRecord/UpdateMyDailyRecord";//PUT

    //Assignment
    public static String GETALLMYACTIVECONSULTANT = "http://hemad.halehnouri.com/api/services/app/Assignment/GetAllMyConsultant";//GET
    public static String GETALLMYASSINMENTS = "http://hemad.halehnouri.com/api/services/app/Assignment/GetAllMyAssignments";//GET

    //Plan
    public static String PLANGET = BaseUrl + "/api/services/app/Plan/Get";//Get
    public static String PLANGETALL = "http://hemad.halehnouri.com/api/services/app/Plan/GetAll";//GET
    //Advise Data
    //Data recepi


    //Purchase
    public static String PURCHASE = "http://hemad.halehnouri.com/api/services/app/Purchase/Purchase";//POST



    //Diet
    public static String GETDIET = "http://hemad.halehnouri.com/api/services/app/Diet/Get";//GET
    public static String GETALLDIET = "http://hemad.halehnouri.com/api/services/app/Diet/GetAllMyDiets";//GET

    //Notification
    public static String GETNOTIFICATION = "http://hemad.halehnouri.com/api/services/app/MotivationalTip/GetAllDailyMotivationalTips";//GET







    //Moshaver Profile
    public static String CONSULTANTSERVICEGET = BaseUrl + "/api/services/app/ConsultantService/Get";//Get
    public static String CONSULTANTSERVICEGETALL = BaseUrl + "/api/services/app/ConsultantService/GetAll";//Get

    //Basic Information -> Customer
    public static String CUSTOMERREGISTER = "http://hemad.halehnouri.com/api/services/app/Customer/Register";//Post
    public static String CUSTOMERGET = BaseUrl + "/api/services/app/Customer/Get";//Get
    public static String CUSTOMERGETALL = BaseUrl + "/api/services/app/Customer/GetAll";//Get


    //Chat
    public static String CHATSENDMESSAGE = BaseUrl + "/api/services/app/Chat/SendMessage";//Post
    public static String SENDFILE = BaseUrl + "/api/services/app/Chat/SendFile";//Post
    public static String CHATRECEIVEFILE = BaseUrl + "/api/services/app/Chat/ReceiveFile";//Post
    public static String CHATRECEIVEMESSAGES = BaseUrl + "/api/services/app/Chat/ReceiveMessages";//Post



}
