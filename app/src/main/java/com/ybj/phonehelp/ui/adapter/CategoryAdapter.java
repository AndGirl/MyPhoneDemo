package com.ybj.phonehelp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.CategoryBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.imageloader.ImageLoader;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */

public class CategoryAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {

    private boolean isShowPosition;
    private boolean isShowCategoryName;
    private boolean isShowBrief;

    public CategoryAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean item) {
        helper.setText(R.id.text_name,item.getName());
        ImageLoader.load(Constant.BASE_IMAGEURL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
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
