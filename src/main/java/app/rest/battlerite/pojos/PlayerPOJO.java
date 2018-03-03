package app.rest.battlerite.pojos;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerPOJO {

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Assets {

        @SerializedName("data")
        @Expose
        private List<Object> data = null;

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

    }

    public class Attributes {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("patchVersion")
        @Expose
        private String patchVersion;
        @SerializedName("shardId")
        @Expose
        private String shardId;
        @SerializedName("titleId")
        @Expose
        private String titleId;
        @SerializedName("stats")
        @Expose
        private Stats stats;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatchVersion() {
            return patchVersion;
        }

        public void setPatchVersion(String patchVersion) {
            this.patchVersion = patchVersion;
        }

        public String getShardId() {
            return shardId;
        }

        public void setShardId(String shardId) {
            this.shardId = shardId;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public Stats getStats() {
            return stats;
        }

    }

    public class Stats {
        @SerializedName("picture")
        @Expose
        long pictureID;
        @SerializedName("title")
        @Expose
        long titleID;

        public void settitleID(long titleID) {
            this.titleID = titleID;
        }

        public long gettitleID() {
            return titleID;
        }

        public void setPictureID(long pictureID) {
            this.pictureID = pictureID;
        }

        public long getPictureID() {
            return pictureID;
        }
    }

    public class Data {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("id")
        @Expose
        private Long id;
        @SerializedName("attributes")
        @Expose
        private Attributes attributes;
        @SerializedName("relationships")
        @Expose
        private Relationships relationships;
        @SerializedName("links")
        @Expose
        private Links links;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public Relationships getRelationships() {
            return relationships;
        }

        public void setRelationships(Relationships relationships) {
            this.relationships = relationships;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

    }

    public class Links {

        @SerializedName("schema")
        @Expose
        private String schema;
        @SerializedName("self")
        @Expose
        private String self;

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

    }

    public class Relationships {

        @SerializedName("assets")
        @Expose
        private Assets assets;

        public Assets getAssets() {
            return assets;
        }

        public void setAssets(Assets assets) {
            this.assets = assets;
        }

    }
}