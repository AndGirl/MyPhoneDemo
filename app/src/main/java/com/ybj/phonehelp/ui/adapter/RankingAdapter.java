package com.ybj.phonehelp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.RankingBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.imageloader.ImageLoader;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */

public class RankingAdapter extends BaseQuickAdapter<RankingBean.DatasBean, BaseViewHolder> {

    private boolean isShowPosition;
    private boolean isShowCategoryName;
    private boolean isShowBrief;


    public RankingAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankingBean.DatasBean item) {
        ImageLoader.load(Constant.BASE_IMAGEURL + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_brief, item.getBriefShow());

        TextView txtViewPosition = helper.getView(R.id.txt_position);
        txtViewPosition.setVisibility(isShowPosition() ? View.VISIBLE : View.GONE);
        txtViewPosition.setText(item.getPosition() + 1 + ". ");

        TextView txtViewCategory = helper.getView(R.id.txt_category);
        txtViewCategory.setVisibility(isShowCategoryName() ? View.VISIBLE : View.GONE);
        txtViewCategory.setText(item.getLevel1CategoryName());

        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        txtViewBrief.setVisibility(isShowBrief() ? View.VISIBLE : View.GONE);
        txtViewBrief.setText(item.getBriefShow());
    }

    public boolean isShowPosition() {
        return isShowPosition;
    }

    public void setShowPosition(boolean showPosition) {
        isShowPosition = showPosition;
    }

    public boolean isShowCategoryName() {
        return isShowCategoryName;
    }

    public void setShowCategoryName(boolean showCategoryName) {
        isShowCategoryName = showCategoryName;
    }

    public boolean isShowBrief() {
        return isShowBrief;
    }

    public void setShowBrief(boolean showBrief) {
        isShowBrief = showBrief;
    }

}
