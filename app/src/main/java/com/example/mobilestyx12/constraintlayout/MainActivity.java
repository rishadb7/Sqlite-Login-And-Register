package com.example.mobilestyx12.constraintlayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView  activity_main_reg;
    EditText activity_main_email,activity_main_password;
    Button activity_main_btnlgn;
    SQLiteDatabase db ;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main_reg=(TextView)findViewById(R.id.activity_main_reg);
        activity_main_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });

        activity_main_email=(EditText)findViewById(R.id.activity_main_email);
        activity_main_password=(EditText)findViewById(R.id.activity_main_password);
        activity_main_btnlgn=(Button)findViewById(R.id.activity_main_btnlgn);
        activity_main_btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUsers(activity_main_email.getText().toString().trim(),activity_main_password.getText().toString().trim());
            }
        });


    }


    public void loginUsers(String email,String password){



            try {

                db = getApplicationContext().openOrCreateDatabase("Demo", SQLiteDatabase.CREATE_IF_NECESSARY, null);

                Cursor m = db.rawQuery("SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "' ", null);
                if(m.getCount()==0){
                    Toast.makeText(getApplicationContext(), "Not Registered or check username and password", Toast.LENGTH_SHORT).show();
                }
                m.moveToFirst();
                while (!m.isAfterLast()) {
                    String s0 = m.getString(0);
                    String s1 = m.getString(1);
                    String s2 = m.getString(2);
                    String s3 = m.getString(3);
                    //  Toast.makeText(getApplicationContext(),s0+" "+s1+" "+s2,Toast.LENGTH_SHORT).show();


                    if (s2.equals(email) && s3.equals(password)) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                    }

                    m.moveToNext();


                }
            } catch (SQLiteException e) {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_SHORT).show();
            }




    }
}
