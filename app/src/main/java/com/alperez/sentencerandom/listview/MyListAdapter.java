package com.alperez.sentencerandom.listview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alperez.sentencerandom.R;

import java.util.List;

/**
 * Created by stanislav.perchenko on 15.01.2023 at 2:57.
 */
public class MyListAdapter extends BaseAdapter {
    private final Context context;
    private final List<ItemModel> data;

    private static final String TAG = "ADAPTER";

    public MyListAdapter(Context context, List<ItemModel> data) {
        super();
        this.context = context;
        if (data == null) throw new IllegalArgumentException("Data is null");
        this.data = data;
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

        ItemModel model = getItem(position);

        if(row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
            Log.d(TAG, String.format("Item %d: create new row", position));
        } else {
            holder = (ViewHolder) row.getTag();
            Log.d(TAG, String.format("Item %d: use existing row", position));
        }
        holder.tvIndex.setText(String.valueOf(position + 1));
        holder.tvData.setText(model.text);


        if (model.isActive) {
            holder.dr.setTint(Color.MAGENTA);
        } else {
            holder.dr.setTint(0);
        }
        holder.iv.setImageDrawable(holder.dr);

        return row;
    }

    private class ViewHolder {
        final TextView tvIndex;
        final TextView tvData;
        final ImageView iv;
        final Drawable dr;

        public ViewHolder(View rootView) {
            tvIndex = (TextView) rootView.findViewById(R.id.text_index);
            tvData = (TextView) rootView.findViewById(R.id.text_data);
            iv = (ImageView) rootView.findViewById(R.id.id_star);
            dr = iv.getDrawable().mutate();
            dr.setTintMode(PorterDuff.Mode.SRC_ATOP);
        }
    }
}
