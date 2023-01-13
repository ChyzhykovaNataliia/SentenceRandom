package com.alperez.sentencerandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText edtText;
    private Button btnScroll;
    private Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RandomSentence rs = new RandomSentence(60, 100);
        for (int i=0; i<20; i++) {
            Log.d("AAAAA", rs.createText());
        }
        edtText = (EditText) findViewById(R.id.edit_text);
        btnScroll = (Button) findViewById(R.id.button_scroll);
        btnList = (Button) findViewById(R.id.button_list);

        btnScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtText.getText().length() > 0) {
                    try {
                        Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("number", Integer.parseInt(edtText.getText().toString()));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Enter number, not a letter", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Enter number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}