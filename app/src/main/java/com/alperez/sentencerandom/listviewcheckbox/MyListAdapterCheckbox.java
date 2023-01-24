package com.alperez.sentencerandom.listviewcheckbox;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
        if(row == null){
            row = LayoutInflater.from(context).inflate(R.layout.layout_list_item_checkbox, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Integer myPosition = (Integer) buttonView.getTag();
                    ItemModel model = getItem(myPosition);
                    ItemModel newModel = new ItemModel(model.text, isChecked);
                    setDataItem(myPosition, newModel);
                }
            });
        } else {
            holder = (ViewHolder) row.getTag();
        }

        //---  Data binding  ---
        ItemModel model = getItem(position);
        holder.checkBox.setTag(position);
        holder.tvIndex.setText(String.valueOf(position + 1));
        holder.tvData.setText(model.text);
        holder.checkBox.setChecked(model.isActive);

        if (model.isActive) {
            holder.dr.setTint(Color.MAGENTA);
        } else {
            holder.dr.setTint(0);
        }
        holder.iv.setImageDrawable(holder.dr);

        return row;
    }

    private class ViewHolder{
        final TextView tvIndex;
        final CheckBox checkBox;
        final TextView tvData;
        final ImageView iv;
        final Drawable dr;

        public ViewHolder(View rootView) {
            tvIndex = rootView.findViewById(R.id.text_index);
            checkBox = rootView.findViewById(R.id.checkbox);
            tvData = rootView.findViewById(R.id.text_data);
            iv = rootView.findViewById(R.id.id_star);
            dr = iv.getDrawable().mutate();
            dr.setTintMode(PorterDuff.Mode.SRC_ATOP);
        }
    }
}
