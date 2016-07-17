package com.apps.type_a.cashlost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Type_A on 09.07.2016.
 */
public class PurchaseListAdapter extends RecyclerView.Adapter<PurchaseListAdapter.ViewHolder> {

    ArrayList<PurchaseItem> items;
    Context context;

    public PurchaseListAdapter(ArrayList<PurchaseItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_purchase, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String date = items.get(position).getDate().getDay()
                + "." +
                items.get(position).getDate().getMonth()
                + "." +
                items.get(position).getDate().getYear();
        String cost = String.valueOf(items.get(position).getCost());
        String place = "(" + items.get(position).getPlace() + ")";
        holder.purchaseName.setText(items.get(position).getName());
        holder.purchaseCost.setText(cost + " " + context.getString(R.string.rub));
        holder.purchaseDate.setText(date);
        holder.purchasePlace.setText(place);
        switch (items.get(position).getPurchaseTypeID()) {
            case 0: {
                holder.coloredSquare.setBackgroundColor(context.getResources().getColor(R.color.electronics));
                break;
            }
            case 1: {
                holder.coloredSquare.setBackgroundColor(context.getResources().getColor(R.color.food));
                break;
            }
            case 2: {
                holder.coloredSquare.setBackgroundColor(context.getResources().getColor(R.color.leisure));
                break;
            }
            case 3: {
                holder.coloredSquare.setBackgroundColor(context.getResources().getColor(R.color.travel));
                break;
            }
            case 4: {
                holder.coloredSquare.setBackgroundColor(context.getResources().getColor(R.color.other));
                break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView purchasePlace, purchaseDate, purchaseCost, purchaseName, coloredSquare;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            purchasePlace = (TextView) itemView.findViewById(R.id.purchasePlace);
            purchaseDate = (TextView) itemView.findViewById(R.id.purchaseDate);
            purchaseCost = (TextView) itemView.findViewById(R.id.purchaseCost);
            purchaseName = (TextView) itemView.findViewById(R.id.purchaseName);
            coloredSquare = (TextView) itemView.findViewById(R.id.coloredSquare);
        }
    }
}
