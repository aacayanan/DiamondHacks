import java.util.HashMap;

public class User {
    private String userId;
    private int dailyGoal;
    private HashMap<String, IntakeLog> userLogs;
    private IntakeLog log = new IntakeLog();

    public User() {
        userLogs = new HashMap<>();
    }

    public void updateIntake(String userId, String foodItem, int vitaminAmount) {
        // check if the userLogs is empty, if so create a new hashmap
        if (userLogs == null) {
            userLogs = new HashMap<>();
        }
        // check if the userLogs contains userId, if not create a new log
        if (userLogs.containsKey(userId)) {
            log.addEntry(foodItem, vitaminAmount);
        } else {
            userLogs.put(userId, log);
            updateIntake(userId, foodItem, vitaminAmount);
        }
    }
}
