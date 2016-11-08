package com.wmz.demo_w1.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class TextDrawableActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView mLv;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_text_drawable;
    }

    @Override
    protected void initData() {
        mGenerator=ColorGenerator.DEFAULT;

        ArrayList<String> mList = new ArrayList<>();
        mList.addAll(Arrays.asList(mTitlesnew));
        mLv.setAdapter(new MyCommonAdapter<String>(TextDrawableActivity.this,mList) {
            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                Drawable drawable=null;
                switch (position){
                    case 0:  //SAMPLE_RECT
                        drawable= TextDrawable.builder().buildRect("R", Color.BLUE);
                        break;
                    case 1:  //SAMPLE_ROUND_RECT
                        drawable=TextDrawable.builder().buildRoundRect("S",Color.CYAN,10);
                        break;
                    case 2:  //SAMPLE_ROUND
                        drawable=TextDrawable.builder().buildRound("圆",Color.LTGRAY);
                        break;
                    case 3:  //SAMPLE_RECT_BORDER
                        drawable=TextDrawable.builder().beginConfig()
                                .withBorder(5)
                                .endConfig()
                                .buildRect("粗", Color.RED);
                        break;
                    case 4:  //SAMPLE_ROUND_RECT_BORDER
                        drawable=TextDrawable.builder()
                                .beginConfig()
                                .withBorder(5)
                                .endConfig()
                                .buildRoundRect("S",Color.argb(220,122,122,1),10);
                        break;
                    case 5:  //SAMPLE_ROUND_BORDER
                        drawable=TextDrawable.builder()
                                .beginConfig().withBorder(5).endConfig()
                                .buildRound("圆", Color.LTGRAY);
                        break;
                    case 6:  //SAMPLE_MULTIPLE_LETTERS
                        drawable=TextDrawable.builder()
                                .beginConfig()
                                .fontSize(40)
                                .toUpperCase()
                                .endConfig()
                                .buildRect("AK", mGenerator.getColor("AK"));
                        break;
                    case 7:  //SAMPLE_FONT
                        drawable = TextDrawable.builder()
                                .beginConfig()
                                .textColor(Color.BLACK)
                                .useFont(Typeface.SERIF)
                                .bold()
                                .toUpperCase()
                                .endConfig()
                                .buildRect("a", Color.RED);
                        break;
                    case 8:  //SAMPLE_SIZE
                        drawable = TextDrawable.builder()
                                .beginConfig()
                                .textColor(Color.BLACK)
                                .fontSize(30) /* size in px */
                                .bold()
                                .toUpperCase()
                                .endConfig()
                                .buildRect("a", Color.RED);
                        break;
                    case 9:  //SAMPLE_ANIMATION
                        TextDrawable.IBuilder builder = TextDrawable.builder()
                                .rect();
                        AnimationDrawable animationDrawable = new AnimationDrawable();
                        for (int i = 10; i > 0; i--) {
                            TextDrawable frame = builder.build(String.valueOf(i), mGenerator.getRandomColor());
                            animationDrawable.addFrame(frame, 1200);
                        }
                        animationDrawable.setOneShot(false);
                        animationDrawable.start();
                        drawable=(Drawable)animationDrawable;
                        break;
                    case 10: //SAMPLE_MISC
                        drawable=TextDrawable.builder()
                                .buildRect("M", mGenerator.getColor("Misc"));
                        break;
                }
                if(drawable!=null){
                    ((ImageView) viewHolder.findViewById(R.id.img)).setImageDrawable(drawable);
                }
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return R.layout.item_bean;
            }
        });
    }

    private String[] mTitlesnew = {"SAMPLE_RECT"
            ,"SAMPLE_ROUND_RECT"
            ,"SAMPLE_ROUND"
            ,"SAMPLE_RECT_BORDER"
            ,"SAMPLE_ROUND_RECT_BORDER"
            ,"SAMPLE_ROUND_BORDER"
            ,"SAMPLE_MULTIPLE_LETTERS"
            ,"SAMPLE_FONT"
            ,"SAMPLE_SIZE"
            ,"SAMPLE_ANIMATION"
            ,"SAMPLE_MISC"
    };

    private ColorGenerator mGenerator;
}
