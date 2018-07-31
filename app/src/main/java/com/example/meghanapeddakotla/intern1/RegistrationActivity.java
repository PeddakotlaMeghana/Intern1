package com.example.meghanapeddakotla.intern1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.regex.Pattern;


public class RegistrationActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])" +
            "(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$");

    private Button Login;
    private ImageButton image;
    private Button Register;
    private TextView Fname;
    private TextView Lname;
    private TextView email;
    private TextView password;
    private TextView conformpass;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Login = (Button) findViewById(R.id.login_button);
        image = (ImageButton) findViewById(R.id.imageview);
        Register = (Button) findViewById(R.id.register_button);
        Fname = (TextView) findViewById(R.id.et_fname);
        Lname = (TextView) findViewById(R.id.et_lname);
        email = (TextView) findViewById(R.id.et_email);
        password = (TextView) findViewById(R.id.et_pass);
        conformpass = (TextView) findViewById(R.id.et_conform);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = Fname.getText().toString();
                String Name2 = Lname.getText().toString();
                String password1 = password.getText().toString();
                String confpassword = conformpass.getText().toString();
                final String email1 = email.getText().toString();


                if(Name.isEmpty())
                {
                    Fname.setError("This field should not be empty");
                }
                else if(Name2.isEmpty())
                {
                    Lname.setError("This field should not be empty");
                }
                else if(email1.isEmpty())
                {
                    email.setError("This field should not be empty");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
                {
                    email.setError("Please Enter valid Mail address");
                }
                else if(password1.isEmpty())
                {
                    password.setError("This field should not be empty");
                }
                else if (!PASSWORD_PATTERN.matcher(password1).matches())
                {
                    password.setError("Password should contain minimum 8 characters with " +
                            "atleast 1 uppercase, 1 lowercase, 1 digit and 1 special character ");
                }
                else if(confpassword.isEmpty())
                {
                    conformpass.setError("This field should not be empty");
                }
                else if (!(confpassword.equals(password1)))
                {
                    conformpass.setError("The password you entered is not correct");
                }
                else
                {
                    contact c = new contact();
                    c.setFName(Name);
                    c.setLName(Name2);
                    c.setMail(email1);
                    c.setPassword(password1);
                    helper.insert(c);

                    AlertDialog.Builder dialogBuilder1 = new AlertDialog.Builder(RegistrationActivity.this);
                    dialogBuilder1.setTitle("Success");
                    dialogBuilder1.setMessage("Successfully Registered");

                    dialogBuilder1.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                        }
                    });

                    AlertDialog alertDialog = dialogBuilder1.create();
                    alertDialog.show();
                }
            }
        });
    }

}
