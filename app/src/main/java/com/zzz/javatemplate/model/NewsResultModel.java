package com.zzz.javatemplate.model;

import java.util.List;

public class NewsResultModel {
    private String reason;

    public String getReason() {
        return reason;
    }

    public Result getResult() {
        return result;
    }

    private Result result;

    public class Result {
        String stat;

        public String getStat() {
            return stat;
        }

        public List<News> getData() {
            return data;
        }

        List<News> data;
    }

    public class News {
        String uniquekey;
        String title;
        String date;
        String category;
        String author_name;
        String url;
        String thumbnail_pic_s;
        String thumbnail_pic_s02;

        public String getUniquekey() {
            return uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }
    }

}
    

