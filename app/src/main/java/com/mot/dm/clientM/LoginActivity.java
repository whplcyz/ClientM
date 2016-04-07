package com.mot.dm.clientM;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mot.dm.R;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
                new HttpRequestTask().execute();
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Greeting>
    {
        @Override
        protected Greeting doInBackground(Void...params)
        {
            try
            {
                String url = "http://rest-service.guides.spring.io/greeting";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                return greeting;
            }
            catch (Exception ex)
            {
                Log.e("MainActivity", ex.getMessage(),ex);
            }

            return  null;
        }

        protected void onPostExecute(Greeting greeting)
        {
            String id = greeting.getId();
            String content = greeting.getContent();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            EditText editText = (EditText)findViewById(R.id.user_name);
            String userName = editText.getText().toString();
            intent.putExtra("user_name", content);
            startActivity(intent);
            finish();
            Log.d("id", id);
        }
    }
}
