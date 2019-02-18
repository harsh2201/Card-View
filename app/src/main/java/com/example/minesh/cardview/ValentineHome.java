package com.example.minesh.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ValentineHome extends AppCompatActivity {

    private ArrayAdapter<String> branchAdapt, yearAdapt;
    private Spinner branchSpinner, yearSpinner;
    private RecyclerView recycler;
    private ListAdapter adapter;
    private List<StudentClass> studentList, templist;
    private String year, branch;
    private DatabaseReference rootref, ref;
    private StudentClass studentClass;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valentine_home);

        rootref = FirebaseDatabase.getInstance().getReference();
        ref = rootref.child("Users");

        studentList = new ArrayList<>();
        templist = new ArrayList<>();

        branchSpinner = findViewById(R.id.branchSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);

        branchAdapt = new ArrayAdapter<>(ValentineHome.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Branch));
        yearAdapt = new ArrayAdapter<>(ValentineHome.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Year));
        branchAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapt);
        branchSpinner.setAdapter(branchAdapt);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(ValentineHome.this));
        adapter = new ListAdapter(this, studentList);  //templist
//        studentList.add(new StudentClass("Harsh","17ce039","CE","2"));
//        studentList.add(new StudentClass("Harsh","17ce039","CE","2"));
//        recycler.setAdapter(adapter);

//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                studentList.clear();
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    studentClass = new StudentClass(""+ds.child("Name").getValue(String.class), ds.getKey(), "CE", "1");
//                    studentList.add(studentClass);
//                }
//                recycler.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        recycler.setAdapter(adapter);


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = String.valueOf(parent.getItemAtPosition(position));
                if (!(branch.equals("-:SELECT BRANCH:-") || year.equals("-:SELECT YEAR:-"))) {
                    ref = rootref.child("Users").child(year).child(branch);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            studentList.clear();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                studentClass = new StudentClass(ds.child("Name").getValue(String.class), ds.getKey(), "" + branch, "" + year);
                                studentList.add(studentClass);
                            }
                            Context con = recycler.getContext();
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                            recycler.setLayoutAnimation(controller);
                            recycler.scheduleLayoutAnimation();
                            recycler.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

//                    templist.clear();
//                    for(int i=0;i<studentList.size();i++){
//                        if(studentList.get(i).gBranch().equals(branch)&&studentList.get(i).gYear().equals(year)){
//                            templist.add(studentList.get(i));
//                            Context con = recycler.getContext();
//                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
//                            recycler.setLayoutAnimation(controller);
//                            recycler.scheduleLayoutAnimation();
//                            recycler.setAdapter(adapter);
//                        }
//                    }
                } else {
                    studentList.clear();
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = String.valueOf(parent.getItemAtPosition(position));
                if (!(branch.equals("-:SELECT BRANCH:-") || year.equals("-:SELECT YEAR:-"))) {
                    ref = rootref.child("Users").child(year).child(branch);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            studentList.clear();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                studentClass = new StudentClass("" + ds.child("Name").getValue(String.class), ds.getKey(), "" + branch, "" + year);
                                studentList.add(studentClass);
                            }
                            Context con = recycler.getContext();
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                            recycler.setLayoutAnimation(controller);
                            recycler.scheduleLayoutAnimation();
                            recycler.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

//                    templist.clear();
//                    for(int i=0;i<studentList.size();i++){
//                        if(studentList.get(i).gYear().equals(year)&&studentList.get(i).gBranch().equals(branch)){
//                            templist.add(studentList.get(i));
//                            Context con = recycler.getContext();
//                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
//                            recycler.setLayoutAnimation(controller);
//                            recycler.scheduleLayoutAnimation();
//                            recycler.setAdapter(adapter);
//                        }
//                    }
                } else {
                    studentList.clear();
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        studentList.add(new StudentClass(" ", " ", " ", " "));
        recycler.setAdapter(adapter);
        onClickList();
    }

    private void onClickList() {
        final ListAdapter adaptNew = (ListAdapter) recycler.getAdapter();
        adaptNew.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ValentineHome.this, "" + studentList.get(position).gid(), Toast.LENGTH_SHORT).show();
                //studentList=adaptNew.currentList();
//                intent.putExtra("Faculty_retrieve_class", studentList.get(position).gName());
//                intent.putExtra("image",(int)facultyList.get(position).gImage());
//                startActivity(intent);
            }
        });
    }

}
