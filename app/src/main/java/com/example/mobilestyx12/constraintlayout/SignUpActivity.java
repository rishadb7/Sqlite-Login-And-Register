package com.example.mobilestyx12.constraintlayout;

import android.content.ContentValues;
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

public class SignUpActivity extends AppCompatActivity {

    EditText activity_signup_name,activity_sign_up_email,activity_sign_up_password,activity_signup_phone;
    Button activity_sign_up_reg;
    TextView activity_sign_up_member;
    SQLiteDatabase db ;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        activity_signup_name=(EditText)findViewById(R.id.activity_signup_name);
        activity_sign_up_email=(EditText)findViewById(R.id.activity_sign_up_email);
        activity_sign_up_password=(EditText)findViewById(R.id.activity_sign_up_password);
        activity_signup_phone=(EditText)findViewById(R.id.activity_signup_phone);


        activity_sign_up_reg=(Button)findViewById(R.id.activity_sign_up_reg);
        activity_sign_up_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkUsers(activity_sign_up_email.getText().toString().trim());
               insertUsers(activity_signup_name.getText().toString().trim(),
                       activity_sign_up_email.getText().toString().trim(),
                       activity_sign_up_password.getText().toString().trim(),
                       activity_signup_phone.getText().toString().trim());
            }
        });

        activity_sign_up_member=(TextView)findViewById(R.id.activity_sign_up_member);
        activity_sign_up_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void insertUsers(String name1,String email1,String password1,String phone1){


        if (flag == 0) {

            try {
                db = getApplicationContext().openOrCreateDatabase("Demo", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                db.execSQL("CREATE TABLE users (id integer PRIMARY KEY AUTOINCREMENT, name text, email text, password text, phone text)");

            } catch (Exception e) {

                e.printStackTrace();

            }

            ContentValues values = new ContentValues();

            values.put("name", name1);
            values.put("email", email1);
            values.put("password", password1);
            values.put("phone", phone1);


            if ((db.insert("users", null, values)) != -1) {


                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_LONG).show();
            }

        }
        else if(flag == 1)
        {

            Toast.makeText(getApplicationContext(), "This user is already registered", Toast.LENGTH_LONG).show();

        }

    }

    public void checkUsers(String emailId)
    {
        try
        {

            db =getApplicationContext().openOrCreateDatabase("Demo", SQLiteDatabase.CREATE_IF_NECESSARY, null);


            Cursor m = db.rawQuery("SELECT * FROM users", null);

            m.moveToFirst();
            while (!m.isAfterLast())
            {
                String s0 = m.getString(0);
                String s1 = m.getString(1);
                String s2 = m.getString(2);

                  // Toast.makeText(getApplicationContext(),s0+" "+s1+" "+s2,Toast.LENGTH_SHORT).show();
                if (s2.equals(emailId))
                {
                    flag = 1;
                    // addToCart.setText(getResources().getString(R.string.added));
                }

                m.moveToNext();


            }
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
            System.out.print("ERROR.............");
        }


    }
}
