package Others;

/**
 * Created by N550J on 11/30/2017.
 */
public class StructureNavigation {
    String textView;
    int imageView;
    int iMessageCount;

    public StructureNavigation(String textView, int imageView, int strMessageCount) {
        this.textView = textView;
        this.imageView = imageView;
        this.iMessageCount = strMessageCount;
    }

    public String getText() {
        return textView;
    }

    public void setText(String textView) {
        this.textView = textView;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    private static int lastContactId = 0;

    public int getiMessageCount() {
        return iMessageCount;
    }

    public void setiMessageCount(int iMessageCount) {
        this.iMessageCount = iMessageCount;
    }
}
