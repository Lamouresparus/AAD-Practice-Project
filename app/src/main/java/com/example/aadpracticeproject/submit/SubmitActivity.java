package com.example.aadpracticeproject.submit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aadpracticeproject.R;
import com.example.aadpracticeproject.model.Leader;
import com.example.aadpracticeproject.retrofit.LeaderService;
import com.example.aadpracticeproject.retrofit.ServiceBuilder;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;

public class SubmitActivity extends AppCompatActivity {

    public static final String SUBMISSION_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText projectLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.submit_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        projectLink = findViewById(R.id.github);

    }

    public void submitProject(View view) {


        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);

        Call<Void> learningLeadersRequest = leaderService.submitProject(SUBMISSION_URL, email.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), projectLink.getText().toString());

    }
}