package com.wmz.demo_w1.secondaryscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.wmz.demo_w1.R;

@SuppressLint("NewApi")
public class DifferentDisplay extends Presentation {

    public DifferentDisplay(Context outerContext,Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.android);
        setContentView(imageView);
    }

}
