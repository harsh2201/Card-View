package com.example.minesh.cardview;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private facultyAdapter adapter;
    List<faculty> facultyList;
    private Workbook wb;
    private Sheet sh;
    private FileOutputStream fos;
    private FileInputStream fis;
    private Keyboard.Row row;
    private Cell cell;
    private InputStream inpstr;
    private String xx = "";
    private SlidrInterface slidr;

    private InputStream inputStream;
    private DatabaseReference root, ref;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = FirebaseDatabase.getInstance().getReference();

        startActivity(new Intent(MainActivity.this, CharusatVAnalytics.class));
        finish();

        slidr = Slidr.attach(this);
        slidr.unlock();

        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("second.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();
            for (int i = 0; i < row; i++) {
                for (int c = 0; c < col; c++) {
                    Cell z = s.getCell(c, i);
                    xx = xx + z.getContents() + "  ";

                }
                xx = xx + "\n";
            }
        } catch (Exception e) {

        }

        facultyList.add(new faculty(
                1,
                "Hoooooo",
                "HoooHooo",
                "Blah Blah Blah",
                R.drawable.mrugendrarahevar

        ));

        facultyList.add(new faculty(
                1,
                xx + "",
                "Professor in Networking",
                "xzy@gmail.com",
                R.drawable.riteshpatel

        ));

        facultyList.add(
                new faculty(
                        1,
                        "Ritesh Patel",
                        "Professor in Networking",
                        "xzy@gmail.com",
                        R.drawable.riteshpatel));
        facultyList.add(
                new faculty(
                        1,
                        "Martin Parmar",
                        "Professor in JAVA",
                        "abc@gmail.com",
                        R.drawable.martinparmar));

        facultyList.add(
                new faculty(
                        1,
                        "Mrugendra Rahevar",
                        "Professor Head JAVA",
                        "asdfg@gmail.com",
                        R.drawable.mrugendrarahevar));
        facultyList.add(
                new faculty(
                        1,
                        "Ritesh Patel",
                        "Professor in Networking",
                        "xzy@gmail.com",
                        R.drawable.riteshpatel));
        facultyList.add(
                new faculty(
                        1,
                        "Martin Parmar",
                        "Professor in JAVA",
                        "abc@gmail.com",
                        R.drawable.martinparmar));

        facultyList.add(
                new faculty(
                        1,
                        "Mrugendra Rahevar",
                        "Professor Head JAVA",
                        "asdfg@gmail.com",
                        R.drawable.mrugendrarahevar));
        facultyList.add(
                new faculty(
                        1,
                        "Ritesh Patel",
                        "Professor in Networking",
                        "xzy@gmail.com",
                        R.drawable.riteshpatel));
        facultyList.add(
                new faculty(
                        1,
                        "Martin Parmar",
                        "Professor in JAVA",
                        "abc@gmail.com",
                        R.drawable.martinparmar));

        facultyList.add(
                new faculty(
                        1,
                        "Mrugendra Rahevar",
                        "Professor Head JAVA",
                        "asdfg@gmail.com",
                        R.drawable.mrugendrarahevar));

        int y,ii;
//        for(int i=1;i<=4;i++){
//            y=14+i;
//            ii=5-i;
//            if(y>=17){
//                AddData(""+ii,"DCS",""+y+"dcs");
//                AddData(""+ii,"DIT",""+y+"dit");
//                AddData(""+ii,"DCE",""+y+"dce");
//            }
//            AddData(""+ii,"BPH",""+y+"bph");
//            AddData(""+ii,"BCA",""+y+"bca");
//            AddData(""+ii,"BSC",""+y+"bsc");
//            AddData(""+ii,"CL",""+y+"cl");
//            AddData(""+ii,"EC",""+y+"ec");
//            AddData(""+ii,"ME",""+y+"me");
//            AddData(""+ii,"IT",""+y+"it");
//            AddData(""+ii,"EE",""+y+"ee");
//            AddData(""+ii,"CE",""+y+"ce");
//
//        }
//        AddData("2","EE","17ee");
//        AddData("3","EE","16ee");

        Context con = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
        adapter = new facultyAdapter(this, facultyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();

        adapter.setOnItemClickListener(new facultyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (facultyList.get(position).gName().equals("Hoooooo")) {
                    startActivity(new Intent(MainActivity.this, History_Main_app.class));
                } else {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("data", facultyList.get(position).gName());
                    startActivity(i);
                    Toast.makeText(MainActivity.this, "Hello " + facultyList.get(position).gName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AddData(String Year, String Branch, String br) {
        try {
            AssetManager am = getAssets();
            inputStream = am.open("" + br + ".csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String csvLine;
            ref = root.child("Users").child(Year).child(Branch);
            while ((csvLine = reader.readLine()) != null) {
                data = csvLine.split(",");
                ref.child(data[0]).child("Name").setValue(data[1]);
            }
//            Toast.makeText(this, "Data of 5 is:" + data[0] + "\nSize=" + data.length, Toast.LENGTH_SHORT).show();
//            for (int j = 0; j < 2; j++) {
//                Toast.makeText(this, "" + data[j], Toast.LENGTH_SHORT).show();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Context con = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
        adapter = new facultyAdapter(this, facultyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }
}

