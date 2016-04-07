package com.mot.dm.clientM;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mot.dm.R;
import com.mot.dm.client.delegation.ServiceDelegation;
import com.mot.dm.common.util.SecurityUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_login);

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServiceDelegation delegation = ServiceDelegation.getInstance();
                StringBuilder errorType = new StringBuilder();
                boolean result = SecurityUtils.login("superuser", "superuser", errorType);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                EditText editText = (EditText)findViewById(R.id.user_name);
                String userName = editText.getText().toString();
                intent.putExtra("user_name", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}
