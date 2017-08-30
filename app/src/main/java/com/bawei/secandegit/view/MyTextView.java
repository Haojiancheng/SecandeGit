package com.bawei.secandegit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.secandegit.R;

/**a
 * Created by Administrator on 2017/8/30.
 * 1506A
 * 郝健澄
 */

public class MyTextView extends View {
    private String text;
    private int textcolor;
    private float textsize;


    public MyTextView(Context context) {
        super(context);
        initAttrs(null);
    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
        text = typedArray.getString(R.styleable.MyTextView_text);
        textcolor = typedArray.getColor(R.styleable.MyTextView_textcolor, 0xffff);
        textsize= typedArray.getDimension(R.styleable.MyTextView_textsize, 16);


    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textcolor);
        paint.setTextSize(textsize);
        canvas.drawText(text,10,100,paint);

    }
}
