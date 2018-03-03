package app.rest.battlerite.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamStatsPOJO {

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("attributes")
        @Expose
        private Attributes attributes;

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

    }

    public class Attributes {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shardId")
        @Expose
        private String shardId;
        @SerializedName("stats")
        @Expose
        private Stats stats;
        @SerializedName("titleId")
        @Expose
        private String titleId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

    }

    public class Stats {

        @SerializedName("avatar")
        @Expose
        private Integer avatar;
        @SerializedName("division")
        @Expose
        private Integer division;
        @SerializedName("divisionRating")
        @Expose
        private Integer divisionRating;
        @SerializedName("league")
        @Expose
        private Integer league;
        @SerializedName("losses")
        @Expose
        private Integer losses;
        @SerializedName("members")
        @Expose
        private ArrayList<Long> members = null;
        @SerializedName("placementGamesLeft")
        @Expose
        private Integer placementGamesLeft;
        @SerializedName("topDivision")
        @Expose
        private Integer topDivision;
        @SerializedName("topDivisionRating")
        @Expose
        private Integer topDivisionRating;
        @SerializedName("topLeague")
        @Expose
        private Integer topLeague;
        @SerializedName("wins")
        @Expose
        private Integer wins;

        public Integer getAvatar() {
            return avatar;
        }

        public void setAvatar(Integer avatar) {
            this.avatar = avatar;
        }

        public Integer getDivision() {
            return division;
        }

        public void setDivision(Integer division) {
            this.division = division;
        }

        public Integer getDivisionRating() {
            return divisionRating;
        }

        public void setDivisionRating(Integer divisionRating) {
            this.divisionRating = divisionRating;
        }

        public Integer getLeague() {
            return league;
        }

        public void setLeague(Integer league) {
            this.league = league;
        }

        public Integer getLosses() {
            return losses;
        }

        public void setLosses(Integer losses) {
            this.losses = losses;
        }

        public ArrayList<Long> getMembers() {
            return members;
        }

        public void setMembers(ArrayList<Long> members) {
            this.members = members;
        }

        public Integer getPlacementGamesLeft() {
            return placementGamesLeft;
        }

        public void setPlacementGamesLeft(Integer placementGamesLeft) {
            this.placementGamesLeft = placementGamesLeft;
        }

        public Integer getTopDivision() {
            return topDivision;
        }

        public void setTopDivision(Integer topDivision) {
            this.topDivision = topDivision;
        }

        public Integer getTopDivisionRating() {
            return topDivisionRating;
        }

        public void setTopDivisionRating(Integer topDivisionRating) {
            this.topDivisionRating = topDivisionRating;
        }

        public Integer getTopLeague() {
            return topLeague;
        }

        public void setTopLeague(Integer topLeague) {
            this.topLeague = topLeague;
        }

        public Integer getWins() {
            return wins;
        }

        public void setWins(Integer wins) {
            this.wins = wins;
        }

    }
}