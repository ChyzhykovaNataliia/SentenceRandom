package com.alperez.sentencerandom;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by stanislav.perchenko on 11.01.2023 at 22:36.
 */
public class ItemsActivity extends AppCompatActivity {
    private LinearLayout llRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_items);
        llRoot = (LinearLayout) findViewById(R.id.llMain);

        Bundle bundle = getIntent().getExtras();
        int number_text = bundle.getInt("number");

        final RandomSentence rs = new RandomSentence(60, 100);
        for (int i=0; i < number_text; i++) {
            String s = rs.createText();
            String str = Integer.toString(i);
            addItem (str, s);
        }
    }

    private void addItem (String index, String s){
        LayoutInflater inflater = LayoutInflater.from(this);
        if(llRoot.getChildCount() > 0) {
            inflater.inflate(R.layout.list_divider, llRoot, true);
        }
        LinearLayout item = (LinearLayout) inflater.inflate(R.layout.view_add, llRoot, false);
        ((TextView) item.findViewById(R.id.text_view)).setText(s);
        ((TextView)item.findViewById(R.id.text_index)).setText(index);

        llRoot.addView(item);
    }


}
