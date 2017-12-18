package com.example.android.a6things.Data;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by holly on 8/21/2017.
 */

public class ListItemModel {
    private String itemLabel;
    boolean itemCompleted;
    TextView itemTextView;
    //itemTextView = (TextView) get

    public ListItemModel(String label, boolean itemCompleted) {
        this.itemLabel = label;
        this.itemCompleted = itemCompleted;
    }

    public String getItemLabel() {
        return this.itemLabel;
    }

    public boolean isItemCompleted() {
        return this.itemCompleted;
    }


    public void changeTextState() {
        if (isItemCompleted()) {
            itemTextView.setPaintFlags(itemTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            itemTextView.setPaintFlags(itemTextView.getPaintFlags() & ~(Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}
