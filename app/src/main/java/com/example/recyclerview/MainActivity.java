package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSubmit,btnView;
    EditText eName,eDob,ePhone,eEmail;
    AutoCompleteTextView Image;
    Spinner Country;

    RadioGroup Gender;
    String Name,Dob,gender,country,Phone,Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName=findViewById(R.id.etName);
        eDob=findViewById(R.id.etDob);
        ePhone=findViewById(R.id.etPhone);
        eEmail=findViewById(R.id.etEmail);
        Country=findViewById(R.id.spCountry);
        Image=findViewById(R.id.etImage);
        Gender=findViewById(R.id.rgGender);


        btnSubmit=findViewById(R.id.btnSubmit);
        btnView=findViewById(R.id.btnView);


     btnSubmit.setOnClickListener(this);
     btnView.setOnClickListener(this);
     eDob.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


    }
}
