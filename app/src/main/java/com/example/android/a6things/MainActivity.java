package com.example.android.a6things;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.a6things.Data.CustomAdapter;
import com.example.android.a6things.Data.ListItemModel;

public class MainActivity extends AppCompatActivity {

    TextView itemTextView;
    //ListView itemListView;
    ListItemModel[] listItemsArray;
    private CustomAdapter adapter;

    public void onClickActions(final CheckBox a) {
        //a.changeTextState();
        a.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
            listItemsArray[1].changeTextState();
        }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entire_list);

        //itemListView = (ListView) findViewById(R.id.entireListView);

        listItemsArray = new ListItemModel[6];

        listItemsArray[0] = new ListItemModel("item1", false);
        listItemsArray[1] = new ListItemModel("item2", false);
        listItemsArray[2] = new ListItemModel("item3", false);
        listItemsArray[3] = new ListItemModel("item4", false);
        listItemsArray[4] = new ListItemModel("item5", false);
        listItemsArray[5] = new ListItemModel("item6", false);

       // adapter = new CustomAdapter(this, listItemsArray);
       // itemListView.setAdapter(adapter);

        LinearLayout entireListLayout = (LinearLayout) findViewById(R.id.entireListLayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (ListItemModel l : listItemsArray) {
            View view = inflater.inflate(R.layout.row, entireListLayout, false);
            entireListLayout.addView(view);
        }

        for(int i = 0; i < 6; i++) {
            TextView item = (TextView) findViewById(R.id.defaultTextView);
            CheckBox cb = (CheckBox) findViewById(R.id.defaultCheckBox);

            item.setText(listItemsArray[i].getItemLabel());

            if (listItemsArray[i].isItemCompleted()) {
                cb.setChecked(true);
            } else {
                cb.setChecked(false);
            }

            onClickActions(cb);
        }






       // ListView listView = (ListView) findViewById(R.id.entireListView);
        //listView.setAdapter(itemsAdapter);


        /**
        SQLiteDatabase db = ;
        Cursor cursor = db.query(ListContract.ListEntry.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToPosition(1); //moves to 1st row
        int nameColumnIndex = cursor.getColumnIndex(ListContract.ListEntry.COLUMN_TASK);
        String name = cursor.getString(nameColumnIndex);
*/
/**




        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        EditText item1 = (EditText) findViewById(R.id.item1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        EditText item2 = (EditText) findViewById(R.id.item2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        EditText item3 = (EditText) findViewById(R.id.item3);
        final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        EditText item4 = (EditText) findViewById(R.id.item4);
        final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        EditText item5 = (EditText) findViewById(R.id.item5);
        final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        EditText item6 = (EditText) findViewById(R.id.item6);

        onClickActions(checkBox1, item1);
        onClickActions(checkBox2, item2);
        onClickActions(checkBox3, item3);
        onClickActions(checkBox4, item4);
        onClickActions(checkBox5, item5);
        onClickActions(checkBox6, item6);

*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_home) {
            Intent newIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_edit_list) {
            Intent newIntent = new Intent(MainActivity.this, EditListActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_notifications) {
            Intent newIntent = new Intent(MainActivity.this, NotificationsActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_personalize) {
            Intent newIntent = new Intent(MainActivity.this, PersonalizationActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_about) {
            Intent newIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(newIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
