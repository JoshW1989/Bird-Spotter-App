package com.example.birdspotter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Ties the bird_item.xml to the recycler view
public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.BirdHolder> {
    private List<Bird> birds = new ArrayList<>();


    @NonNull
    @Override
    public BirdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bird_item, parent, false);
        return new BirdHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BirdHolder holder, int position) {
        Bird currentBird = birds.get(position);
        holder.textViewName.setText(currentBird.getName());
        holder.textViewDate.setText(currentBird.getDate());
        holder.textViewDescription.setText(currentBird.getDescription());
        holder.textViewLocation.setText(currentBird.getLocation());
    }

    @Override
    public int getItemCount() {
        return birds.size();
    }

    // Updates obervable when birds list is set
    public void setBirds(List<Bird> birds) {
        this.birds = birds;
        notifyDataSetChanged();
    }

    // The position of the selected bird for deleting entries
    public Bird getBirdAt(int position) {
        return birds.get(position);
    }

    // Binds elements of xml to the holder
    class BirdHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDate;
        private TextView textViewDescription;
        private TextView textViewLocation;

        public BirdHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewLocation = itemView.findViewById(R.id.text_view_location);

        }
    }

}
