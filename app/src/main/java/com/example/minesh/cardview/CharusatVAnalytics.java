package com.example.minesh.cardview;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CharusatVAnalytics extends AppCompatActivity {

    private TextView msgText, crushText;
    private DatabaseReference ref;
    private long msgCount=0;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charusat_vanalytics);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Analysis");

        msgText=findViewById(R.id.textViewNoMsg);
        crushText=findViewById(R.id.textViewNoCrush);

        ref=FirebaseDatabase.getInstance().getReference();
        ref=ref.child("messages");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                crushText.setText(""+dataSnapshot.getChildrenCount());
                msgCount=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    msgCount+=ds.getChildrenCount();
                }
                msgText.setText(""+msgCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
