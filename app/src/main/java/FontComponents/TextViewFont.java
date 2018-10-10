package FontComponents;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by N550J on 12/1/2017.
 */
public class TextViewFont extends TextView {

    private static final String TAG = "TextView";
    public TextViewFont(Context context)
    {
        super(context);
    }

    public TextViewFont(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
        init();
    }

    public TextViewFont(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        CustomFontHelper.setCustomFont(this, context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViewFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        CustomFontHelper.setCustomFont(this, context, attrs);
        init();
    }

    private void init() {
        Typeface font=Typeface.createFromAsset(getContext().getAssets(), "fonts/IranSansFaNum.ttf");
        this.setTypeface(font);
    }
}

