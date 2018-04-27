package app.db.models;

public class Player {

    public final static String TABLE_NAME = "players";

    public enum Fields {

        ID("id"), NAME("name"), TITLE_ID("title_id"), PICTURE_ID("picture_id");

        public String val;

        private Fields(String val) {
            this.val = val;
        }
    }

    private String id;
    private String name;
    private String titleId;
    private String pictureId;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the titleId
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    /**
     * @return the pictureId
     */
    public String getPictureId() {
        return pictureId;
    }

    /**
     * @param pictureId the pictureId to set
     */
    public void getPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

}