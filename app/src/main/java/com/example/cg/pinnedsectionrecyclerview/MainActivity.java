package com.example.cg.pinnedsectionrecyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cg.pinnedsectionrecyclerview.Adapter.PinnedRecyAdapter;
import com.example.cg.pinnedsectionrecyclerview.models.myTestModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //分别定义三种显示格式的值
    private static int GridFlag = 1;
    private static int ListFlag = 2;
    private static int titleFlag = 3;

    private Context mContext;

    private RecyclerView recy_pinned;
    private List<myTestModel> mDatas;
    private PinnedRecyAdapter pAdapter;
    private GridLayoutManager gManager;

    private LinearLayout titleLayout;
    private TextView txt_Title;
    private int lastFirstVisibleItem = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        initData();
        initControls();
    }

    /**
     * 初始化数据
     * 因为这是Grid与list的混排，所以没法将title也就是类别，放到每条记录里，因为Grid类型，没法处理。
     * 所以这里我采用的是单独把类别做为一个条记录存入并且给了一个单独的类别3
     */
    protected void initData()
    {

        mDatas = new ArrayList<>();


        myTestModel mModel1 = new myTestModel();
        mModel1.setTitle("行业版块");
        mModel1.setName("");
        mModel1.setValue1("");
        mModel1.setValue2("");
        mModel1.setType(titleFlag);
        mModel1.setSordid(1);
        mDatas.add(mModel1);

        for(int i=0;i<6;i++)
        {

            myTestModel mModel = new myTestModel();
            mModel.setTitle("行业版块");
            mModel.setName("行业版块" + i);
            mModel.setValue1("+4.23%");
            mModel.setValue2("行业股" + i + " 3.24%");
            mModel.setType(GridFlag);
            mModel.setSordid(1);
            mDatas.add(mModel);
        }

        myTestModel mModel2 = new myTestModel();
        mModel2.setTitle("概念版块");
        mModel2.setName("");
        mModel2.setValue1("");
        mModel2.setValue2("");
        mModel2.setType(titleFlag);
        mModel2.setSordid(2);
        mDatas.add(mModel2);

        for(int i=0;i<6;i++)
        {

            myTestModel mModel = new myTestModel();
            mModel.setTitle("概念版块");
            mModel.setName("概念版块" + i);
            mModel.setValue1("+5.23%");
            mModel.setValue2("概念股" + i + " 5.24%");
            mModel.setType(GridFlag);
            mModel.setSordid(2);
            mDatas.add(mModel);
        }

        myTestModel mModel3 = new myTestModel();
        mModel3.setTitle("泸深A股");
        mModel3.setName("");
        mModel3.setValue1("");
        mModel3.setValue2("");
        mModel3.setType(titleFlag);
        mModel3.setSordid(3);
        mDatas.add(mModel3);

        for(int i=0;i<10;i++)
        {

            myTestModel mModel = new myTestModel();
            mModel.setTitle("泸深A股");
            mModel.setName("泸深A股" + i);
            mModel.setValue1("+1.23%");
            mModel.setValue2("1.24%");
            mModel.setType(ListFlag);
            mModel.setSordid(3);
            mDatas.add(mModel);
        }

        myTestModel mModel4 = new myTestModel();
        mModel4.setTitle("创业版指");
        mModel4.setName("");
        mModel4.setValue1("");
        mModel4.setValue2("");
        mModel4.setType(titleFlag);
        mModel4.setSordid(4);
        mDatas.add(mModel4);

        for(int i=0;i<15;i++)
        {

            myTestModel mModel = new myTestModel();
            mModel.setTitle("创业版指");
            mModel.setName("创业版指" + i);
            mModel.setValue1("+6.23%");
            mModel.setValue2("6.24%");
            mModel.setType(ListFlag);
            mModel.setSordid(4);
            mDatas.add(mModel);
        }

    }


    /**
     * 初始化控件
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initControls() {

        titleLayout = (LinearLayout)findViewById(R.id.title_layout);
        txt_Title = (TextView)findViewById(R.id.txt_Title);

        recy_pinned = (RecyclerView)findViewById(R.id.recy_pinned);

        /**
         * 在这里，我们把LayoutManager设成Grid形式，根据实际情况，这里我设置成三列
         * setSpanSizeLookup方法，个人感觉有点像我们xml布局中的layout_weight,它表示Grid中的每项占几个位置
         * 在这里，根据实际情况，我们可以得知，类别栏和下面的list形式数据全是每条占满的也就是一个占三个的位置
         */
        gManager = new GridLayoutManager(this,3);
        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (position == 0 || position == 7 || position > 13) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        recy_pinned.setLayoutManager(gManager);
        pAdapter = new PinnedRecyAdapter(mDatas,this);
        recy_pinned.setAdapter(pAdapter);

        /**
         * 这里，我们根据控件的滚动情况，来对菜单栏进行处理。
         * 这里可以参看郭霖大神的http://blog.csdn.net/guolin_blog/article/details/9033553
         */
        recy_pinned.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //取得当前屏幕可见数据的第一个值
                int firstVisibleItem = gManager.findFirstVisibleItemPosition();

                //取得当前屏幕可见数据的第一个值的类别值
                int section = mDatas.get(firstVisibleItem).getSordid();

                //取得当前屏幕可见数据的第一个值的类别值在类别顺序中的下一个类别值
                int nextSecPosition = getLastIndex(section);

                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    txt_Title.setText(mDatas.get(firstVisibleItem).getTitle());
                }


                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = recyclerView.getChildAt(0);
                    if (childView != null) {

                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

        pAdapter.setOnItemClickLitener(new PinnedRecyAdapter.OnItemClickLitener() {

            @Override
            public void OnItemClick(View view, int positon, int type) {
                if(type==GridFlag)
                {
                    Toast.makeText(mContext, mDatas.get(positon).getName(), Toast.LENGTH_SHORT).show();
                }else if(type==titleFlag)
                {
                    Toast.makeText(mContext,mDatas.get(positon).getTitle(),Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(mContext,mDatas.get(positon).getName(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }


    /**
     * 根据传入的当前类别值，取出下一个类别的第一条记录的排序号，就是在数据列表中的顺序
     *
     * 这里的值，大家可以根据自己的实际情况去算一下。
     *
     * @param i   当前类别值
     * @return    下一个类别的第一条记录的排序号
     */
    private int getLastIndex(int i)
    {
        switch (i)
        {
            case 1:
                return 5;
            case 2:
                return 12;
            case 3:
                return 25;
            default:
                return 0;
        }
    }
}
