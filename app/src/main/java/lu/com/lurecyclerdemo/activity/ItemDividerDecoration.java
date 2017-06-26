package lu.com.lurecyclerdemo.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class ItemDividerDecoration extends RecyclerView.ItemDecoration {
    private  int mOrientation;
    private  int[] ATTRS=new int[]{android.R.attr.listDivider};
    private  int HOR=LinearLayoutManager.HORIZONTAL;
    private int  VER=LinearLayoutManager.VERTICAL;
    private Drawable mDivider;

    public ItemDividerDecoration(Context context,int mOrientation) {
        this.mOrientation = mOrientation;
        setOrientation(mOrientation);
        //获取画图资源
        TypedArray typedArray =context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    private void setOrientation(int orientation) {
        if(orientation!= LinearLayoutManager.VERTICAL && orientation!=LinearLayoutManager.HORIZONTAL){
            throw  new IllegalArgumentException("invalid orientation");
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
       if(mOrientation==HOR){
           drawHor(c,parent);
       }else {
           draVer(c,parent);
       }
    }

    private void draVer(Canvas c, RecyclerView parent) {
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            //获取childView
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams parms =(RecyclerView.LayoutParams) childView.getLayoutParams();
            int top=childView.getBottom()+parms.bottomMargin;
            int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top, right, bottom);

            mDivider.draw(c);
        }
    }

    private void drawHor(Canvas c, RecyclerView parent) {
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            //获取childView
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams parms =(RecyclerView.LayoutParams) childView.getLayoutParams();
            int left=childView.getRight()+parms.rightMargin;
            int right=left+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top, right, bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation==HOR){
            outRect.set(0,0,100,0);
        }else{
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }
    }
}
