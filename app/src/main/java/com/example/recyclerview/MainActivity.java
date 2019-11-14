package com.example.recyclerview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recyclerview.Model.User;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener {
    final List<User> user = new ArrayList<>();
    Button btnSubmit,btnView;
    EditText eName,eDob,ePhone,eEmail;
    String[] images = {"shristi"};
    String[] countries = {"--Choose--","Nepal", "India", "Srinlanka", "Bhutan", "Maldives", "Myammar", "Pakistan", "Afganistan"};
    AutoCompleteTextView Image;
    Spinner Country;
    RadioButton male,female,other;
    RadioGroup Gender;
    String Name,Dob,gender,country,Phone,Email,image;


    Calendar calendar=Calendar.getInstance();

    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR,i);
            calendar.set( Calendar.MONTH,i1);
            calendar.set(Calendar.DAY_OF_MONTH,i2);
            String mydateFormat = "dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            eDob.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };

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
        male=findViewById(R.id.radioMale);
        female=findViewById(R.id.radioFemale);
        other=findViewById(R.id.radioOthers);
        Image=findViewById(R.id.etImage);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnView=findViewById(R.id.btnView);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(this, R.layout.images,images);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.images, countries);
        Image.setAdapter(stringArrayAdapter);
        Image.setThreshold(1);
     btnSubmit.setOnClickListener(this);
     btnView.setOnClickListener(this);
     eDob.setOnClickListener(this);
     Gender.setOnCheckedChangeListener(this);
     Country.setAdapter(adapter);




    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.radioMale) {
            gender = "Male";
            Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.radioFemale) {
            gender = "Female";
            Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.radioOthers) {
            gender = "Other";
            Toast.makeText(this, "Others", Toast.LENGTH_SHORT).show();
        }
    }

    private void setaValue() {
        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        setaValue();
        Name = eName.getText().toString();
        Email = eEmail.getText().toString();
        Dob = eDob.getText().toString();
        Phone = ePhone.getText().toString();
        image =Image.getText().toString();

        String uri = "@drawable/"+image;
        int resID = getResources().getIdentifier(uri, null, getPackageName());

        if (validate()) {
            if(view.getId() == R.id.btnSubmit){
                user.add(new User(Name,Dob,Email,gender,Phone,country,resID));


                Toast.makeText(this, "User has been added successfully", Toast.LENGTH_SHORT).show();
            }
        }
        if(view.getId() == R.id.etDob){
            new DatePickerDialog(this,mydatepicker,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        }
        if (view.getId() == R.id.btnView){
            Intent intent = new Intent(MainActivity.this,userlist.class);
            intent.putExtra("allusers",(Serializable) user);
            startActivity(intent);
        }
    }



    private boolean validate() {
        if (TextUtils.isEmpty(Name)) {
            eName.setError("Enter Name");
            return false;

        }
        if (TextUtils.isEmpty(Email)) {
            eEmail.setError("Enter Email");
            return false;

        }

        if (TextUtils.isEmpty(Dob)) {
            eDob.setError("Enter DOB");
            return false;

        }
        if (TextUtils.isEmpty(Phone)) {
            ePhone.setError("Enter Phone");
            return false;

        }
        if (TextUtils.isEmpty(image)) {
            Image.setError("Enter image");
            return false;

        }
        if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(!TextUtils.isDigitsOnly(Phone)){
            ePhone.setError("Invalid Phone");
            return false;

        }
        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            eEmail.setError("Invalid Email");
            return false;
        }

        return true;

    }
}
