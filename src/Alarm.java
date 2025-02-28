import java.time.LocalTime;

class Alarm implements Comparable<Alarm>{
    private String name;
    private LocalTime time;
    private boolean isActive;

    Alarm (String name, LocalTime time) {
        this.name = name;
        this.time = time;

        // Check if time is a future time
        LocalTime currTime = LocalTime.now();
        isActive = time.isAfter(currTime);
    }

    public String getName () {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }
    public boolean isInactive() {
        return !isActive;
    }

    public LocalTime getTime () {
        return time;
    }

    @Override
    public int compareTo(Alarm otherAlarm) {
        return time.compareTo(otherAlarm.time);
    }

    @Override
    public String toString() {
        return "Alarm: " + name + " to go off at " + time;
    }
}
