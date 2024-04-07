import java.util.HashMap;

public class User {
    private String userId;
    private int dailyGoal;
    private HashMap<String, IntakeLog> userLogs;
    private IntakeLog log = new IntakeLog();
    private ProgressBar progress;

    public User(String userId) {
        this.userId = userId;
        this.progress = new ProgressBar();

        if (userLogs == null) {
            userLogs = new HashMap<>();
        }
    }

    public void updateIntake(String userId, String foodItem, int vitaminAmount) {
        // check if the userLogs is empty, if so create a new hashmap
        if (userLogs == null) {
            userLogs = new HashMap<>();
            updateIntake(userId, foodItem, vitaminAmount);
        }
        // check if the userLogs contains userId, if not create a new log
        if (userLogs.containsKey(userId)) {
            log.addEntry(foodItem, vitaminAmount);
        } else {
            userLogs.put(userId, log);
            updateIntake(userId, foodItem, vitaminAmount);
            progress.updateProgress(vitaminAmount);
        }
    }

    public String getLogs(String userId) {
        IntakeLog log = userLogs.get(userId);
        if (log != null) {
            return log.getEntries();
        } else {
            return "No logs found for user: " + userId;
        }
    }
    
    public ProgressBar getProgressBar() {
        return this.progress;
    }

    public void updateProgressBar(int value) {
        this.progress.updateProgress(value);
    }
}
