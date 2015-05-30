package com.aitc.storage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileActivity extends AppCompatActivity {
    EditText fileName;
    EditText fileContent;
    TextView displayFileContents;
    Spinner fileList;
    Button saveButton;

    String[] files;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        files = new String[]{};

        fileName = (EditText) findViewById(R.id.file_name);
        fileContent = (EditText) findViewById(R.id.file_contents);
        displayFileContents = (TextView) findViewById(R.id.display_file);

        fileList = (Spinner) findViewById(R.id.file_list);
        getFiles();

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, files);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fileList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = null;
                try {
                    item = (String) parent.getItemAtPosition(position);

                    File file = new File(getFilesDir(), item);

                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fileInputStream));

                    String line = "";
                    String content = "";

                    while ((line = myReader.readLine()) != null) {
                        content += line + "\n";
                    }

                    displayFileContents.setText(content);

                    myReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("SpINNER", item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fileList.setAdapter(adapter);
        saveButton = (Button) findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileName.getText().toString() == null || fileContent.getText().toString() == null) {
                    Toast.makeText(FileActivity.this, "Please Enter A Filename and Content", Toast.LENGTH_LONG).show();
                }
                else {
                    String filename = fileName.getText().toString();
                    String content = fileContent.getText().toString();

                    File file = new File(getFilesDir(), filename);

                    FileOutputStream outputStream;

                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(content.getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    fileName.setText("");
                    fileContent.setText("");
                    getFiles();

                    adapter.notifyDataSetChanged();
                    Toast.makeText(FileActivity.this, "File Written", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file, menu);
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

        return super.onOptionsItemSelected(item);
    }

    private void getFiles() {
        files = getFilesDir().list();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
