# SimpleGameAchievements
Easily implement achievements in a game though a combination of properties and activation rules. After each property, for a given achievement is unlocked, the achievement is then unlocked.

I included a fully threaded example using "money" generated per second and a series of achievements unlocked when the money reaches the following levels, 1, 10, 100, 1000 and 10000
## Usage
AchievementManager am = new AchievementManager();

```
// Define properties based on a "name", "initial value", "condition" and "unlock value"
// Example: Consider property labled, "money1". Initial value of "0" when it reaches or exceeds a value of "1"
// the propery will be considered unlocked.
am.defineProperty("money1", 0, AchievementManager.ACTIVE_IF_GREATER_THAN, 1);
am.defineProperty("money10", 2, AchievementManager.ACTIVE_IF_GREATER_THAN, 10);
am.defineProperty("money100", 11, AchievementManager.ACTIVE_IF_GREATER_THAN, 100);
am.defineProperty("money1000", 101, AchievementManager.ACTIVE_IF_GREATER_THAN, 1000);
am.defineProperty("money10000", 10001, AchievementManager.ACTIVE_IF_GREATER_THAN, 10000);

// Create an ArrayList w/ one or multiple properties
Achievements.ml0.add(am.getValue("money1"));
Achievements.ml1.add(am.getValue("money10"));
Achievements.ml2.add(am.getValue("money100"));
Achievements.ml3.add(am.getValue("money1000"));
Achievements.ml4.add(am.getValue("money10000"));

// Achievement is considered based on a single or group of properties.
// Define achievement based on a "name", "description" and an ArrayList
// Once all propoerties in the ArrayList have met there conditions, the achievement is considered unlocked.
am.defineAchievement("achievemoney1", "You never forget your first dollar.", Achievements.ml0); // Get 1 gold through auto clickers
am.defineAchievement("achievemoney10", "Drinks are on me.", Achievements.ml1); // Get 10 gold through auto clickers
am.defineAchievement("achievemoney100", "Steak or lobster, because dinner is on me.", Achievements.ml2); // Get 100 gold through auto clickers
am.defineAchievement("achievemoney1000", "Making it rain.", Achievements.ml3); //Get 1000 gold through auto clickers
am.defineAchievement("achievemoney10000", "The sky's the limit.", Achievements.ml4); // Get 10000 gold through auto clickers
```

## Motivation
Based off of the original JS Achievement work of Dovyski [github.com/Dovyski](https://github.com/Dovyski)
Original credit goes to ["how to code unlockable achievements for your game (a simple approach)"](http://gamedevelopment.tutsplus.com/tutorials/how-to-code-unlockable-achievements-for-your-game-a-simple-approach--gamedev-6012)

## Contributors
Feel free to submit pull requests or  [open a new issue](https://github.com/walterreid/SimpleGameAchievements/issues)

## License
Licensed under the MIT license.
