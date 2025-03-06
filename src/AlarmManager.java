import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class AlarmManager {
    private List<Alarm> alarmList = new ArrayList<Alarm>();
    private PriorityQueue<Alarm> alarmQueue = new PriorityQueue<>();
    private static final String alarmSoundPath = "src/resources/alarmsound.wav";
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    AlarmManager () {
        scheduleNextAlarm();
    }

    public void addAlarm (String name, LocalTime time) {
        Alarm newAlarm = new Alarm(name, time);
        alarmList.add(newAlarm);
        alarmQueue.offer(newAlarm);
        scheduleNextAlarm();
    }

    public void removeOldAlarms () {
        alarmList.removeIf(Alarm::isInactive);
        alarmQueue.removeIf(Alarm::isInactive);
        System.out.println("Removed old alarms!");
    }

    public void listAllAlarms () {
        System.out.println("\nAll Alarms");
        System.out.println("---------------");
        for (Alarm alarm : alarmList) {
            System.out.println(alarm.toString());
        }
    }

    public void listAlarmsByTime () {
        System.out.println("\nAll Alarms by time");
        System.out.println("---------------------");

        PriorityQueue<Alarm> sortedAlarms = new PriorityQueue<>(alarmQueue);

        while (!sortedAlarms.isEmpty()) {
            System.out.println(sortedAlarms.poll());
        }
    }

    private void scheduleNextAlarm() {
        if (alarmQueue.isEmpty()) return;

        // Find the next alarm
        Alarm alarm = alarmQueue.peek();

        long delay = Duration.between(LocalTime.now(), alarm.getTime()).toMillis();

        if (delay < 0) delay = 0;

        scheduler.schedule(() -> triggerAlarm(alarm), delay, TimeUnit.MILLISECONDS);
    }

    private void triggerAlarm(Alarm alarm) {
        if (alarm.isActive()) {
            System.out.println("⏰ Reminder: Alarm '" + alarm.getName() + "' is due at " + alarm.getTime() + "!");
            SoundUtils.playSound(alarmSoundPath);
            alarm.makeInactive();
            removeOldAlarms();
            scheduleNextAlarm(); // Schedule the next alarm after this one
        }
    }
}
