package com.example.meghanapeddakotla.intern1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView forgot;
    private Button Register;
    private Button login;
    private TextView Name;
    private TextView Password;
    DatabaseHelper myDb = new DatabaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgot = (TextView) findViewById(R.id.tv_password);
        Register = (Button) findViewById(R.id.register_button);
        login = (Button) findViewById(R.id.login_button);
        Name =(TextView) findViewById(R.id.et_username);
        Password =(TextView) findViewById(R.id.et_password);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent;
                boolean exist = myDb.searchpass(Name.getText().toString(),Password.getText().toString());

                if(exist )
                {
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                else if(Name.getText().toString().equals(""))
                {
                    Name.setError("This field cannot be empty");
                }
                else if (Password.getText().toString().equals(""))
                {
                    Password.setError("This field cannot be empty");
                }
                else
                {
                    AlertDialog.Builder dialogBuilder1 = new AlertDialog.Builder(LoginActivity.this);
                    dialogBuilder1.setTitle("Error");
                    dialogBuilder1.setMessage("Please Enter valid user name and password");

                    dialogBuilder1.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                           Name.setText("");
                           Password.setText("");
                        }
                    });

                    AlertDialog alertDialog = dialogBuilder1.create();
                    alertDialog.show();
                }
            }
        });



    }


}
