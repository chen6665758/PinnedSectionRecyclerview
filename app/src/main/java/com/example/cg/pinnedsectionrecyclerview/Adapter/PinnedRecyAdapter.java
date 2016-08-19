package com.example.cg.pinnedsectionrecyclerview.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cg.pinnedsectionrecyclerview.R;
import com.example.cg.pinnedsectionrecyclerview.models.myTestModel;

import java.util.List;

/**
* RecyclerView的Adapter
* 作者：cg
* 时间：2016/8/18 0018 上午 10:47
*/
public class PinnedRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<myTestModel> list_data;
    private LayoutInflater inflater;
    private Context context;

    public PinnedRecyAdapter(List<myTestModel> list_data, Context context) {
        this.list_data = list_data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     * 定义点击每项的接口,此处只实现了点击，没有实现长按
     */
    public interface OnItemClickLitener
    {
        void OnItemClick(View view, int positon, int type);
        void OnItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view;
        RecyclerView.ViewHolder mViewHolder;
        if(viewType==1) {

            view = inflater.inflate(R.layout.activity_pinned_griditem, parent, false);
            mViewHolder = new gViewHolder(view);
        }else if(viewType==2)
        {
            view = inflater.inflate(R.layout.activity_pinned_listitem, parent, false);
            mViewHolder = new lViewHolder(view);
        }else
        {
            view = inflater.inflate(R.layout.activity_pinned_titleitem, parent, false);
            mViewHolder = new tViewHolder(view);
        }

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position))
        {
            case 1:
                final gViewHolder gHolder = (gViewHolder)holder;
                gHolder.txt_pGName.setText(list_data.get(position).getName());
                gHolder.txt_pGValue1.setText(list_data.get(position).getValue1());
                gHolder.txt_pGValue2.setText(list_data.get(position).getValue2());

                if(mOnItemClickLitener!=null)
                {
                    gHolder.linear_g.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(context,list_data.get(position).getName(),Toast.LENGTH_SHORT).show();
                            mOnItemClickLitener.OnItemClick(gHolder.itemView, position,1);
                        }
                    });
                }
                break;
            case 2:
                final lViewHolder lHolder = (lViewHolder)holder;
                lHolder.txt_pLName.setText(list_data.get(position).getName());
                lHolder.txt_pLValue1.setText(list_data.get(position).getValue1());
                lHolder.txt_pLValue2.setText(list_data.get(position).getValue2());
                if(mOnItemClickLitener!=null)
                {
                    lHolder.rela_l.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(context,list_data.get(position).getName(),Toast.LENGTH_SHORT).show();
                            mOnItemClickLitener.OnItemClick(lHolder.itemView, position,2);
                        }
                    });
                }
                break;
            case 3:
                final tViewHolder tHolder = (tViewHolder)holder;
                tHolder.txt_pTitle.setText(list_data.get(position).getTitle());
                if(mOnItemClickLitener!=null)
                {
                    tHolder.linear_t.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(context,list_data.get(position).getName(),Toast.LENGTH_SHORT).show();
                            mOnItemClickLitener.OnItemClick(tHolder.itemView, position,3);
                        }
                    });
                }
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {

        return list_data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    /**
     * 类别形式的布局数据
     */
    class tViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linear_t;
        TextView txt_pTitle;

        public tViewHolder(View itemView) {
            super(itemView);
            linear_t = (LinearLayout)itemView.findViewById(R.id.linear_t);
            txt_pTitle = (TextView)itemView.findViewById(R.id.txt_pTitle);
        }
    }

    /**
     * Grid形式的布局数据
     */
    class gViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linear_g;
        TextView txt_pGName;
        TextView txt_pGValue1;
        TextView txt_pGValue2;

        public gViewHolder(View itemView) {
            super(itemView);

            linear_g = (LinearLayout)itemView.findViewById(R.id.linear_g);
            txt_pGName = (TextView)itemView.findViewById(R.id.txt_pGName);
            txt_pGValue1 = (TextView)itemView.findViewById(R.id.txt_pGValue1);
            txt_pGValue2 = (TextView)itemView.findViewById(R.id.txt_pGValue2);
        }
    }

    /**
     * List形式的布局数据
     */
    class lViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rela_l;
        TextView txt_pLName;
        TextView txt_pLValue1;
        TextView txt_pLValue2;

        public lViewHolder(View itemView) {
            super(itemView);

            rela_l = (RelativeLayout)itemView.findViewById(R.id.rela_l);
            txt_pLName = (TextView)itemView.findViewById(R.id.txt_pLName);
            txt_pLValue1 = (TextView)itemView.findViewById(R.id.txt_pLValue1);
            txt_pLValue2 = (TextView)itemView.findViewById(R.id.txt_pLValue2);
        }
    }

}
