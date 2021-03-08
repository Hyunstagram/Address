package com.kang.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    public ListViewAdapter() {}

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView_profile) ;
        TextView NameTextView = (TextView) convertView.findViewById(R.id.textView_name) ;
        TextView NumTextView = (TextView) convertView.findViewById(R.id.textView_num) ;
        TextView EmailTextView = (TextView) convertView.findViewById(R.id.textView_email) ;

        ListViewItem listViewItem = listViewItemList.get(position);

        iconImageView.setImageResource(R.drawable.user);
        NameTextView.setText(listViewItem.getName());
        NumTextView.setText(listViewItem.getNum());
        EmailTextView.setText(listViewItem.getEmail());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(String name, String num, String email) {
        ListViewItem item = new ListViewItem();

        item.setName(name);
        item.setNum(num);
        item.setEmail(email);

        listViewItemList.add(item);
    }

    public void deleteItem(int position) {
        listViewItemList.remove(position);
    }
}