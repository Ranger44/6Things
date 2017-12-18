package com.example.android.a6things;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.a6things.Data.ListContract;

import static com.example.android.a6things.Data.ListContract.ListEntry.COLUMN_COMPLETE;
import static com.example.android.a6things.Data.ListContract.ListEntry.COLUMN_TASK;

public class EditListActivity extends AppCompatActivity {
    //private DbHelper mDbHelper = new DbHelper(this);
    ContentValues values = new ContentValues();

    Button updateListBtn;
    EditText item1, item2, item3, item4, item5, item6;

    TextView itemTextView;

    public void changeTextState(CheckBox c) {
        if (c.isChecked()) {
            itemTextView.setPaintFlags(itemTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            itemTextView.setPaintFlags(itemTextView.getPaintFlags() & ~(Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);


        /**
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int numRows = 0;
        for(int i = 0; i < 6; i++) {
            db.insert(TABLE_NAME, null, values);
            numRows++;
        }

        if(numRows == 0) {
            Toast.makeText(this, "Error saving list", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Item saved with row id: " + numRows, Toast.LENGTH_SHORT).show();
        }
        */

        item1 = (EditText) findViewById(R.id.item1);
        item2 = (EditText) findViewById(R.id.item2);
        item3 = (EditText) findViewById(R.id.item3);
        item4 = (EditText) findViewById(R.id.item4);
        item5 = (EditText) findViewById(R.id.item5);
        item6 = (EditText) findViewById(R.id.item6);
        updateListBtn = (Button) findViewById(R.id.updateListBtn);

        updateListBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(EditListActivity.this, "save button clicked", Toast.LENGTH_LONG).show();
                insertNewItem();
                //exit activity
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent newIntent;
        switch (item.getItemId()) {
            case R.id.action_home:
                newIntent = new Intent(EditListActivity.this, MainActivity.class);
                startActivity(newIntent);
                return true;

            case R.id.action_edit_list:
                newIntent = new Intent(EditListActivity.this, EditListActivity.class);
                startActivity(newIntent);
                return true;

            case R.id.action_notifications:
                newIntent = new Intent(EditListActivity.this, NotificationsActivity.class);
                startActivity(newIntent);
                return true;

            case R.id.action_personalize:
                newIntent = new Intent(EditListActivity.this, PersonalizationActivity.class);
                startActivity(newIntent);
                return true;

            case R.id.action_about:
                newIntent = new Intent(EditListActivity.this, AboutActivity.class);
                startActivity(newIntent);
                return true;

        }
                return super.onOptionsItemSelected(item);
    }

    private void insertNewItem() {

        String item1String = item1.getText().toString().trim();

        //need to create strings for all fields
        //string to integer = integer.parseInt("1");


        //SQLiteDatabase db = mDbHelper.getWritableDatabase();

        values.put(COLUMN_TASK, item1String);
        values.put(COLUMN_COMPLETE, 0);
        Uri newUri = getContentResolver().insert(ListContract.ListEntry.CONTENT_URI, values);
        if(newUri == null) {
            Toast.makeText(this, getString(R.string.edit_insert_failed),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, getString(R.string.edit_insert_success), Toast.LENGTH_SHORT).show();
        }


        //explanation at: https://stackoverflow.com/questions/13311727/android-sqlite-insert-or-update
        //int id = (int)db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        //if (id == -1) {
        //    db.update(TABLE_NAME, values, COLUMN_TASK, new String[] {"1"});
        //}
    }

    private void displayDatabaseInfo() {
        String[] projection = {
                ListContract.ListEntry._ID,
                ListContract.ListEntry.COLUMN_TASK,
                ListContract.ListEntry.COLUMN_COMPLETE};
        Cursor cursor = getContentResolver().query(ListContract.ListEntry.CONTENT_URI, projection, null, null, null);
    }
}
