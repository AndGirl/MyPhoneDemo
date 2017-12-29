package com.ybj.phonehelp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.AppInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨阳洋 on 2017/12/26.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private Context mContext;
    private List<AppInfo.DatasBean> mDatas;
    private LayoutInflater mLayoutInflater;
    private String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public RecommendAdapter(Context context, List<AppInfo.DatasBean> datasBeen) {
        this.mContext = context;
        this.mDatas = datasBeen;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo.DatasBean bean = mDatas.get(position);

        Picasso.with(mContext)
                .load(baseImgUrl + bean.getIcon())
                .into(holder.mImgIcon);

        holder.mTextTitle.setText(bean.getDisplayName());
        holder.mTextSize.setText(bean.getApkSize() / 1024 / 1024 + "MB");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView mImgIcon;
        @BindView(R.id.text_title)
        TextView mTextTitle;
        @BindView(R.id.text_size)
        TextView mTextSize;
        @BindView(R.id.btn_dl)
        Button mBtnDl;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
