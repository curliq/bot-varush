package app.rest.twitch.pojos;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreamPOJO {

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("game_id")
        @Expose
        private String gameId;
        @SerializedName("community_ids")
        @Expose
        private ArrayList<Object> communityIds = null;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("viewer_count")
        @Expose
        private Integer viewerCount;
        @SerializedName("started_at")
        @Expose
        private String startedAt;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("thumbnail_url")
        @Expose
        private String thumbnailUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public ArrayList<Object> getCommunityIds() {
            return communityIds;
        }

        public void setCommunityIds(ArrayList<Object> communityIds) {
            this.communityIds = communityIds;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getViewerCount() {
            return viewerCount;
        }

        public void setViewerCount(Integer viewerCount) {
            this.viewerCount = viewerCount;
        }

        public String getStartedAt() {
            return startedAt;
        }

        public void setStartedAt(String startedAt) {
            this.startedAt = startedAt;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

    }

    public class Pagination {

        @SerializedName("cursor")
        @Expose
        private String cursor;

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

    }

}