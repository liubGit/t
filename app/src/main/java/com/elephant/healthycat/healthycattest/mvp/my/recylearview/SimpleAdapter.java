package com.elephant.healthycat.healthycattest.mvp.my.recylearview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.fragment.TestFragment;
import com.elephant.healthycat.healthycattest.mvp.my.model.MyModel;
import com.elephant.healthycat.healthycattest.util.DensityUtil;

import java.util.List;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> {
    private List<MyModel> list;
    private int largeCardHeight, smallCardHeight;

    public SimpleAdapter(List<MyModel> list, Context context) {
        this.list = list;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        MyModel myModel = list.get(position);
        holder.nameTv.setText(myModel.getName());
        holder.ageTv.setText(myModel.getNumber());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
//            holder.rootView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
//        }
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(List<MyModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_my_recylerview_item, parent, false);

        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    public void insert(MyModel person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                nameTv = (TextView) itemView
                        .findViewById(R.id.recycleview_name_tv);
                ageTv = (TextView) itemView
                        .findViewById(R.id.recycleview_number_tv);
//                rootView = itemView
//                        .findViewById(R.id.card_view);
            }

        }
    }

    public MyModel getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}