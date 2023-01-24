package com.alperez.sentencerandom.listviewcheckbox;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alperez.sentencerandom.R;
import com.alperez.sentencerandom.RandomSentence;
import com.alperez.sentencerandom.listview.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stanislav.perchenko on 18.01.2023 at 21:30.
 */
public class BaseAdapterListViewActivityCheckbox extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_list_view);
        listView = (ListView) findViewById(R.id.list1);

        Bundle bundle = getIntent().getExtras();
        int nItems = bundle.getInt("number");

        List<ItemModel> data = createListData(nItems);

        MyListAdapterCheckbox adapter = new MyListAdapterCheckbox(this, data);
        listView.setAdapter(adapter);
    }

    private List<ItemModel> createListData (int n){
        List<ItemModel> items = new ArrayList<>(n);
        RandomSentence rs = new RandomSentence(60, 150);
        Random r = new Random();

        for (int i = 0; i < n; i++){
            ItemModel item = new ItemModel(rs.createText(), r.nextBoolean());
            items.add(item);
        }
        return items;
    }
}
