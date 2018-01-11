package com.ybj.phonehelp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 杨阳洋 on 2018/1/11.
 * 首页的数据
 * 包含banner gird recyclerView的数据
 */

public class RecommendBean implements Serializable{

    private List<BannersBean> banners;
    private List<RecommendAppsBean> recommendApps;
    private List<RecommendGamesBean> recommendGames;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<RecommendAppsBean> getRecommendApps() {
        return recommendApps;
    }

    public void setRecommendApps(List<RecommendAppsBean> recommendApps) {
        this.recommendApps = recommendApps;
    }

    public List<RecommendGamesBean> getRecommendGames() {
        return recommendGames;
    }

    public void setRecommendGames(List<RecommendGamesBean> recommendGames) {
        this.recommendGames = recommendGames;
    }

    public static class BannersBean {
        /**
         * action : subject
         * id : 169136
         * thumbnail : http://t4.market.mi-img.com/thumbnail/jpeg/l750/AppStore/0ff69546de24355c8a484aafc27222e230f41f76f
         */

        private String action;
        private String id;
        private String thumbnail;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    public static class RecommendAppsBean {
        /**
         * adType : 0
         * addTime : 0
         * ads : 0
         * apkSize : 1377295870
         * appTags : [{"link":"sametag/278","tagId":278,"tagName":"角色"},{"link":"sametag/350","tagId":350,"tagName":"3D RPG"}]
         * appendSize : 0
         * briefShow : 腾讯正版奇迹MU手游，开放自由交易
         * briefUseIntro : false
         * developerId : 0
         * diffFileSize : 0
         * displayName : 奇迹MU：觉醒
         * favorite : false
         * fitness : 0
         * grantCode : 0
         * hasSameDevApp : false
         * icon : AppStore/090844b1503fb679c0d506f1195f982c34940dd5d
         * id : 509263
         * isFavorite : false
         * level1CategoryId : 15
         * level1CategoryName : 游戏
         * level2CategoryId : 19
         * packageName : com.tencent.tmgp.tmsk.qj2
         * position : 0
         * publisherName : 天马时空
         * rId : 0
         * ratingScore : 2.5
         * ratingTotalCount : 0
         * relateAppHasMore : false
         * releaseKeyHash : 58a81ef27cab6b38096e57a518be9c96
         * samDevAppHasMore : false
         * screenshot : AppStore/02a8755096ce4467e295030001183a262165a4ef7,AppStore/0ea8755096ce4467e295010001683a25216504ef7,AppStore/01c7ae5ea4b1d41042c5858108feab63dc4c06388,AppStore/0dc7ae5ea4b1d41042c58281084eab63dc4c26388,AppStore/07a875109ec64f675f950b000148362e21642f1a5
         * source :
         * suitableType : 2
         * updateTime : 1515051571684
         * versionCode : 110
         * versionName : 1.1.0
         * videoId : 0
         * hdIcon : {"main":"AppStore/0387d3417b39b44133230d977250990d99625e165"}
         */

        private int adType;
        private int addTime;
        private int ads;
        private int apkSize;
        private int appendSize;
        private String briefShow;
        private boolean briefUseIntro;
        private int developerId;
        private int diffFileSize;
        private String displayName;
        private boolean favorite;
        private int fitness;
        private int grantCode;
        private boolean hasSameDevApp;
        private String icon;
        private int id;
        private boolean isFavorite;
        private int level1CategoryId;
        private String level1CategoryName;
        private int level2CategoryId;
        private String packageName;
        private int position;
        private String publisherName;
        private int rId;
        private double ratingScore;
        private int ratingTotalCount;
        private boolean relateAppHasMore;
        private String releaseKeyHash;
        private boolean samDevAppHasMore;
        private String screenshot;
        private String source;
        private int suitableType;
        private long updateTime;
        private int versionCode;
        private String versionName;
        private int videoId;
        private HdIconBean hdIcon;
        private List<AppTagsBean> appTags;

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getAds() {
            return ads;
        }

        public void setAds(int ads) {
            this.ads = ads;
        }

        public int getApkSize() {
            return apkSize;
        }

        public void setApkSize(int apkSize) {
            this.apkSize = apkSize;
        }

        public int getAppendSize() {
            return appendSize;
        }

        public void setAppendSize(int appendSize) {
            this.appendSize = appendSize;
        }

        public String getBriefShow() {
            return briefShow;
        }

        public void setBriefShow(String briefShow) {
            this.briefShow = briefShow;
        }

        public boolean isBriefUseIntro() {
            return briefUseIntro;
        }

        public void setBriefUseIntro(boolean briefUseIntro) {
            this.briefUseIntro = briefUseIntro;
        }

        public int getDeveloperId() {
            return developerId;
        }

        public void setDeveloperId(int developerId) {
            this.developerId = developerId;
        }

        public int getDiffFileSize() {
            return diffFileSize;
        }

        public void setDiffFileSize(int diffFileSize) {
            this.diffFileSize = diffFileSize;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public int getFitness() {
            return fitness;
        }

        public void setFitness(int fitness) {
            this.fitness = fitness;
        }

        public int getGrantCode() {
            return grantCode;
        }

        public void setGrantCode(int grantCode) {
            this.grantCode = grantCode;
        }

        public boolean isHasSameDevApp() {
            return hasSameDevApp;
        }

        public void setHasSameDevApp(boolean hasSameDevApp) {
            this.hasSameDevApp = hasSameDevApp;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public int getLevel1CategoryId() {
            return level1CategoryId;
        }

        public void setLevel1CategoryId(int level1CategoryId) {
            this.level1CategoryId = level1CategoryId;
        }

        public String getLevel1CategoryName() {
            return level1CategoryName;
        }

        public void setLevel1CategoryName(String level1CategoryName) {
            this.level1CategoryName = level1CategoryName;
        }

        public int getLevel2CategoryId() {
            return level2CategoryId;
        }

        public void setLevel2CategoryId(int level2CategoryId) {
            this.level2CategoryId = level2CategoryId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public int getRId() {
            return rId;
        }

        public void setRId(int rId) {
            this.rId = rId;
        }

        public double getRatingScore() {
            return ratingScore;
        }

        public void setRatingScore(double ratingScore) {
            this.ratingScore = ratingScore;
        }

        public int getRatingTotalCount() {
            return ratingTotalCount;
        }

        public void setRatingTotalCount(int ratingTotalCount) {
            this.ratingTotalCount = ratingTotalCount;
        }

        public boolean isRelateAppHasMore() {
            return relateAppHasMore;
        }

        public void setRelateAppHasMore(boolean relateAppHasMore) {
            this.relateAppHasMore = relateAppHasMore;
        }

        public String getReleaseKeyHash() {
            return releaseKeyHash;
        }

        public void setReleaseKeyHash(String releaseKeyHash) {
            this.releaseKeyHash = releaseKeyHash;
        }

        public boolean isSamDevAppHasMore() {
            return samDevAppHasMore;
        }

        public void setSamDevAppHasMore(boolean samDevAppHasMore) {
            this.samDevAppHasMore = samDevAppHasMore;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getSuitableType() {
            return suitableType;
        }

        public void setSuitableType(int suitableType) {
            this.suitableType = suitableType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public HdIconBean getHdIcon() {
            return hdIcon;
        }

        public void setHdIcon(HdIconBean hdIcon) {
            this.hdIcon = hdIcon;
        }

        public List<AppTagsBean> getAppTags() {
            return appTags;
        }

        public void setAppTags(List<AppTagsBean> appTags) {
            this.appTags = appTags;
        }

        public static class HdIconBean {
            /**
             * main : AppStore/0387d3417b39b44133230d977250990d99625e165
             */

            private String main;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }
        }

        public static class AppTagsBean {
            /**
             * link : sametag/278
             * tagId : 278
             * tagName : 角色
             */

            private String link;
            private int tagId;
            private String tagName;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }

    public static class RecommendGamesBean {
        /**
         * adType : 0
         * addTime : 0
         * ads : 0
         * apkSize : 164467303
         * appTags : [{"link":"sametag/260","tagId":260,"tagName":"卡牌"},{"link":"sametag/278","tagId":278,"tagName":"角色"}]
         * appendSize : 0
         * briefShow : 青葱岁月触手可及，童年我来了
         * briefUseIntro : false
         * developerId : 0
         * diffFileSize : 0
         * displayName : 超能继承者-动漫全明星
         * favorite : false
         * fitness : 0
         * grantCode : 0
         * hasSameDevApp : false
         * hdIcon : {"main":"AppStore/02b50f535b8444981006d2000f133a08a5a2d367f"}
         * icon : AppStore/08b9b468be4081bb50dd861dc603a468cad4333bc
         * id : 115326
         * isFavorite : false
         * level1CategoryId : 15
         * level1CategoryName : 游戏
         * level2CategoryId : 19
         * packageName : com.npc.s80.mi
         * position : 0
         * publisherName : 成都简乐互动远景科技有限公司
         * rId : 0
         * ratingScore : 3.5
         * ratingTotalCount : 0
         * relateAppHasMore : false
         * releaseKeyHash : 4abca3abb7515554eb68aaa867931d3a
         * samDevAppHasMore : false
         * screenshot : AppStore/0274f4ede0493521dc4da8bb3ef4718d00f43948b,AppStore/00b2144446824b316670e9401ad35892d9b42ddda,AppStore/0e74635331db64bf7295a94c800782d97c3fc9ac2,AppStore/0bb21a444b864431c270e9401da35d96d4260d2d0,AppStore/0abb4459c299849d2097bc5efe2c370d36ffe499a
         * source :
         * suitableType : 2
         * updateTime : 1514873536695
         * versionCode : 7
         * versionName : 1.0.7
         * videoId : 217
         */

        private int adType;
        private int addTime;
        private int ads;
        private int apkSize;
        private int appendSize;
        private String briefShow;
        private boolean briefUseIntro;
        private int developerId;
        private int diffFileSize;
        private String displayName;
        private boolean favorite;
        private int fitness;
        private int grantCode;
        private boolean hasSameDevApp;
        private HdIconBeanX hdIcon;
        private String icon;
        private int id;
        private boolean isFavorite;
        private int level1CategoryId;
        private String level1CategoryName;
        private int level2CategoryId;
        private String packageName;
        private int position;
        private String publisherName;
        private int rId;
        private double ratingScore;
        private int ratingTotalCount;
        private boolean relateAppHasMore;
        private String releaseKeyHash;
        private boolean samDevAppHasMore;
        private String screenshot;
        private String source;
        private int suitableType;
        private long updateTime;
        private int versionCode;
        private String versionName;
        private int videoId;
        private List<AppTagsBeanX> appTags;

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getAds() {
            return ads;
        }

        public void setAds(int ads) {
            this.ads = ads;
        }

        public int getApkSize() {
            return apkSize;
        }

        public void setApkSize(int apkSize) {
            this.apkSize = apkSize;
        }

        public int getAppendSize() {
            return appendSize;
        }

        public void setAppendSize(int appendSize) {
            this.appendSize = appendSize;
        }

        public String getBriefShow() {
            return briefShow;
        }

        public void setBriefShow(String briefShow) {
            this.briefShow = briefShow;
        }

        public boolean isBriefUseIntro() {
            return briefUseIntro;
        }

        public void setBriefUseIntro(boolean briefUseIntro) {
            this.briefUseIntro = briefUseIntro;
        }

        public int getDeveloperId() {
            return developerId;
        }

        public void setDeveloperId(int developerId) {
            this.developerId = developerId;
        }

        public int getDiffFileSize() {
            return diffFileSize;
        }

        public void setDiffFileSize(int diffFileSize) {
            this.diffFileSize = diffFileSize;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public int getFitness() {
            return fitness;
        }

        public void setFitness(int fitness) {
            this.fitness = fitness;
        }

        public int getGrantCode() {
            return grantCode;
        }

        public void setGrantCode(int grantCode) {
            this.grantCode = grantCode;
        }

        public boolean isHasSameDevApp() {
            return hasSameDevApp;
        }

        public void setHasSameDevApp(boolean hasSameDevApp) {
            this.hasSameDevApp = hasSameDevApp;
        }

        public HdIconBeanX getHdIcon() {
            return hdIcon;
        }

        public void setHdIcon(HdIconBeanX hdIcon) {
            this.hdIcon = hdIcon;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public int getLevel1CategoryId() {
            return level1CategoryId;
        }

        public void setLevel1CategoryId(int level1CategoryId) {
            this.level1CategoryId = level1CategoryId;
        }

        public String getLevel1CategoryName() {
            return level1CategoryName;
        }

        public void setLevel1CategoryName(String level1CategoryName) {
            this.level1CategoryName = level1CategoryName;
        }

        public int getLevel2CategoryId() {
            return level2CategoryId;
        }

        public void setLevel2CategoryId(int level2CategoryId) {
            this.level2CategoryId = level2CategoryId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public int getRId() {
            return rId;
        }

        public void setRId(int rId) {
            this.rId = rId;
        }

        public double getRatingScore() {
            return ratingScore;
        }

        public void setRatingScore(double ratingScore) {
            this.ratingScore = ratingScore;
        }

        public int getRatingTotalCount() {
            return ratingTotalCount;
        }

        public void setRatingTotalCount(int ratingTotalCount) {
            this.ratingTotalCount = ratingTotalCount;
        }

        public boolean isRelateAppHasMore() {
            return relateAppHasMore;
        }

        public void setRelateAppHasMore(boolean relateAppHasMore) {
            this.relateAppHasMore = relateAppHasMore;
        }

        public String getReleaseKeyHash() {
            return releaseKeyHash;
        }

        public void setReleaseKeyHash(String releaseKeyHash) {
            this.releaseKeyHash = releaseKeyHash;
        }

        public boolean isSamDevAppHasMore() {
            return samDevAppHasMore;
        }

        public void setSamDevAppHasMore(boolean samDevAppHasMore) {
            this.samDevAppHasMore = samDevAppHasMore;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getSuitableType() {
            return suitableType;
        }

        public void setSuitableType(int suitableType) {
            this.suitableType = suitableType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public List<AppTagsBeanX> getAppTags() {
            return appTags;
        }

        public void setAppTags(List<AppTagsBeanX> appTags) {
            this.appTags = appTags;
        }

        public static class HdIconBeanX {
            /**
             * main : AppStore/02b50f535b8444981006d2000f133a08a5a2d367f
             */

            private String main;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }
        }

        public static class AppTagsBeanX {
            /**
             * link : sametag/260
             * tagId : 260
             * tagName : 卡牌
             */

            private String link;
            private int tagId;
            private String tagName;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }
}
