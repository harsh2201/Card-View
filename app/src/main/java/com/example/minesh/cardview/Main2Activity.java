package com.example.minesh.cardview;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.Content;
import com.codekidlabs.storagechooser.StorageChooser;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.read.biff.BiffException;

public class Main2Activity extends AppCompatActivity {

    private TextView txt;
    private TextView txtPath;
    private int STORAGE_PERMISSION_CODE = 23;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        String str = i.getStringExtra("data");
        /*txt = findViewById(R.id.txtPath);
        txt.setText(str);*/

        slidr = Slidr.attach(this);
        slidr.unlock();

        txtPath = findViewById(R.id.txtPath);
        try {
            /*AssetManager am=getAssets();
            InputStream is=am.open("second.xls");
            Workbook wb =Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row =s.getRows();
            int col=s.getColumns();
            for(int j=0;j<row;j++)
            {
                for (int c=0;c<col;c++)
                {
                    Cell z=s.getCell(c,j);
                    xx=xx+z.getContents()+"  ";

                }
                xx=xx+"\n";
            }*/
            //txtPath.setText(xx);
        } catch (Exception e) {

        }

        Fade fade = new Fade();
        fade.setDuration(400);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setEnterTransition(fade);

    }

    public void openChooser(View view) {
        if (!isStorageReadable()) {
            requestStoragePermission();
        } else {
            chooser();
        }
    }

    //We are calling this method to check the permission status
    private boolean isStorageReadable() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getApplicationContext(), "Permission Required to Access Storage", Toast.LENGTH_SHORT).show();
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooser();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the Storage permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void chooser() {
        Content c = new Content();
        c.setInternalStorageText("Internal Storage");
        c.setCancelLabel("Cancel");
        c.setSelectLabel("Choose");
        c.setOverviewHeading("Choose Drive");

        StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(Main2Activity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .setType(StorageChooser.FILE_PICKER)   //.setType(StorageChooser.FILE_PICKER) to Pick a Path of a file
                .showHidden(false) //.showHidden(true)
                .allowCustomPath(true)
                .withPredefinedPath(Environment.getExternalStorageDirectory().toString())
                .setDialogTitle("Choose Directory")
                .withContent(c)
                .build();

        chooser.show();

        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                //txtPath.setText(path);
                try {
                    File f=new File(path);
                    Workbook wb =Workbook.getWorkbook(f);
                    Sheet s=wb.getSheet(0);
                    int row =s.getRows();
                    int col=s.getColumns();
                    for(int i=0;i<row;i++)
                    {
                        for (int c=0;c<col;c++)
                        {
                            Cell z=s.getCell(c,i);
                            xx=xx+z.getContents()+"  ";

                        }
                        xx=xx+"\n";
                    }
                    txtPath.setText(xx);
                } catch (Exception e) {

                }
                InputStream ExcelFileToRead = null;
                try {
                    ExcelFileToRead = new FileInputStream(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                XSSFWorkbook wb = null;
                try {
                    wb = new XSSFWorkbook(ExcelFileToRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                XSSFWorkbook test = new XSSFWorkbook();

                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow row;
                XSSFCell cell;

                Iterator rows = sheet.rowIterator();

                while (rows.hasNext()) {
                    row = (XSSFRow) rows.next();
                    Iterator cells = row.cellIterator();
                    String toast = "";
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();

                        if (true) {
                            toast += cell.getStringCellValue() + " ";
//                        } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//                            toast += cell.getNumericCellValue() + " ";
//                        } else {
                            //U Can Handel Boolean, Formula, Errors
                        }
                    }
                    Toast.makeText(Main2Activity.this, "" + toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


