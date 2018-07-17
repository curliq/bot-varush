package app.tasks;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TasksManager {

    private final int RUN_SCRIPTS_HOUR = 8; //10 AM

    public static void init() {
        new TasksManager().scheduleTask();
    }

    private void scheduleTask() {
        LocalDateTime now = LocalDateTime.now();
        int startInHours;
        if (now.getHour() < RUN_SCRIPTS_HOUR) {
            // right now is before 10 AM
            startInHours = RUN_SCRIPTS_HOUR - now.getHour();
        } else {
            // right now is after 10 AM
            startInHours = 24 - (now.getHour() - RUN_SCRIPTS_HOUR);
        }
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> new Thread(() -> {
            getTopPlayers();
            getMatches();
        }).start(), startInHours, 24, TimeUnit.HOURS);
    }

    private void getTopPlayers() {
        //todo
    }

    private void getMatches() {
        Scripts.getNewPlayersFromMatches(LocalDateTime.now().minusDays(1));
    }

}
