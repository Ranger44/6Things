package com.example.android.a6things;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
            Intent newIntent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_edit_list) {
            Intent newIntent = new Intent(AboutActivity.this, EditListActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_notifications) {
            Intent newIntent = new Intent(AboutActivity.this, NotificationsActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_personalize) {
            Intent newIntent = new Intent(AboutActivity.this, PersonalizationActivity.class);
            startActivity(newIntent);
        }

        else if(id == R.id.action_about) {
            Intent newIntent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(newIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
