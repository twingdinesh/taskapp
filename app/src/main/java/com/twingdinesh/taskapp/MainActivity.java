package com.twingdinesh.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView username,userid,loginid,followers,following,repo,bio,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        userid=findViewById(R.id.userid);
        loginid=findViewById(R.id.login);
        followers=findViewById(R.id.follwers);
        following=findViewById(R.id.following);
        repo=findViewById(R.id.repo);
        bio=findViewById(R.id.bio);
        location=findViewById(R.id.location);
        String url="https://api.github.com/users/akashsarkar188";
        StringRequest stringRequest= new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    username.setText(object.getString("name"));
                    userid.setText(object.getString("id"));
                    loginid.setText(object.getString("login"));
                    followers.setText(object.getString("followers"));
                    following.setText(object.getString("following"));
                    repo.setText(object.getString("public_repos"));
                    bio.setText(object.getString("bio"));
                    location.setText(object.getString("location"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error occured", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
