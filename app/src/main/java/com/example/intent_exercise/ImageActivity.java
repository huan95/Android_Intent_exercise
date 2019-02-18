package com.example.intent_exercise;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = (TableLayout) findViewById(R.id.tableLayoutImage);

        int line = 4;
        int column = 3;

        //tron mang
        Collections.shuffle(MainActivity.arrayName);

        //tao dong va cot
        for (int i = 1;  i <= line; i++){
            TableRow tableRow = new TableRow(this);

            //tao cot -> imageView
            for (int j = 1; j <= column; j++){
                ImageView imageView = new ImageView(this);

                // Converts 14 dip into its equivalent px
                int dip = 100;
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        dip,
                        r.getDisplayMetrics()
                );

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);

                final int position = column * (i - 1) + j - 1;

                int idPictures = getResources().getIdentifier(MainActivity.arrayName.get(position), "drawable", getPackageName());
                imageView.setImageResource(idPictures);
                //add imageView vao table row
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("namePictures", MainActivity.arrayName.get(position));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            //add tablerow vao table
            myTable.addView(tableRow);
        }
    }
}
