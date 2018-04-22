package app.utils.db;

import java.util.ArrayList;

public class Team {

	public final static String TABLE_NAME = "teams";

    public enum Fields {

        ID("id"), MEMBERS_IDS("members_ids"), NAME("name"), LEAGUE("league"), DIVISION("division"), POINTS("points"),
        AVATAR_ID("avatar_id"), WINS("wins"), LOSSES("losses"), PLACEMENTS_LEFT("placements_left"),
        TOP_LEAGUE("top_league"), TOP_DIVISION("top_division"), TOP_POINTS("top_points");

        public String val;

        private Fields(String val) {
            this.val = val;
        }
    }

    private String id;
    private ArrayList<String> membersIds;
    private String name;
    private Integer league;
    private Integer division;
    private Integer points;
    private String avatarId;
    private Integer wins;
    private Integer losses;
    private Integer placementsLeft;
    private Integer topLeague;
    private Integer topDivision;
    private Integer topPoints;
    
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
	 * @return the membersIds
	 */
	public ArrayList<String> getMembersIds() {
		return membersIds;
	}
	/**
	 * @param membersIds the membersIds to set
	 */
	public void setMembersIds(ArrayList<String> membersIds) {
		this.membersIds = membersIds;
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
	 * @return the league
	 */
	public Integer getLeague() {
		return league;
	}
	/**
	 * @param league the league to set
	 */
	public void setLeague(Integer league) {
		this.league = league;
	}
	/**
	 * @return the division
	 */
	public Integer getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(Integer division) {
		this.division = division;
	}
	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	/**
	 * @return the avatarId
	 */
	public String getAvatarId() {
		return avatarId;
	}
	/**
	 * @param avatarId the avatarId to set
	 */
	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}
	/**
	 * @return the wins
	 */
	public Integer getWins() {
		return wins;
	}
	/**
	 * @param wins the wins to set
	 */
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	/**
	 * @return the losses
	 */
	public Integer getLosses() {
		return losses;
	}
	/**
	 * @param losses the losses to set
	 */
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	/**
	 * @return the placementsLeft
	 */
	public Integer getPlacementsLeft() {
		return placementsLeft;
	}
	/**
	 * @param placementsLeft the placementsLeft to set
	 */
	public void setPlacementsLeft(Integer placementsLeft) {
		this.placementsLeft = placementsLeft;
	}
	/**
	 * @return the topLeague
	 */
	public Integer getTopLeague() {
		return topLeague;
	}
	/**
	 * @param topLeague the topLeague to set
	 */
	public void setTopLeague(Integer topLeague) {
		this.topLeague = topLeague;
	}
	/**
	 * @return the topDivision
	 */
	public Integer getTopDivision() {
		return topDivision;
	}
	/**
	 * @param topDivision the topDivision to set
	 */
	public void setTopDivision(Integer topDivision) {
		this.topDivision = topDivision;
	}
	/**
	 * @return the topPoints
	 */
	public Integer getTopPoints() {
		return topPoints;
	}
	/**
	 * @param topPoints the topPoints to set
	 */
	public void setTopPoints(Integer topPoints) {
		this.topPoints = topPoints;
	}

}