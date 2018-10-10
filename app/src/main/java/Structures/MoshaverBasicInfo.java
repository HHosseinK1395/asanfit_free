package Structures;

/**
 * Created by N550J on 3/15/2018.
 */

public class MoshaverBasicInfo {
    private MoshaverBasicInfoInner basicInfo;
    private String biography;
    private String curriculumVitae;
    private String Info;
    private Rating rating;
    private AdditionalInfo additionalInfo;


    public MoshaverBasicInfo() {
        this.basicInfo = new MoshaverBasicInfoInner();
        this.rating = new Rating();
        this.additionalInfo = new AdditionalInfo();
    }

    public MoshaverBasicInfoInner getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(MoshaverBasicInfoInner basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    class Rating
    {

    }

    class AdditionalInfo
    {

    }

}


