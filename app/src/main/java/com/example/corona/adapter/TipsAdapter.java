package com.example.corona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.R;
import com.example.corona.model.Tips;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    public interface OnListItemClickListener {
        void onListItemClick(Tips tips);
    }

    private List<Tips> tips;
    final private OnListItemClickListener mOnListItemClickListener;

    public TipsAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsAdapter.ViewHolder holder, int position) {
        if (tips != null) {
            Tips tipsPosition = tips.get(position);
            holder.tipDescription.setText(tipsPosition.getDescription());
        }
    }

    public void setTips(List<Tips> tips) {
        this.tips = tips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (tips == null) {
            return 0;
        }
        return tips.size();
    }

    public Tips getTipAt(int position) {
        return tips.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tipDescription;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipDescription = itemView.findViewById(R.id.tips_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mOnListItemClickListener.onListItemClick(tips.get(getAdapterPosition()));
        }
    }
}