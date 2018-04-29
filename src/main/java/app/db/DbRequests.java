package app.db;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import app.db.models.Player;
import app.db.models.Team;
import app.rest.pojos.PlayerPOJO;
import app.utils.DbUtils;
import app.utils.GenericUtils;

public class DbRequests {

    /**
     * Save a player in the database
     */
    public static void savePlayer(PlayerPOJO.Attributes attrs, PlayerPOJO.Data data, PlayerPOJO.Stats stats) {
        JSONObject json = new JSONObject(new Gson().toJson(stats));
        json.put(Player.Fields.NAME.val, attrs.getName());
        json.put(Player.Fields.TITLE_ID.val, stats.getTitleId());
        json.put(Player.Fields.PICTURE_ID.val, stats.getPictureID());
        json.put(Player.Fields.ID.val, data.getId());

        Iterator<?> keys = json.keys();
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object val = json.get(key);
            if (val instanceof Integer) {
                fields.append(key);
                values.append('\'').append(val).append('\'').append(',');
            }
        }
        // remove last comma
        values.setLength(values.length() - 1);

        DbUtils.makeRequest(String.format(
                "INSERT INTO %s (%s) VALUES (%s);",
                Player.TABLE_NAME, fields.toString(), values.toString()));

        GenericUtils.log("BD: saved player: " + attrs.getName());
    }

    /**
     * Get a player by name from the database
     */
    public static Player getPlayer(String name) {
        Player player = new Player();

        ResultSet resultSet = DbUtils
                .makeRequest(String.format("SELECT * FROM %s WHERE name='%s';", Player.TABLE_NAME, name));

        try {
            player.setId(resultSet.getString(Player.Fields.ID.val));
            player.setName(resultSet.getString(Player.Fields.NAME.val));
            player.setTitleId(resultSet.getString(Player.Fields.TITLE_ID.val));
            player.getPictureId(resultSet.getString(Player.Fields.PICTURE_ID.val));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return player;
    }

    /**
     * Save a team in the database or update if already exists
     */
    public static void saveTeam(String id, ArrayList<String> membersIds, String name, Integer league, Integer division,
                                Integer points, String avatarId, Integer wins, Integer losses, Integer
                                        placementsLeft, Integer topLeague,
                                Integer topDivision, Integer topPoints) {

        new Thread(() -> {
            DbUtils.makeRequest(String.format(
                    "INSERT INTO %1$s (%2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s, %9$s, %10$s, %11$s, %12$s, "
                            + "%13$s, %14$s) VALUES ('%15$s', '%16$s', '%17$s', '%18$s', '%19$s', '%20$s', '%21$s', " +
                            "'%22$s', '%23$s', '%24$s', '%25$s', "
                            + "'%26$s', '%27$s') ON CONFLICT (%2$s) DO UPDATE SET %3$s = excluded.%3$s, %4$s = " +
                            "excluded.%4$s, "
                            + "%5$s = excluded.%5$s, %6$s = excluded.%6$s, %7$s = excluded.%7$s, %8$s = excluded.%8$s, "
                            + "%9$s = excluded.%9$s, %10$s = excluded.%10$s, %11$s = excluded.%11$s, "
                            + "%12$s = excluded.%12$s, %13$s = excluded.%13$s, %14$s = excluded.%14$s;",
                    Team.TABLE_NAME, Team.Fields.ID.val, Team.Fields.MEMBERS_IDS.val, Team.Fields.NAME.val,
                    Team.Fields.LEAGUE.val, Team.Fields.DIVISION.val, Team.Fields.POINTS.val,
                    Team.Fields.AVATAR_ID.val, Team.Fields.WINS.val, Team.Fields.LOSSES.val,
                    Team.Fields.PLACEMENTS_LEFT.val, Team.Fields.TOP_LEAGUE.val, Team.Fields.TOP_DIVISION.val,
                    Team.Fields.TOP_POINTS.val, id, DbUtils.getArrayForSql(membersIds), name, league, division,
                    points, avatarId, wins, losses, placementsLeft, topLeague, topDivision, topPoints));
            GenericUtils.log(String.format("BD: saved team: %s, %s", id, name));
        }).start();
    }

    /**
     * Get a team by Id from the database
     */
    public static Team getTeam(String id) {
        Team team = new Team();

        ResultSet resultSet = DbUtils
                .makeRequest(String.format("SELECT * FROM %s WHERE id='%s';", Team.TABLE_NAME, id));

        try {
            if (resultSet == null || !resultSet.next())
                return null;
        } catch (SQLException e1) {
            return null;
        }

        try {
            team.setId(resultSet.getString(Team.Fields.ID.val));
            // convert Array to String[], and then convert to ArrayList<String>
            team.setMembersIds(new ArrayList<String>(
                    Arrays.asList((String[]) resultSet.getArray(Team.Fields.MEMBERS_IDS.val).getArray())));
            team.setName(resultSet.getString(Team.Fields.NAME.val));
            team.setLeague(resultSet.getInt(Team.Fields.LEAGUE.val));
            team.setDivision(resultSet.getInt(Team.Fields.DIVISION.val));
            team.setPoints(resultSet.getInt(Team.Fields.POINTS.val));
            team.setAvatarId(resultSet.getString(Team.Fields.AVATAR_ID.val));
            team.setWins(resultSet.getInt(Team.Fields.WINS.val));
            team.setLosses(resultSet.getInt(Team.Fields.LOSSES.val));
            team.setPlacementsLeft(resultSet.getInt(Team.Fields.PLACEMENTS_LEFT.val));
            team.setTopLeague(resultSet.getInt(Team.Fields.TOP_LEAGUE.val));
            team.setTopDivision(resultSet.getInt(Team.Fields.TOP_DIVISION.val));
            team.setTopPoints(resultSet.getInt(Team.Fields.TOP_POINTS.val));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return team;
    }

}