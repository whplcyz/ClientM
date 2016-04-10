package com.mot.dm.clientM;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mot.dm.R;
import com.mot.dm.clientM.Base.BaseActivity;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_login);
        //this.setTitle("Task ID:" + getTaskId());

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new HttpRequestTask().execute();
                //Intent intent = new Intent("com.qngv36.contact");
                //startActivity(intent);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                EditText editText = (EditText)findViewById(R.id.user_name);
                String userName = editText.getText().toString();
                intent.putExtra("user_name", userName);
                startActivity(intent);
                finish();
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Greeting>
    {
        @Override
        protected Greeting doInBackground(Void...params)
        {
            /*final String url = getString(R.string.base_uri) + "/getmessage";

            // Populate the HTTP Basic Authentitcation header with the username and password
            HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Message.class);
                return response.getBody();
            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new Message(0, e.getStatusText(), e.getLocalizedMessage());
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new Message(0, e.getClass().getSimpleName(), e.getLocalizedMessage());
            }*/

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
