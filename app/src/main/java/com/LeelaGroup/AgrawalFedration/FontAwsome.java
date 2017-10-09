package com.LeelaGroup.AgrawalFedration;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by USer on 12-09-2017.
 */

public class FontAwsome extends TextView {


    public FontAwsome(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontAwsome(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontAwsome(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "font/fontawesome-webfont.ttf");
        setTypeface(tf);
    }
}
