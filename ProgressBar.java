import java.awt.Color;
import javax.swing.JProgressBar;

public class ProgressBar {
    private int goal;
    private int currentProgress;
    public JProgressBar progressBar;

    public ProgressBar(int goal) {
        this.goal = goal;
        this.currentProgress = 0;

        progressBar = new JProgressBar(currentProgress, this.goal);
        progressBar.setBounds(50, 50, 500, 50);
        progressBar.setForeground(Color.GREEN);
        progressBar.setStringPainted(true);
    }

    public void updateProgress(int progress) {
        currentProgress += progress;
        progressBar.setValue(currentProgress);
    }
}
