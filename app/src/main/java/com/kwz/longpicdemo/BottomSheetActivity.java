package com.kwz.longpicdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kwz.bottomdialoglib.BottomDialog;

public class BottomSheetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

    }


    public void show(View view) {
        BottomDialog bottomDialog = new BottomDialog(this);
        bottomDialog.showAddDialog(true, true, new BottomDialog.OnAddListener() {
            @Override
            public void onTakingPictures() {
                Toast.makeText(BottomSheetActivity.this, "1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLocalPhotoAlbum() {
                Toast.makeText(BottomSheetActivity.this, "2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShortVideo() {
                Toast.makeText(BottomSheetActivity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
