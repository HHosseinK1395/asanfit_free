package Others;

import com.google.gson.Gson;

import Structures.StructureCustomer;
import Structures.StructureDashbord;
import Structures.StructureFoodPlan;
import Structures.StructureHeader;
import Structures.StructureMoshaverProfile;
import Structures.StructureParvandehPezeshki;
import Structures.StructurePlan;
import Structures.StructurePlanBuyOrNot;
import Structures.StructurePurchase;
import Structures.StructureWeightForUpdate;

/**
 * Created by N550J on 2/11/2018.
 */

public class Settings {

    public static final String PREFER_NAME = "Reg";

    private static Settings properties = new Settings();

    static {
        properties = new Settings();
    }

    public static Settings getInstance()
    {
        if (properties == null) {
            properties = new Settings();
        }

        return properties;
    }


    public Settings(){

    }


    //Save all and Load all
    public void loadAll() {
        properties = new Gson().fromJson(PreferencesManager.getInstance().readValuesFromFile("properties"), Settings.class);

        properties = getInstance();
        if(properties.toodehBadani == null)
            properties.toodehBadani = new StructureCustomer();

        if(properties.profile == null)
            properties.profile = new StructureCustomer();

        if(properties.plan == null)
            properties.plan = new StructurePlan();


        if(properties.moshaverProfile == null)
            properties.moshaverProfile = new StructureMoshaverProfile();


        if(properties.parvandehPezeshki == null)
            properties.parvandehPezeshki = new StructureParvandehPezeshki();


        if(properties.foodPlan == null)
            properties.foodPlan = new StructureFoodPlan();


        if(properties.header == null)
            properties.header = new StructureHeader();

        if(properties.dashbord == null)
            properties.dashbord = new StructureDashbord();

        if(properties.weightForUpdate == null)
            properties.weightForUpdate = new StructureWeightForUpdate();

        if(structurePlanBuyOrNot == null)
            structurePlanBuyOrNot = new StructurePlanBuyOrNot();

        if(structurePurchase == null)
            structurePurchase = new StructurePurchase();
    }

    public void saveAll()
    {
        PreferencesManager.getInstance().writeValuesOnFile("properties", new Gson().toJson(properties));
    }

    private boolean bUserIsRegistered;
    private boolean bUserInfoMaleOrFemale;
    private String strUserInfoWeight;
    private String strUserInfoHeight;
    private String strUserInfoAge;
    private boolean bProfileInformationNotification;
    private boolean bProfileOtherNotification;
    private boolean bIsRegistered;
    private boolean bOneTimeFillSpinnerParvandeh;
    private boolean bOneTimeFillSpinnerProfile;
    private StructureCustomer toodehBadani;
    private StructureCustomer profile;
    private StructurePlan plan;
    private StructureMoshaverProfile moshaverProfile;
    private StructureParvandehPezeshki parvandehPezeshki;
    private StructureFoodPlan foodPlan;
    private StructureHeader header;
    private StructureWeightForUpdate weightForUpdate;
    private String globalToken;
    private String strNotificationMessage;
    private StructurePlanBuyOrNot structurePlanBuyOrNot;
    private StructureDashbord dashbord;
    private StructurePurchase structurePurchase;


    public boolean getbIsRegistered() {
        return bIsRegistered;
    }

    public void setbIsRegistered(boolean bIsRegistered) {
        this.bIsRegistered = bIsRegistered;
    }

    public boolean isbUserIsRegistered() {
        return bUserIsRegistered;
    }

    public void setbUserIsRegistered(boolean bUserIsRegistered) {
        this.bUserIsRegistered = bUserIsRegistered;
    }

    public boolean isbUserInfoMaleOrFemale() {
        return bUserInfoMaleOrFemale;
    }

    public void setbUserInfoMaleOrFemale(boolean bUserInfoMaleOrFemale) {
        this.bUserInfoMaleOrFemale = bUserInfoMaleOrFemale;
    }

    public String getStrUserInfoWeight() {
        return strUserInfoWeight;
    }

    public void setStrUserInfoWeight(String strUserInfoWeight) {
        this.strUserInfoWeight = strUserInfoWeight;
    }

    public String getStrUserInfoHeight() {
        return strUserInfoHeight;
    }

    public void setStrUserInfoHeight(String strUserInfoHeight) {
        this.strUserInfoHeight = strUserInfoHeight;
    }

