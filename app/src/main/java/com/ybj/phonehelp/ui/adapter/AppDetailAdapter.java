package com.ybj.phonehelp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.AppDetailsBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.imageloader.ImageLoader;

/**
 * Created by 杨阳洋 on 2018/2/28.
 */

public class AppDetailAdapter extends BaseQuickAdapter<AppDetailsBean,BaseViewHolder>{

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    private boolean isShowPosition;
    private boolean isShowCategoryName;
    private boolean isShowBrief;


    public AppDetailAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppDetailsBean item) {
        ImageLoader.load(Constant.BASE_IMAGEURL+item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName());


        TextView txtViewPosition = helper.getView(R.id.txt_position);
        txtViewPosition.setVisibility(isShowPosition() ? View.VISIBLE : View.GONE);
        txtViewPosition.setText(item.getPosition() + 1 + ". ");


        TextView textViewCategoryName = helper.getView(R.id.txt_category);
        textViewCategoryName.setVisibility(isShowCategoryName() ? View.VISIBLE : View.GONE);
        textViewCategoryName.setText(item.getLevel1CategoryName());

        TextView textViewBrief = helper.getView(R.id.txt_brief);
        textViewBrief.setVisibility(isShowBrief() ? View.VISIBLE : View.GONE);
        textViewBrief.setText(item.getBriefShow());


        TextView textViewSize = helper.getView(R.id.txt_apk_size);

        if(textViewSize !=null){
            textViewSize.setText((item.getApkSize() / 1014 / 1024) +"Mb");
        }
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
