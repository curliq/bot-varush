package app.utils;

public class TeamCachedPOJO {

    private int division, league, points;

    public TeamCachedPOJO(int division, int league, int points) {
        setDivision(division);
        setLeague(league);
        setPoints(points);
    }

    /**
     * @return the division
     */
    public int getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(int division) {
        this.division = division;
    }

    /**
     * @return the league
     */
    public int getLeague() {
        return league;
    }
    
    /**
     * @param league the league to set
     */
    public void setLeague(int league) {
        this.league = league;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
}