    public String getStrUserInfoAge() {
        return strUserInfoAge;
    }

    public void setStrUserInfoAge(String strUserInfoAge) {
        this.strUserInfoAge = strUserInfoAge;
    }

    public boolean isbProfileInformationNotification() {
        return bProfileInformationNotification;
    }

    public void setbProfileInformationNotification(boolean bProfileInformationNotification) {
        this.bProfileInformationNotification = bProfileInformationNotification;
    }

    public boolean isbProfileOtherNotification() {
        return bProfileOtherNotification;
    }

    public void setbProfileOtherNotification(boolean bProfileOtherNotification) {
        this.bProfileOtherNotification = bProfileOtherNotification;
    }

    public StructureCustomer getToodehBadani() {
        return toodehBadani;
    }

    public void setToodehBadani(StructureCustomer toodehBadani) {
        this.toodehBadani = toodehBadani;
    }

    public String getGlobalToken() {
        return globalToken;
    }

    public void setGlobalToken(String globalToken) {
        this.globalToken = globalToken;
    }

    public StructureCustomer getProfile() {
        return profile;
    }

    public void setProfile(StructureCustomer profile) {
        this.profile = profile;
    }

    public StructurePlan getPlan() {
        return plan;
    }

    public void setPlan(StructurePlan plan) {
        this.plan = plan;
    }

    public StructureMoshaverProfile getMoshaverProfile() {
        return moshaverProfile;
    }

    public void setMoshaverProfile(StructureMoshaverProfile moshaverProfile) {
        this.moshaverProfile = moshaverProfile;
    }

    public StructureParvandehPezeshki getParvandehPezeshki() {
        return parvandehPezeshki;
    }

    public void setParvandehPezeshki(StructureParvandehPezeshki parvandehPezeshki) {
        this.parvandehPezeshki = parvandehPezeshki;
    }

//    public StructureFoodPlan getFoodPlan() {
//        if(foodPlan == null)
//            foodPlan = new StructureFoodPlan("", null);
//        return foodPlan;
//    }
//
//    public void setFoodPlan(StructureFoodPlan foodPlan) {
//        if(foodPlan == null)
//            foodPlan = new StructureFoodPlan("", null);
//        this.foodPlan = foodPlan;
//    }

    public StructureFoodPlan getFoodPlan() {
        return foodPlan;
    }

    public void setFoodPlan(StructureFoodPlan foodPlan) {
        this.foodPlan = foodPlan;
    }

    public StructureHeader getHeader() {
        return header;
    }

    public void setHeader(StructureHeader header) {
        this.header = header;
    }

    public StructureDashbord getDashbord() {
        return dashbord;
    }

    public void setDashbord(StructureDashbord dashbord) {
        this.dashbord = dashbord;
    }


    public boolean isbOneTimeFillSpinnerParvandeh() {
        return bOneTimeFillSpinnerParvandeh;
    }

    public void setbOneTimeFillSpinnerParvandeh(boolean bOneTimeFillSpinnerParvandeh) {
        this.bOneTimeFillSpinnerParvandeh = bOneTimeFillSpinnerParvandeh;
    }

    public boolean isbOneTimeFillSpinnerProfile() {
        return bOneTimeFillSpinnerProfile;
    }

    public void setbOneTimeFillSpinnerProfile(boolean bOneTimeFillSpinnerProfile) {
        this.bOneTimeFillSpinnerProfile = bOneTimeFillSpinnerProfile;
    }

    public String getStrNotificationMessage() {
        return strNotificationMessage;
    }

    public void setStrNotificationMessage(String strNotificationMessage) {
        this.strNotificationMessage = strNotificationMessage;
    }

    public StructureWeightForUpdate getWeightForUpdate() {
        return weightForUpdate;
    }

    public void setWeightForUpdate(StructureWeightForUpdate weightForUpdate) {
        this.weightForUpdate = weightForUpdate;
    }

    public StructurePlanBuyOrNot getStructurePlanBuyOrNot() {
        return structurePlanBuyOrNot;
    }

    public void setStructurePlanBuyOrNot(StructurePlanBuyOrNot structurePlanBuyOrNot) {
        this.structurePlanBuyOrNot = structurePlanBuyOrNot;
    }

    public StructurePurchase getPurchase() {
        return structurePurchase;
    }

    public void setPurchase(StructurePurchase purchase) {
        this.structurePurchase = purchase;
    }
}
