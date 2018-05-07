package app.rest.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MatchPOJO {

    public enum IncludedObjectType {

        Player("player"), Participant("participant"), Roster("roster"), Team("team"), Round("round"), Asset("asset");

        public String val;

        IncludedObjectType(String value) {
            this.val = value;
        }
    }

    @SerializedName("data")
    private ArrayList<Data> data;
    @SerializedName("included")
    private ArrayList<IncludedObject> included;

    public ArrayList<Data> getData() {
        return data;
    }

    public ArrayList<IncludedObject> getIncluded() {
        return included;
    }

    public class Data {

        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private String id;
        @SerializedName("attributes")
        private Attributes attributes;
        @SerializedName("relationships")
        private Relationships relationships;
        @SerializedName("links")
        private Links links;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public class Assets {

            @SerializedName("data")
            private List<Datum> data = null;

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

        }

        public class Attributes {

            @SerializedName("createdAt")
            private String createdAt;
            @SerializedName("duration")
            private Integer duration;
            @SerializedName("gameMode")
            private String gameMode;
            @SerializedName("patchVersion")
            private String patchVersion;
            @SerializedName("shardId")
            private String shardId;
            @SerializedName("stats")
            private Stats stats;
            @SerializedName("tags")
            private Tags tags;
            @SerializedName("titleId")
            private String titleId;

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
                this.duration = duration;
            }

            public String getGameMode() {
                return gameMode;
            }

            public void setGameMode(String gameMode) {
                this.gameMode = gameMode;
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

            public Stats getStats() {
                return stats;
            }

            public void setStats(Stats stats) {
                this.stats = stats;
            }

            public Tags getTags() {
                return tags;
            }

            public void setTags(Tags tags) {
                this.tags = tags;
            }

            public String getTitleId() {
                return titleId;
            }

            public void setTitleId(String titleId) {
                this.titleId = titleId;
            }

        }

        public class Datum {

            @SerializedName("type")
            private String type;
            @SerializedName("id")
            private String id;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

        public class Links {

            @SerializedName("schema")
            private String schema;
            @SerializedName("self")
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
            private Assets assets;
            @SerializedName("rosters")
            private Rosters rosters;
            @SerializedName("rounds")
            private Rounds rounds;
            @SerializedName("spectators")
            private Spectators spectators;

            public Assets getAssets() {
                return assets;
            }

            public void setAssets(Assets assets) {
                this.assets = assets;
            }

            public Rosters getRosters() {
                return rosters;
            }

            public void setRosters(Rosters rosters) {
                this.rosters = rosters;
            }

            public Rounds getRounds() {
                return rounds;
            }

            public void setRounds(Rounds rounds) {
                this.rounds = rounds;
            }

            public Spectators getSpectators() {
                return spectators;
            }

            public void setSpectators(Spectators spectators) {
                this.spectators = spectators;
            }

        }

        public class Rosters {

            @SerializedName("data")
            private List<Datum> data = null;

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

        }

        public class Rounds {

            @SerializedName("data")
            private List<Datum> data = null;

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

        }

        public class Spectators {

            @SerializedName("data")
            private List<Object> data = null;

            public List<Object> getData() {
                return data;
            }

            public void setData(List<Object> data) {
                this.data = data;
            }

        }

        public class Stats {

            @SerializedName("mapID")
            private String mapID;
            @SerializedName("type")
            private String type;

            public String getMapID() {
                return mapID;
            }

            public void setMapID(String mapID) {
                this.mapID = mapID;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

        }

        public class Tags {

            @SerializedName("rankingType")
            private String rankingType;
            @SerializedName("serverType")
            private String serverType;

            public String getRankingType() {
                return rankingType;
            }

            public void setRankingType(String rankingType) {
                this.rankingType = rankingType;
            }

            public String getServerType() {
                return serverType;
            }

            public void setServerType(String serverType) {
                this.serverType = serverType;
            }

        }

    }

    public class IncludedObject {


        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
