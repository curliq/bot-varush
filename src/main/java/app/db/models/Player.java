package app.db.models;

import app.rest.pojos.PlayerPOJO;

public class Player {

    public final static String TABLE_NAME = "players";

    public enum Fields {

        ID("id"), NAME("name"), TITLE_ID("title_id"), PICTURE_ID("picture_id");

        public String val;

        private Fields(String val) {
            this.val = val;
        }
    }

    private PlayerPOJO.Data playerPojo = new PlayerPOJO().new Data();

    public PlayerPOJO.Data getPlayerPojo() {
        return playerPojo;
    }
}