import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import sun.rmi.runtime.Log;

/**
 * Created by wreid on 1/25/16.
 */
public class GameData {

    final static int STARTING_MONEY = 1;

    private BigDecimal totalMoney = BigDecimal.valueOf(0);
    private BigDecimal totalMPS = BigDecimal.valueOf(0);

    public AchievementManager getAchievementManager() {
        return achievementManager;
    }
    private AchievementManager achievementManager = new AchievementManager();


    public static class Achievements {
        static volatile boolean dataChanged = false;

        public static ArrayList<Property> ml0 = new ArrayList<Property>();
        public static ArrayList<Property> ml1 = new ArrayList<Property>();
        public static ArrayList<Property> ml2 = new ArrayList<Property>();
        public static ArrayList<Property> ml3 = new ArrayList<Property>();
        public static ArrayList<Property> ml4 = new ArrayList<Property>();
        public static ArrayList<Property> ml5 = new ArrayList<Property>();
    }

    GameData() {

        achievementManager.defineProperty("moneyone", 0, AchievementManager.ACTIVE_IF_GREATER_THAN, 1);
        achievementManager.defineProperty("money10", 2, AchievementManager.ACTIVE_IF_GREATER_THAN, 10);
        achievementManager.defineProperty("money100", 11, AchievementManager.ACTIVE_IF_GREATER_THAN, 100);
        achievementManager.defineProperty("money1000", 101, AchievementManager.ACTIVE_IF_GREATER_THAN, 1000);
        achievementManager.defineProperty("money10000", 10001, AchievementManager.ACTIVE_IF_GREATER_THAN, 10000);

        Achievements.ml0.add(achievementManager.getValue("moneyone"));
        Achievements.ml1.add(achievementManager.getValue("money10"));
        Achievements.ml2.add(achievementManager.getValue("money100"));
        Achievements.ml3.add(achievementManager.getValue("money1000"));
        Achievements.ml4.add(achievementManager.getValue("money10000"));

        Achievements.ml5.add(achievementManager.getValue("moneyone"));
        Achievements.ml5.add(achievementManager.getValue("money10"));
        Achievements.ml5.add(achievementManager.getValue("money100"));
        Achievements.ml5.add(achievementManager.getValue("money1000"));
        Achievements.ml5.add(achievementManager.getValue("money10000"));

        achievementManager.defineAchievement("money1", "You never forget your first dollar.", Achievements.ml0); // Get 1 gold through auto clickers
        achievementManager.defineAchievement("money10", "Drinks are on me.", Achievements.ml1); // Get 1 gold through auto clickers
        achievementManager.defineAchievement("money100", "Steak or lobster, because dinner is on me.", Achievements.ml2); // Get 100 gold through auto clickers
        achievementManager.defineAchievement("money1000", "Making it rain.", Achievements.ml3); //Get 1000 gold through auto clickers
        achievementManager.defineAchievement("money10000", "The sky's the limit.", Achievements.ml4); // Get 10000 gold through auto clickers


    }
    /**
     * Updates the gamedata
     * @param deltaTime Time since last update call in seconds
     */
    public synchronized void update(double deltaTime) {

        //Reset & recalculate MPS
        totalMPS = BigDecimal.valueOf(1);

        // track Money Per Second towards achievements

        totalMoney  = totalMoney.add(BigDecimal.valueOf(totalMPS.doubleValue() * deltaTime));

        achievementManager.setValue(Achievements.ml5, totalMoney.doubleValue());

        // Check Achievements
        Vector<Achievement> finished = getAchievementManager().checkAchievements();
        for (int i = 0; i < finished.size(); i++) {
            System.out.println("Achievement: " + finished.get(i).getDesc());
        }

    }

}
