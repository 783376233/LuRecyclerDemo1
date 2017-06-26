package lu.com.lurecyclerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lu.com.lurecyclerdemo.R;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class StagAdapter extends RecyclerView.Adapter<StagAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mList;

    public StagAdapter(Context context, List<String> List) {
        this.mContext = context;
        this.mList = List;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_sta);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stagger, parent, false);
        StagAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s = mList.get(position);
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

