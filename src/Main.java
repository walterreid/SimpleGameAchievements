import java.util.ArrayList;

public class Main extends Thread {

    public static final long UPDATE_RATE_MS = 300;

    private boolean gameOver = false;

    private GameData data;

    public static void main(String [] args)
    {
        Main instance = new Main();
    }

    private Main() {
        this.setName("Main");
        data = new GameData();
        this.start();
    }

    @Override
    public void run() {

        long updateTime = System.currentTimeMillis();
        long sleepTime = 1;

        while(!gameOver) {
            data.update(sleepTime / 1000.0);

            updateTime += UPDATE_RATE_MS;
            sleepTime = updateTime - System.currentTimeMillis();

            if(sleepTime >= 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
