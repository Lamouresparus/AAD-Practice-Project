package com.example.aadpracticeproject.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aadpracticeproject.R;

import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private List<Leader> mLeaders;
    private int imageResource;
    private  String messageString;

    public LeaderBoardAdapter(List<Leader> leaders, int imageResource, String message) {
        this.mLeaders = leaders;
        this.imageResource = imageResource;
        messageString = message;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_board_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leader leader = mLeaders.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameTv;
        private TextView hoursTV;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.leader_name);
            hoursTV = itemView.findViewById(R.id.leader_score);
            imageView = itemView.findViewById(R.id.leader_image_view);

        }
        public void bind(Leader leader) {
            nameTv.setText(leader.getName());
            String text = leader.getHours()+" " + messageString + " " +leader.getCountry() +".";
            hoursTV.setText(text);
            imageView.setImageResource(imageResource);

        }
    }
}
