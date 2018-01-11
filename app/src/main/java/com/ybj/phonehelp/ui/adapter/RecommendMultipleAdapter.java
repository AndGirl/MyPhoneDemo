package com.ybj.phonehelp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.RecommendBean;
import com.ybj.phonehelp.imageloader.ImageLoader;
import com.ybj.phonehelp.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨阳洋 on 2018/1/11.
 * 首页adapter
 */

public class RecommendMultipleAdapter extends RecyclerView.Adapter<RecommendMultipleAdapter.ViewHolder> {

    //广告条
    public static final int TYPE_BANNER = 1;
    //三个Grid
    private static final int TYPE_ICON = 2;
    //热门应用
    private static final int TYPE_APPS = 3;
    //热门游戏
    private static final int TYPE_GAMES = 4;

    private Context context;
    private LayoutInflater mLayoutInflater;
    private RecommendBean datasBeen;

    public RecommendMultipleAdapter(Context context, RecommendBean datasBeen) {
        this.context = context;
        this.datasBeen = datasBeen;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatasBeen(RecommendBean datasBeen) {
        this.datasBeen = datasBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {

//            return new IconViewHolder(null);

        } else if (viewType == TYPE_APPS) {

//            return new AppViewHolder(null);
        } else if (viewType == TYPE_GAMES) {

//            return new GamesViewHolder(null);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<RecommendBean.BannersBean> banners = datasBeen.getBanners();
            ArrayList<String> urls = new ArrayList<>(banners.size());
            for (RecommendBean.BannersBean bean : banners){
                urls.add(bean.getThumbnail());
            }
            //这里必须实现ImageLoader否则报空
            bannerViewHolder.mBanner.setImageLoader(new ImgLoader());
            bannerViewHolder.mBanner.setViewUrls(urls);
            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }else if(position == 1) {

        }else if(position == 2) {

        }else if(position == 3) {

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_BANNER;
            case 1:
                return TYPE_ICON;
            case 2:
                return TYPE_APPS;
            case 3:
                return TYPE_GAMES;
            default:
                return 0;
        }
    }

    static

    //如果有就存放公共的部分
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 广告条的ViewHolder
     */
    public class BannerViewHolder extends ViewHolder {
        @BindView(R.id.banner)
        BannerLayout mBanner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 三个Grid的ViewHolder
     */
    public class IconViewHolder extends ViewHolder {
        public IconViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 热门应用的ViewHolder
     */
    public class AppViewHolder extends ViewHolder {
        public AppViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 游戏的ViewHolder
     */
    public class GamesViewHolder extends ViewHolder {
        public GamesViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ImgLoader implements BannerLayout.ImageLoader{
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.getInstance().load(path,imageView);
        }
    }

}
