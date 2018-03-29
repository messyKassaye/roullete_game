package com.example.meseret.roulette;

import android.content.Context;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextViewWithImages extends TextView {
    private static final Factory spannableFactory = Factory.getInstance();

    public TextViewWithImages(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TextViewWithImages(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewWithImages(Context context) {
        super(context);
    }

    public void setText(CharSequence text, BufferType type) {
        super.setText(getTextWithImages(getContext(), text), BufferType.SPANNABLE);
    }

    private static boolean addImages(Context context, Spannable spannable) {
        boolean hasChanges = false;
        Matcher matcher = Pattern.compile("\\Q[img src=\\E([a-zA-Z0-9_]+?)\\Q/]\\E").matcher(spannable);
        while (matcher.find()) {
            boolean set = true;
            for (ImageSpan span : (ImageSpan[]) spannable.getSpans(matcher.start(), matcher.end(), ImageSpan.class)) {
                if (spannable.getSpanStart(span) < matcher.start() || spannable.getSpanEnd(span) > matcher.end()) {
                    set = false;
                    break;
                }
                spannable.removeSpan(span);
            }
            int id = context.getResources().getIdentifier(spannable.subSequence(matcher.start(1), matcher.end(1)).toString().trim(), "drawable", context.getPackageName());
            if (set) {
                hasChanges = true;
                spannable.setSpan(new ImageSpan(context, id), matcher.start(), matcher.end(), 33);
            }
        }
        return hasChanges;
    }

    private static Spannable getTextWithImages(Context context, CharSequence text) {
        Spannable spannable = spannableFactory.newSpannable(text);
        addImages(context, spannable);
        return spannable;
    }
}
