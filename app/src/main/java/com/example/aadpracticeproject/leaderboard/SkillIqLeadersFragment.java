package com.example.aadpracticeproject.leaderboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aadpracticeproject.R;
import com.example.aadpracticeproject.model.Leader;
import com.example.aadpracticeproject.model.LeaderBoardAdapter;
import com.example.aadpracticeproject.retrofit.LeaderService;
import com.example.aadpracticeproject.retrofit.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIqLeadersFragment extends Fragment {

    private Dialog mDialog;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LeaderBoardAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.loading_dialog);
        mDialog = builder.create();
        mDialog.setCancelable(false);


        View rootView = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        recyclerView =    rootView.findViewById(R.id.skill_iq_leaders_rv);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);

        Call<List<Leader>> skillIqLeadersRequest = leaderService.getSkillIqLeaders();
        mDialog.show();

        skillIqLeadersRequest.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> call, Response<List<Leader>> response) {
                mDialog.dismiss();
                if(response.isSuccessful()){

                    adapter = new LeaderBoardAdapter(response.body(),R.mipmap.ic_skill_iq, getString(R.string.skill_iq_score));
                    recyclerView.setAdapter(adapter);

                }
                else {
                    mDialog.dismiss();
                    Toast.makeText(getContext(), "Ooops! An Error Occured", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Leader>> call, Throwable t) {
                Toast.makeText(getContext(), "Ooops! An Error Occured", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}