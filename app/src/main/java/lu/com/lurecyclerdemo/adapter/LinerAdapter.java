package lu.com.lurecyclerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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

public class LinerAdapter extends RecyclerView.Adapter<LinerAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private  static int mOrientation;
    public LinerAdapter(Context context,List<String> List,int orientation) {
        this.mContext=context;
        this.mList=List;
        this.mOrientation=orientation;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            if (mOrientation==LinearLayoutManager.VERTICAL){
                textView=(TextView) itemView.findViewById(R.id.text_liner_ver);
            }else{
                textView=(TextView) itemView.findViewById(R.id.text_liner_hor);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if(mOrientation== LinearLayoutManager.VERTICAL){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liner_vertical,parent,false);
        }else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liner_hor,parent,false);
        }
        LinerAdapter.ViewHolder holder=new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s = mList.get(position);
        holder.textView.setText(s);
    }
    public  void addData(int pos){
        mList.add(pos,"insert one");
        notifyItemInserted(pos);
    }
    public void deleteData(int pos){
        mList.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {

            return  mList.size();
        }
    }

