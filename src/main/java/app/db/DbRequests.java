package app.db;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import app.db.models.Player;
import app.db.models.Team;
import app.rest.pojos.PlayerPOJO;
import app.utils.DbUtils;
import app.utils.GenericUtils;
import app.utils.mapping.MappingUtils;

public class DbRequests {

    /**
     * Save a player in the database
     * Here we create the sql request dynamically by creating a JSONObject with all the player's data and mapping the
     * json keys to the database's column names. This is to avoid hardcoding thousands of values.
     */
    public static void savePlayer(PlayerPOJO.Attributes attrs, PlayerPOJO.Data data) {
        // make a JSONObject of all the player's Stats, the keys will be the values in @SerializedName in PlayerPOJO
        JSONObject json = new JSONObject(new Gson().toJson(attrs.getStats()));

        // remove the picture and title because these are not the names in our database
        json.remove("picture");
        json.remove("title");

        // add the picture and title with the keys being the same name as in our database
        json.put(Player.Fields.TITLE_ID.val, attrs.getStats().getTitleId());
        json.put(Player.Fields.PICTURE_ID.val, attrs.getStats().getPictureID());

        // add the player's name and id, with the keys being the same name as in our database
        json.put(Player.Fields.NAME.val, attrs.getName());
        json.put(Player.Fields.ID.val, data.getId());

        GenericUtils.log("json obj");
        GenericUtils.log(json.toString());

        // create an iterator with all the keys in our json
        Iterator<?> keys = json.keys();

        // prepare two StringBuilders, for the keys and for the values to use in the SQL query
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder updateFields = new StringBuilder();

        // loop through every key in our json
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String val = String.valueOf(json.get(key));

            // check if the value is an Integer, this also checks for null values
            if (val != null) {

                // prepend a "c" so the key becomes a string and matches the column names in our database
                // but check if it's a number first, because some fields aren't, like the id and the name
                if (Character.isDigit(key.charAt(0)))
                    key = "c" + key;

                // add the key to our field (i.e. keys) string, this will look like (id, name, title_id, ...)"
                fields.append(key).append(",");
                updateFields.append(key).append("=excluded.").append(key).append(",");

                // add the key to our values string, this will look like ('123', 'Curlicue', '423421087', ...)
                val = val.replace("\'", "\'\'");
                values.append("'").append(val).append("'").append(",");
            }
        }
        // remove last comma from the strings
        fields.setLength(fields.length() - 1);
        values.setLength(values.length() - 1);
        updateFields.setLength(updateFields.length() - 1);

        GenericUtils.log("lists");
        GenericUtils.log(fields.toString());
        GenericUtils.log(values.toString());


        DbUtils.makeRequest(String.format(
                "INSERT INTO %s (%s) VALUES (%s) ON CONFLICT (%s) DO UPDATE SET %s;",
                Player.TABLE_NAME, fields.toString(), values.toString(),
                Player.Fields.ID.val, updateFields.toString()));

        GenericUtils.log("BD: saved player: " + attrs.getName());
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
            if (resultSet == null || !resultSet.isBeforeFirst())
                return null;
        } catch (SQLException e1) {
            return null;
        }

        try {
            resultSet.next();
            team.setId(resultSet.getString(Team.Fields.ID.val));
            // convert Array to String[], and then convert to ArrayList<String>
            team.setMembersIds(new ArrayList<>(
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

    /**
     * Get a list of players from the database.
     * To create the Player object, we assign the fields to it through reflection, checking if a field exists in
     * player.getAttributes() first, if not then in player.getData(), if not then in player.getAttributes.getStats()
     *
     * @param request the raw sql request
     * @param fields  the array of fields we're requesting, so we know what to use when building our Player object,
     *                has to have exactly the same fields that the sql query returns
     */
    public static ArrayList<Player> getPlayers(String request, String[] fields) {
        ArrayList<Player> playerArray = new ArrayList<>();
        ResultSet resultSet = DbUtils.makeRequest(request);
        try {
            if (resultSet == null || !resultSet.isBeforeFirst())
                return playerArray;
        } catch (SQLException e1) {
            return playerArray;
        }

        try {
            while (resultSet.next()) {
                Player player = new Player();

                // loop through all the fields and set it to a player object through reflection
                for (String fieldName : fields) {
                    String fieldValue = resultSet.getString(fieldName).trim();

                    try {
                        // add the fields in the Player.Attributes, i.e. name
                        Field field = player.getPlayerPojo().getAttributes().getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        field.set(player.getPlayerPojo().getAttributes(), fieldValue);
                    } catch (NoSuchFieldException e) {
                        GenericUtils.log("didnt set field " + fieldName + ":" + fieldValue + " for attributes");

                        // add the fields in the Player.Data, i.e. id
                        try {
                            Field field = player.getPlayerPojo().getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            field.set(player.getPlayerPojo(), fieldValue);
                        } catch (NoSuchFieldException e1) {
                            GenericUtils.log("didnt set field " + fieldName + ":" + fieldValue + " for data");

                            // add the fields in the Player.Stats
                            try {
                                Field field = player.getPlayerPojo().getAttributes().getStats().getClass()
                                        .getDeclaredField(fieldName);
                                field.setAccessible(true);
                                field.set(
                                        player.getPlayerPojo().getAttributes().getStats(),
                                        Integer.valueOf(fieldValue));
                            } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e2) {
                                e2.printStackTrace();
                                GenericUtils.log("error setting field in PlayerPOJO.Stats or getting the field");
                            }
                        } catch (IllegalAccessException | NullPointerException e1) {
                            e1.printStackTrace();
                            GenericUtils.log("error setting field in PlayerPOJO.Data");
                        }
                    } catch (IllegalAccessException | NullPointerException e) {
                        e.printStackTrace();
                        GenericUtils.log("error setting field in PlayerPOJO.Attributes");
                    }
                }
                playerArray.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerArray;
    }

    /**
     * Get the players ordered by most games played with given champion
     *
     * @param champion the champion
     */
    public static ArrayList<Player> getChampionRank(String champion) {
        int limit = 15;
        String championWins = MappingUtils.getDatabaseField("CharacterWins", champion);
        String championLosses = MappingUtils.getDatabaseField("CharacterLosses", champion);
        String[] fieldsArray = {Player.Fields.ID.val, Player.Fields.NAME.val, championWins, championLosses};

        return getPlayers(String.format("SELECT %1$s, %2$s, %3$s, %4$s FROM %5$s WHERE %3$s IS NOT NULL ORDER BY %3$s" +
                        " DESC LIMIT %6$s;",
                Player.Fields.ID.val, Player.Fields.NAME.val, championWins, championLosses, Player.TABLE_NAME, limit),
                fieldsArray);
    }

}