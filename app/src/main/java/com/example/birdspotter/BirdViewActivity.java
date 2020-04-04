package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Activity for viewing and deleting the bird database entries
public class BirdViewActivity extends AppCompatActivity {

    private BirdViewModel birdViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_view);

        // Recycler view standard configuration
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Sets the adapter to the adapter class created
        final BirdAdapter adapter = new BirdAdapter();
        recyclerView.setAdapter(adapter);

        //Uses the view model to retrieve database entries, updates upon change to dataset
        birdViewModel = ViewModelProviders.of(this).get(BirdViewModel.class);
        birdViewModel.getAllBirds().observe(this, new Observer<List<Bird>>() {
            @Override
            public void onChanged(@Nullable List<Bird> birds) {
                adapter.setBirds(birds);
            }
        });

        // Code to delete item when swiping left
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                birdViewModel.delete(adapter.getBirdAt(viewHolder.getAdapterPosition()));
            }

        }).attachToRecyclerView(recyclerView);

    }
}