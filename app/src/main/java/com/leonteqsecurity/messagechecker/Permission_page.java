package com.leonteqsecurity.messagechecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Permission_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_page);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
    }

    public  void toMainMenu(View view)
    {
        Intent intent = new Intent(this,Mainmenu.class);
        startActivity(intent);
    }
}