package com.example.cwp1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
    Screen3 screen;

    MyListAdapter(Screen3 s3){
        screen = s3;
    }

    @Override
    public int getCount() {
        return 26;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = screen.getLayoutInflater().inflate(R.layout.myrow,null,false);
        }
        if(position < screen.names.length) {
            ImageView im1 = convertView.findViewById(R.id.imv);
            im1.setImageBitmap(screen.pics[position*3]);
            ImageView im2 = convertView.findViewById(R.id.imv2);
            im2.setImageBitmap(screen.pics[(position*3)+1]);
            ImageView im3 = convertView.findViewById(R.id.imv3);
            im3.setImageBitmap(screen.pics[(position*3)+2]);

            TextView tv1 = convertView.findViewById(R.id.tv1);
            tv1.setText(screen.names[(position*3)]);
            TextView tv2 = convertView.findViewById(R.id.tv2);
            tv2.setText(screen.duration[(position*3)]);
            TextView tv3 = convertView.findViewById(R.id.tv3);
            tv3.setText(screen.datetime[(position*3)]);
            TextView tv4 = convertView.findViewById(R.id.tv4);
            tv4.setText(screen.names[(position*3)+1]);
            TextView tv5 = convertView.findViewById(R.id.tv5);
            tv5.setText(screen.duration[(position*3)+1]);
            TextView tv6 = convertView.findViewById(R.id.tv6);
            tv6.setText(screen.datetime[(position*3)+1]);
            TextView tv7 = convertView.findViewById(R.id.tv7);
            tv7.setText(screen.names[(position*3)+2]);
            TextView tv8 = convertView.findViewById(R.id.tv8);
            tv8.setText(screen.duration[(position*3)+2]);
            TextView tv9 = convertView.findViewById(R.id.tv9);
            tv9.setText(screen.datetime[(position*3)+2]);
        }

        return convertView;
    }
}
