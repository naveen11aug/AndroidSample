package com.example.naveen.googleplayservicesandvolleysample.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naveen.googleplayservicesandvolleysample.Activities.ListActivity;
import com.example.naveen.googleplayservicesandvolleysample.Entities.BasePlace;
import com.example.naveen.googleplayservicesandvolleysample.R;
import com.example.naveen.googleplayservicesandvolleysample.ViewHolders.ListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GenericRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList places;
    private LayoutInflater mLayoutInflater = null;
    private int layout;

    public GenericRecyclerAdapter(ListActivity listActivity, ArrayList places) {
        this.places = places;
        this.mLayoutInflater = (LayoutInflater) listActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = getLayout();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = mLayoutInflater.inflate(layout, null);
        RecyclerView.ViewHolder viewHolder = getViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        BasePlace place = getItems().get(position);
        ListViewHolder mHolder = (ListViewHolder)holder;
        mHolder.nameTextView.setText(place.getName().toUpperCase());
        mHolder.addressTextView.setText(place.getVicinity());
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return (null != places ? places.size() : 0);
    }


    public List<BasePlace> getItems() {
        return places;
    }

    private RecyclerView.ViewHolder getViewHolder(View view)
    {
        return new ListViewHolder(view);
    }

    private int getLayout()
    {
        return R.layout.list_item_card_row;
    }

}
