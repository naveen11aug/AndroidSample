package com.example.naveen.googleplayservicesandvolleysample.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.naveen.googleplayservicesandvolleysample.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView addressTextView;

    public ListViewHolder(View view) {
        super(view);
        nameTextView = (TextView)view.findViewById(R.id.placeName);
        addressTextView = (TextView)view.findViewById(R.id.placeAddr);
    }
}