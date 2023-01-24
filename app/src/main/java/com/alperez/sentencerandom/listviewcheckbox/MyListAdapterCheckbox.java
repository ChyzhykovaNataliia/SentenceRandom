package com.alperez.sentencerandom.listviewcheckbox;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alperez.sentencerandom.R;
import com.alperez.sentencerandom.listview.ItemModel;

import java.util.List;

/**
 * Created by stanislav.perchenko on 18.01.2023 at 21:31.
 */
public class MyListAdapterCheckbox extends BaseAdapter {
    public static final String TAG = "MY_ADAPTER";

    private final Context context;
    private final List<ItemModel> data;

    public MyListAdapterCheckbox(Context context, List<ItemModel> data) {
        super();
        this.context = context;
        if(data == null){
            throw new IllegalArgumentException("Data is null");
        }
        this.data = data;
    }

    public void setDataItem(int position, ItemModel item) {
        data.set(position, item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ItemModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        //---  Create Row view  ---
        if(row == null) {
            Log.e(TAG, "getView() NEW for position "+position);
            row = LayoutInflater.from(context).inflate(R.layout.layout_list_item_checkbox, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            Log.d(TAG, "getView() for position "+position);
            holder = (ViewHolder) row.getTag();
        }

        //---  Data binding  ---
        ItemModel model = getItem(position);
        holder.bindData(model, position);

        return row;
    }

    private class ViewHolder{
        final TextView tvIndex;
        final CheckBox checkBox;
        final TextView tvData;
        final ImageView iv;
        final Drawable dr;

        private int position;

        private boolean ignoreNextCallback;

        public ViewHolder(View rootView) {
            tvIndex = rootView.findViewById(R.id.text_index);
            checkBox = rootView.findViewById(R.id.checkbox);
            tvData = rootView.findViewById(R.id.text_data);
            iv = rootView.findViewById(R.id.id_star);
            dr = iv.getDrawable().mutate();
            dr.setTintMode(PorterDuff.Mode.SRC_ATOP);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (ignoreNextCallback) {
                        ignoreNextCallback = false;
                        Log.e(TAG, String.format("onCheckChanged() position=%s, isChecked=%b -> IGNORE", position, isChecked));
                        return;
                    }
                    Log.e(TAG, String.format("onCheckChanged() position=%s, isChecked=%b", position, isChecked));
                    ItemModel model = getItem(position);
                    ItemModel newModel = new ItemModel(model.text, isChecked);
                    setDataItem(position, newModel);
                }
            });

        }

        public void bindData(ItemModel model, int position) {
            this.position = position;

            tvIndex.setText(String.valueOf(position + 1));
            tvData.setText(model.text);
            if (checkBox.isChecked() != model.isActive) {
                ignoreNextCallback = true;
                checkBox.setChecked(model.isActive);
            }

            if (model.isActive) {
                dr.setTint(Color.MAGENTA);
            } else {
                dr.setTint(0);
            }
            iv.setImageDrawable(dr);
        }
    }
}
