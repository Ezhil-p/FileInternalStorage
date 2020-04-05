package com.example.fileinternalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText filename;
    EditText filecontent;
    Button storefile;
    Button showfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename=(EditText)findViewById(R.id.filename);
        filecontent=(EditText)findViewById(R.id.file_cnt);
        storefile=(Button)findViewById(R.id.store);
        storefile.setOnClickListener(this);
        showfile=(Button)findViewById(R.id.show_file);
        showfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store:
            try {
                FileOutputStream fout = openFileOutput(filename.getText().toString(), Context.MODE_PRIVATE);
                fout.write(filecontent.getText().toString().getBytes());
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
            case R.id.show_file:
                try {
                    FileInputStream fin=openFileInput(filename.getText().toString());
                    byte b[]=new byte[65535];
                    fin.read(b);
                    filecontent.setText(new String(b));
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
