package com.test.recyclerviewenhanced;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * ====================== Adapter ========================
 *
 * @author SGamble
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    LayoutInflater inflater;
    List<RowModel> modelList;

    public Adapter(Context context, List<RowModel> list) {
        inflater = LayoutInflater.from(context);
        modelList = new ArrayList<>(list);
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        holder.bindData(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mainText, subText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            subText = (TextView) itemView.findViewById(R.id.subText);
        }

        public void bindData(RowModel rowModel) {
            mainText.setText(rowModel.getMainText());
            subText.setText(rowModel.getSubText());
        }
    }
}
