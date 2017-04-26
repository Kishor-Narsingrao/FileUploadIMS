package com.ronivi.ims;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends ActionBarActivity implements IResultCallBack {

    EditText etEmail,etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
//                    AsyncTask_WebAPI asyncTask=new AsyncTask_WebAPI(Login.this,"localhost",Login.this);
//                    asyncTask.execute();

                    Intent intent=new Intent(Login.this,SearchActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                }
            }
        });
    }

    private boolean validate() {
        if(etEmail.getText().length() <=0 && etPassword.getText().length() <=0)
        {
            Toast.makeText(this,"Please Enter the Email Id and Password",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(etEmail.getText().length() <=0 || etEmail.getText().toString().trim().equals(""))
        {
            Toast.makeText(this,"Please Enter the Email Id",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(etPassword.getText().length() <=0)
        {
            Toast.makeText(this,"Please enter the Password",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }

    }

    private void init() {
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
    }

    @Override
    public void onResultListener(String object) {
        Intent intent=new Intent(Login.this,SearchActivity.class);
        startActivity(intent);
        Login.this.finish();
    }
}
