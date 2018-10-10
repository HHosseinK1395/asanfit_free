package Others;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 11/30/2017.
 */
public class Globals {
    Context myContext;
    public static RecyclerView recyclerView;
    public static DrawerLayout drawerLayout;
    public static ImageView toolbarBack;
    public static final int PICKFILE_RESULT_CODE = 1;
    public static String netWorkError = "خطای شبکه";


    public Globals(Context context) {
        this.myContext = context;
    }

    public float InitArcView(DecoView decoView, final TextView myTextView, final float iDegree, float iLineWidth, int iRange, int iColor, boolean bColor, int delay, final boolean bType)
    {
        final float shakhesFirst = iDegree;
        final float[] shakhesEnd = {0};

        if(bColor)
        {
            decoView.addSeries(new SeriesItem.Builder(iColor)
                    .setRange(0, 100, 100)
                    .setInitialVisibility(false)
                    .setLineWidth(iLineWidth)
                    .build());
        }
        else {
            decoView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                    .setRange(0, 100, 100)
                    .setInitialVisibility(false)
                    .setLineWidth(iLineWidth)
                    .build());
        }

        final SeriesItem seriesItem = new SeriesItem.Builder(iColor)//.argb(255, 64, 196, 0))
                .setRange(0, iRange, 0)
                .setLineWidth(iLineWidth)
                .setInitialVisibility(true)
                .setSpinClockwise(true)
                .build();

        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener()
        {
            @Override
            public void onSeriesItemAnimationProgress(float v, float v1)
            {
                int i = (int)v1;
                if(bType)
                    myTextView.setText(String.valueOf(i) + "%");
                else
                    myTextView.setText(String.valueOf(i));
            }

            @Override
            public void onSeriesItemDisplayProgress(float v)
            {

            }
        });

        int series1Index = decoView.addSeries(seriesItem);
        decoView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(500)
                .setDuration(1000)
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {

                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {
                        shakhesEnd[0] = shakhesFirst;
                    }
                })
                .build());

        decoView.addEvent(new DecoEvent
                .Builder(iDegree)
                .setIndex(series1Index)
                .setDelay(delay)
                .build());

        return shakhesEnd[0];
    }

    public void changeEditText(final MyEditText edtOne, TextViewFont txtOne, final MyEditText edtTwo, TextViewFont txtTwo, int iType)
    {
        switch (iType)
        {
            case 0: // focused in
            {
                edtOne.animate().alpha(0.0f);
                edtOne.setVisibility(View.GONE);
                edtTwo.animate().alpha(1.0f);
                edtTwo.setVisibility(View.VISIBLE);
                edtTwo.requestFocus();
                if(txtOne!=null)
                    txtOne.setTextColor(this.myContext.getResources().getColor(R.color.mycolor_yellow_text));
                if(txtTwo!=null)
                    txtTwo.setTextColor(this.myContext.getResources().getColor(R.color.mycolor_yellow_text));
            }
            break;
            case 1: //focused out
            {
                if(edtTwo.getText().toString().equals("") || edtTwo.getText().toString().equals(null)) {
                    edtOne.animate().alpha(1.0f);
                    edtOne.setVisibility(View.VISIBLE);
                    edtTwo.animate().alpha(0.0f);
                    edtTwo.setVisibility(View.GONE);

                    if (txtOne != null)
                        txtOne.setTextColor(this.myContext.getResources().getColor(R.color.gray));
                    if (txtTwo != null)
                        txtTwo.setTextColor(this.myContext.getResources().getColor(R.color.gray));
                }
                else
                {
                    edtOne.animate().alpha(1.0f);
                    edtOne.setVisibility(View.VISIBLE);
                    edtTwo.animate().alpha(0.0f);
                    edtTwo.setVisibility(View.GONE);

                    edtOne.setHint(edtTwo.getText().toString());

                    if (txtOne != null)
                        txtOne.setTextColor(this.myContext.getResources().getColor(R.color.gray));
                    if (txtTwo != null)
                        txtTwo.setTextColor(this.myContext.getResources().getColor(R.color.gray));
                }
            }
            break;
            default:
            {

            }
            break;
        }
    }
}
