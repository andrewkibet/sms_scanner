package com.leonteqsecurity.messagechecker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Mainmenu extends AppCompatActivity {

    private TextView textView;

    private static final int REQUEST_CODE_SMS_PERMISSION = 123;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        textView = findViewById(R.id.scannn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSMSPermissions(); // call the method to check permissions and read SMS messages
            }
        });

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
    }

    private void checkSMSPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS}, REQUEST_CODE_SMS_PERMISSION);
        } else {
            Intent launcherIntent = getPackageManager().getLaunchIntentForPackage(Telephony.Sms.getDefaultSmsPackage(this));
            if (launcherIntent !=null){

                startActivity(launcherIntent);
            } else {
                Toast.makeText(Mainmenu.this,"No Package",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_SMS_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // readSMS();
                } else {
                    Toast.makeText(this, "SMS permission is required to read SMS messages.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

/*    private void readSMS() {
        // Add your code to read SMS messages here

        String[] projection = {"_id", "address", "person", "body", "date", "type"};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = "date DESC";
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), projection, selection, selectionArgs, sortOrder);
        while (cursor!=null && cursor.moveToNext()) {
            String stringMessage = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            // Do something with stringMessage, such as display it in a TextView
            Log.d("ScanMessages", stringMessage); // print message to Logcat for debugging purposes
        }
        cursor.close();
    }

 */
}
