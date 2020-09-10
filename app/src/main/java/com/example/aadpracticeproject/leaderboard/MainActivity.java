package com.example.aadpracticeproject.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aadpracticeproject.R;
import com.example.aadpracticeproject.submit.SubmitActivity;
import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.leader_board_viewpager);
        TabLayout tabLayout = findViewById(R.id.leader_board_tab_layout);

        viewPager.setAdapter(new LeaderBoardPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    public void launchSubmit(View view) {
        Intent intent = new Intent(this, SubmitActivity.class);
        startActivity(intent);
    }
}