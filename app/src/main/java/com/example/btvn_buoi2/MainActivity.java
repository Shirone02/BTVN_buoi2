package com.example.btvn_buoi2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private TextView tvSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //điền email trên git e
        //điền user name trên git
        edtUser = findViewById(R.id.edtEnterUser);
        edtPass = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intentHome);
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(getApplicationContext(), RegisterActivity.class);
                gotoSignUp.launch(intentSignUp);
            }
        });
    }

    ActivityResultLauncher<Intent> gotoSignUp = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                edtUser.setText(intent.getStringExtra("Username"));
                edtPass.setText(intent.getStringExtra("Password"));
                Log.d(TAG, "onActivityResult: RESULT_OK " + intent.getStringExtra("Username"));
                Log.d(TAG, "onActivityResult: RESULT_OK " + intent.getStringExtra("Password"));
            }
            if(result.getResultCode() == RESULT_CANCELED){
                Log.d(TAG, "onActivityResult: RESULT_CANCELLED");
                Toast.makeText(MainActivity.this, "User back", Toast.LENGTH_SHORT).show();
            }
        }
    });

}