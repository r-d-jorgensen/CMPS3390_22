package a11.djorgensen.FarmForWinter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EndScreenActivity extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final ArrayList<Score> scores = new ArrayList<>();
    private RecyclerView listScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        this.listScores = findViewById(R.id.listScores);
        listScores.setAdapter(new RecycleAdapter(this, scores));
        listScores.setLayoutManager(new LinearLayoutManager(this));
        getScores();
    }

    private void getScores() {
        DatabaseReference ref = database.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                scores.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HashMap value = (HashMap) dataSnapshot.getValue();
                    Score tmp = new Score(value.get("value").toString());
                    scores.add(tmp);
                }
                Objects.requireNonNull(listScores.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void onPlayAgain(View view) {
        startActivity(new Intent(EndScreenActivity.this, GameActivity.class));
    }
}