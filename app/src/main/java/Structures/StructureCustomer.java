package Structures;

/**
 * Created by N550J on 2/16/2018.
 */

public class StructureCustomer {


    public String authorizationToken;
    public int tenantId;

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public Profile profile;
    public BasicInfo basicInfo;
    public GoalInfo goalInfo;
    public MedicalInfo medicalInfo;
    public FoodHabitInfo foodHabitInfo;
    public OtherInfo otherInfo;
    public ActivityInfo activityInfo;
    public AdditionalInfo additionalInfo;

    public StructureCustomer()
    {
        this.profile = new Profile();
        this.basicInfo = new BasicInfo();
        this.goalInfo = new GoalInfo();
        this.medicalInfo = new MedicalInfo();
        this.foodHabitInfo = new FoodHabitInfo();
        this.otherInfo = new OtherInfo();
        this.activityInfo = new ActivityInfo();
        this.additionalInfo = new AdditionalInfo();
    }

}
