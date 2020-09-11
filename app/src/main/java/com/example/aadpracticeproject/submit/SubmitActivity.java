package com.example.aadpracticeproject.submit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aadpracticeproject.R;
import com.example.aadpracticeproject.leaderboard.MainActivity;
import com.example.aadpracticeproject.retrofit.LeaderService;
import com.example.aadpracticeproject.retrofit.ServiceBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    private static final String SUBMISSION_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText emailEt;
    private EditText projectLinkEt;
    private Dialog mDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private String emailString;
    private String firstNameString;
    private String lastNameString;
    private String projrctUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.submit_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setAlertDialog();

        setAlertDialogBuilder();


        firstNameEt = findViewById(R.id.first_name);
        lastNameEt = findViewById(R.id.last_name);
        emailEt = findViewById(R.id.email);
        projectLinkEt = findViewById(R.id.github);

    }

    private void setAlertDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(R.layout.loading_dialog);
        mDialog = builder.create();
        mDialog.setCancelable(false);
    }

    private void setAlertDialogBuilder() {
        alertDialogBuilder = new AlertDialog.Builder(this).setTitle("Submit Project")
                .setMessage("Do you really want to submit?")
                .setIcon(R.drawable.ic_baseline_info_24)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        submit();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SubmitActivity.this, "Submission Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void submit() {
        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);

        Call<Void> learningLeadersRequest = leaderService.submitProject(SUBMISSION_URL, emailString, firstNameString, lastNameString, projrctUrl);
        mDialog.show();
        learningLeadersRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mDialog.dismiss();
                if(response.isSuccessful()){
                successfulResponceAlert();
                }
                else {
                    failedResponceAlert();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mDialog.dismiss();
                failedResponceAlert();

            }
        });
    }

    private void successfulResponceAlert() {
        alertDialogBuilder
                .setCancelable(false)
                .setMessage("Submission Successful").setIcon(R.drawable.ic_baseline_check_circle_24)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("", null).show();
    }

    private void failedResponceAlert() {
        alertDialogBuilder
                .setCancelable(false)
                .setMessage("Submission not successful").setIcon(R.drawable.ic_baseline_error_24)
                .setNegativeButton("OK", null).show();
    }


    public void submitProject(View view) {
        emailString = emailEt.getText().toString();
        firstNameString = firstNameEt.getText().toString();
        lastNameString = lastNameEt.getText().toString();
        projrctUrl = projectLinkEt.getText().toString();

        if(emailString.isEmpty() || firstNameString.isEmpty() || lastNameString.isEmpty() || projrctUrl.isEmpty()){
            Toast.makeText(this, "Please fill in required fields", Toast.LENGTH_SHORT).show();
        }

        else {

            alertDialogBuilder.show();

        }
    }
}