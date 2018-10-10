package Others;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by N550J on 1/5/2018.
 */

public class StructureSelectAdvisor {
    String strCommentHeader;
    int iRate;
    String strCommentBody;
    Bitmap btmUserProfile;

    public ImageView getImgTick() {
        return imgTick;
    }

    public void setImgTick(ImageView imgTick) {
        this.imgTick = imgTick;
    }

    ImageView imgTick;

    public StructureSelectAdvisor(String strCommentHeader, int iRate, String strCommentBody, Bitmap btmUserProfile) {
        this.strCommentHeader = strCommentHeader;
        this.iRate = iRate;
        this.strCommentBody = strCommentBody;
        this.btmUserProfile = btmUserProfile;
    }

    public String getStrCommentHeader() {
        return strCommentHeader;
    }

    public void setStrCommentHeader(String strCommentHeader) {
        this.strCommentHeader = strCommentHeader;
    }

    public int getiRate() {
        return iRate;
    }

    public void setiRate(int iRate) {
        this.iRate = iRate;
    }

    public String getStrCommentBody() {
        return strCommentBody;
    }

    public void setStrCommentBody(String strCommentBody) {
        this.strCommentBody = strCommentBody;
    }

    public Bitmap getBtmUserProfile() {
        return btmUserProfile;
    }

    public void setBtmUserProfile(Bitmap btmUserProfile) {
        this.btmUserProfile = btmUserProfile;
    }
}
