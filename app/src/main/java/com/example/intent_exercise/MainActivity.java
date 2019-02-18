package com.example.intent_exercise;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgOrigin, imgReceive;
    TextView txtPoint;
    int REQUEST_CODE_IMAGE = 123;
    String nameImageOrigin = "";
    int total = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgOrigin = (ImageView) findViewById(R.id.imageView1);
        imgReceive = (ImageView) findViewById(R.id.imageView2);
        txtPoint = (TextView) findViewById(R.id.textViewPoint);

        txtPoint.setText(total + "");

        //khai bao mang
        String[] arrayBird = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(arrayBird));


        imgReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class), REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            String nameImageReceive = data.getStringExtra("namePictures");
            int idPicturesReceive = getResources().getIdentifier(nameImageReceive, "drawable", getPackageName());
            imgReceive.setImageResource(idPicturesReceive);
            //so sanh theo ten minh
            if (nameImageOrigin.equals(nameImageReceive)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                //cong diem
                total += 10;
                //neu dung doi hinh goc
                new CountDownTimer(200, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        //xáo trộn mảng
                        Collections.shuffle(arrayName);
                        nameImageOrigin = arrayName.get(4);

                        //gep thanh 1 id hinh
                        int idPictures = getResources().getIdentifier(arrayName.get(4), "drawable", getPackageName());

                        imgOrigin.setImageResource(idPictures);
                    }
                }.start();
            } else {
                Toast.makeText(this, "Incorrect!!!", Toast.LENGTH_SHORT).show();
                total -= 10;
            }
            txtPoint.setText(total+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuReload) {
            //xáo trộn mảng
            Collections.shuffle(arrayName);
            nameImageOrigin = arrayName.get(4);

            //gep thanh 1 id hinh
            int idPictures = getResources().getIdentifier(arrayName.get(4), "drawable", getPackageName());

            imgOrigin.setImageResource(idPictures);

        }
        return super.onOptionsItemSelected(item);
    }
}
