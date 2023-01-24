package com.alperez.sentencerandom.listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alperez.sentencerandom.R;
import com.alperez.sentencerandom.RandomSentence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stanislav.perchenko on 15.01.2023 at 2:25.
 */
public class BaseAdapterListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_list_view);
        listView = findViewById(R.id.list1);

        Bundle bundle = getIntent().getExtras();
        int nItems = bundle.getInt("number");

        List<ItemModel> data = createListData(nItems);

        MyListAdapter adapter = new MyListAdapter(this, data);

        listView.setAdapter(adapter);
    }

    private List<ItemModel> createListData(int n) {
        List<ItemModel> items = new ArrayList<>(n);
        RandomSentence rs = new RandomSentence(60, 100);
        Random rnd = new Random();

        for (int i=0; i<n; i++) {
            ItemModel item = new ItemModel(rs.createText(), rnd.nextBoolean());
            items.add(item);
        }

        return items;
    }
}
