package com.aitc.storage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


public class DatabaseActivity extends AppCompatActivity implements DatabaseActivityFragment.OnItemClickEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences.getBoolean("first_run", false) == false) {
            Log.d("ACTIVITY", "In activity");
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            DBHelper dbHelper = new DBHelper(DatabaseActivity.this);
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("Jane Sammuels", 48, "246-788-5965", "jane@awesome.com", "Senior Accountant", "45 Peanuts", "Cartoon Country");
            dbHelper.insertPerson("Martin Dueva", 50, "246-783-5287", "md@awesome.com", "Owner", "Head Above Drive", "Cartoon Country");
            dbHelper.insertPerson("Sol Parish", 58, "246-493-0262", "sol.par@webmine.com", "Artists Agent (Manager)", "6477 Rustic Lagoon Vale", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            dbHelper.insertPerson("John Smith", 22, "246-423-5446", "js@awesome.com", "Senior Accountant", "#123 Apple Drive", "Cartoon Country");
            Log.d("ACTIVITY", "PLEASE WORK");

            progressBar.setVisibility(View.GONE);
        }

        if (findViewById(R.id.fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            DatabaseActivityFragment databaseActivityFragment = new DatabaseActivityFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, databaseActivityFragment);
            fragmentTransaction.commit();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first_run", true);
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.go_to_file){
            startActivity(new Intent(DatabaseActivity.this, FileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(long id) {
        if (findViewById(R.id.fragment) != null) {
            // Create a new Fragment to be placed in the activity layout
            DisplayPersonFragment displayPersonFragment
                    = new DisplayPersonFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);

            displayPersonFragment.setArguments(bundle);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment, displayPersonFragment);
            transaction.addToBackStack("person");
            transaction.commit();
        }
    }
}
