package com.example.android.a6things.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.android.a6things.R;

/**
 * Created by holly on 8/21/2017.
 */

public class CustomAdapter extends ArrayAdapter<ListItemModel> {

    ListItemModel[] listItemArray = null;
    Context context;

    public CustomAdapter(Context context, ListItemModel[] resource) {
        super(context, R.layout.row, resource);
        this.context = context;
        this.listItemArray = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        /**
        DisplayMetrics dm = new DisplayMetrics();
        this.getContext().getResources().getDisplayMetrics();
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y) * dm.scaledDensity;
*/

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);

        TextView item = (TextView) convertView.findViewById(R.id.defaultTextView);

        CheckBox cb = (CheckBox) convertView.findViewById(R.id.defaultCheckBox);
        item.setText(listItemArray[position].getItemLabel());
        if(listItemArray[position].isItemCompleted()) {
            cb.setChecked(true);
        }
        else {
            cb.setChecked(false);
        }
        return convertView;
    }
}
