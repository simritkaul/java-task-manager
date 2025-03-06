import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class AlarmManager {
    private List<Alarm> alarmList = new ArrayList<Alarm>();
    private PriorityQueue<Alarm> alarmQueue = new PriorityQueue<>();
    private static final String alarmSoundPath = "src/resources/alarmsound.wav";

    AlarmManager () {
        startAlarmThread();
    }

    public void addAlarm (String name, LocalTime time) {
        Alarm newAlarm = new Alarm(name, time);
        alarmList.add(newAlarm);
        alarmQueue.offer(newAlarm);
    }

    public void removeOldAlarms () {
        alarmList.removeIf(Alarm::isInactive);
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

    private void startAlarmThread () {
        Thread alarmThread = new Thread(() -> {
            while (true) {
                removeOldAlarms();
                LocalTime now = LocalTime.now();
                Optional<Duration> sleepDuration = Optional.empty();

                for (Alarm alarm : alarmList) {
                    if (alarm.isActive() && alarm.getTime().isBefore(now)) {
                        System.out.println("⏰ Reminder: Alarm '" + alarm.getName() + "' is due at " + alarm.getTime() + "!");
                        SoundUtils.playSound(alarmSoundPath);
                        alarm.makeInactive();
                    } else if (alarm.isActive() && alarm.getTime().isAfter(now)) {
                        Duration duration = Duration.between(now, alarm.getTime());
                        if (sleepDuration.isEmpty() || duration.compareTo(sleepDuration.get()) < 0) {
                            sleepDuration = Optional.of(duration);
                        }
                    }
                }
                try {
                    if (sleepDuration.isPresent()) {
                        long sleepMilliSeconds = sleepDuration.get().toMillis();
                        if (sleepMilliSeconds > 0) {
                            Thread.sleep(sleepMilliSeconds);
                        } else {
                            Thread.sleep(10);
                        }
                    } else {
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        alarmThread.setDaemon(true);
        alarmThread.start();
    }
}
