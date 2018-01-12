package com.ybj.phonehelp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.RecommendBean;
import com.ybj.phonehelp.imageloader.ImageLoader;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;
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

    private boolean isFirstApps = true;
    private boolean isFirstGames = true;

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
            return new IconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));
        } else if (viewType == TYPE_APPS) {
            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false));
        } else if (viewType == TYPE_GAMES) {
            return new GamesViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false));
        }


        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<RecommendBean.BannersBean> banners = datasBeen.getBanners();
            ArrayList<String> urls = new ArrayList<>(banners.size());
            for (RecommendBean.BannersBean bean : banners) {
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

        } else if (position == 1) {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;
            iconViewHolder.mLayoutHotApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "热门应用", Toast.LENGTH_SHORT).show();
                }
            });
            iconViewHolder.mLayoutHotGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "热门游戏", Toast.LENGTH_SHORT).show();
                }
            });
            iconViewHolder.mLayoutHotSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "热门主题", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (position == 2) {
            AppViewHolder appViewHolder = (AppViewHolder) holder;
            initRecyclerView(appViewHolder.mRecyclerView,isFirstApps,position);
            appViewHolder.mText.setText("热门应用");
            AppAdapter appAdapter = new AppAdapter(R.layout.template_appinfo, datasBeen.getRecommendApps());
            appAdapter.setShowBrief(true);
            appAdapter.setShowCategoryName(false);
            appAdapter.setShowPosition(false);
            appViewHolder.mRecyclerView.setAdapter(appAdapter);

            //点击事件
            appAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (position == 3) {
            GamesViewHolder gamesViewHolder = (GamesViewHolder) holder;
            initRecyclerView(gamesViewHolder.mRecyclerView,isFirstGames,position);
            gamesViewHolder.mText.setText("热门游戏");
            GamesAdapter gamesAdapter = new GamesAdapter(R.layout.template_appinfo, datasBeen.getRecommendGames());
            gamesAdapter.setShowBrief(true);
            gamesAdapter.setShowCategoryName(false);
            gamesAdapter.setShowPosition(false);
            gamesViewHolder.mRecyclerView.setAdapter(gamesAdapter);

            //点击事件
            gamesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
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
        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        public IconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 热门应用的ViewHolder
     */
    public class AppViewHolder extends ViewHolder {
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 游戏的ViewHolder
     */
    public class GamesViewHolder extends ViewHolder {
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public GamesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void initRecyclerView(RecyclerView recyclerView, boolean isFlag, int position){

        recyclerView.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义),在这里会导致过渡重绘
        if(isFlag) {
            recyclerView.addItemDecoration(new DividerItemDecoration(context
                    , DividerItemDecoration.VERTICAL_LIST));
            if(position == 2) {
                isFirstApps = false;
            }else if(position == 3) {
                isFirstGames = false;
            }

        }
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    class ImgLoader implements BannerLayout.ImageLoader {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.getInstance().load(path, imageView);
        }
    }

}
