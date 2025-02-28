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
            int alarmRingCount = 0;
            while (true) {
                LocalTime now = LocalTime.now();
                for (Alarm alarm : alarmList) {
                    if (alarmRingCount > 3) {
                        removeOldAlarms();
                    }
                    if (alarm.isActive() && alarm.getTime().isBefore(now)) {
                        System.out.println("⏰ Reminder: Alarm '" + alarm.getName() + "' is due at " + alarm.getTime() + "!");
                        SoundUtils.playSound(alarmSoundPath);
                        alarmRingCount++;
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        alarmThread.setDaemon(true);
        alarmThread.start();
    }
}
