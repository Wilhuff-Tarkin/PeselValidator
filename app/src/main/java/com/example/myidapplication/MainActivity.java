package com.example.myidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//                https://pl.wikipedia.org/wiki/PESEL


public class MainActivity extends AppCompatActivity {

    EditText etID;
    Button btnSubmit;
    TextView tvResult;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etID = findViewById(R.id.etID);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.textView);
        tvResult.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String peselNumber = etID.getText().toString().trim();


        if (isPeselValid(peselNumber)) {

            String monthOfBirth;
            String centuryOfBirth;

            switch (peselNumber.substring(2, 3)) {
                case "8":
                case "9":
                    centuryOfBirth = "18";
                    break;
                case "0":
                case "1":
                    centuryOfBirth = "19";
                    break;
                case "2":
                case "3":
                    centuryOfBirth = "20";
                    break;
                case "4":
                case "5":
                    centuryOfBirth = "21";
                    break;
                case "6":
                case "7":
                    centuryOfBirth = "22";
                    break;
                default:
                    centuryOfBirth = "19";
            }

            if (Integer.parseInt(peselNumber.substring(2, 3)) % 2 == 0) {
                monthOfBirth = "0" + peselNumber.substring(3, 4);
            } else {
                monthOfBirth = "1" + peselNumber.substring(3, 4);
            }


            int gender = Integer.parseInt(Character.toString(peselNumber.charAt(9)));

            String sGender;

            if (gender % 2 == 0) {
                sGender = getString(R.string.female);
            } else {
                sGender = getString(R.string.male);
            }

            String fullYearOfBirth = centuryOfBirth + peselNumber.substring(0, 2);

            String text = getString(R.string.yob) + " " + fullYearOfBirth + "\n" +
                    getString(R.string.mob) + " " + monthOfBirth + "\n" +
                    getString(R.string.gender) + " " + sGender + "\n";


            tvResult.setText(text);
            tvResult.setVisibility(View.VISIBLE);
        }
        else
        {
            String text = "PESEL niepoprawny";
            tvResult.setText(text);
            tvResult.setVisibility(View.VISIBLE);

        }

            }
        });




    }

    private boolean isPeselValid(String peselNumber) {

        //przyjmuje jeszcze za dlugie numery

        Pattern peselRegex = Pattern.compile("[0-9]{4}[0-3]{1}[0-9]{1}[0-9]{5}");
        Matcher matcher = peselRegex.matcher(peselNumber);

        return matcher.find();
    }


}
