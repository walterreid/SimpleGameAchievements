import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Created by wreid on 1/8/16.
 */
public class AchievementManager
{
    // activation rules
    final public static String ACTIVE_IF_GREATER_THAN = ">";
    final public static String ACTIVE_IF_LESS_THAN = "<";
    final public static String ACTIVE_IF_EQUALS_TO = "==";

    private NavigableMap<String, Property> propertyMap = new TreeMap<String, Property>(); // dictionary of properties
    private NavigableMap<String, Achievement> achievementMap = new TreeMap<String, Achievement>(); // dictionary of achievements

    public void Achieve() {

    }

    public void defineProperty(String theName,Property prop) {
        propertyMap.put(theName, prop);
    }

    public void defineProperty(String theName,int theInitialValue, String theaActivationMode, int theValue) {
        propertyMap.put(theName, new Property(theName, theInitialValue, theaActivationMode, theValue));
    }

    public void defineAchievement(String theName, String desc, ArrayList theRelatedProps) {
        achievementMap.put(theName, new Achievement(theName, desc, theRelatedProps));
    }

    public Property getValue(String theProp) {
        Map.Entry<String, Property> entry = propertyMap.floorEntry(theProp);
        return entry.getValue(); //mProps[theProp].value;
    }

    public void addValue(String theName, double theValue) {
        Map.Entry<String, Property> entry = propertyMap.floorEntry(theName);
        Property prop = entry.getValue();
        setValue(theName, getValue(theName).getValue() + theValue);
    }

    public void addValue(ArrayList theProps, double theValue) {
        for (int i = 0; i < theProps.size(); i++) {
            Property prop = (Property)theProps.get(i);
            prop.setValue(prop.getValue() + theValue);
        }
    }

    public void setValue(ArrayList theProps, double theValue) {
        for (int i = 0; i < theProps.size(); i++) {
            Property prop = (Property)theProps.get(i);
            prop.setValue(theValue);
        }
    }

    private void setValue(String theName, double theValue) {
        Map.Entry<String, Property> entry = propertyMap.floorEntry(theName);
        entry.getValue().setValue(theValue);

        // Which activation rule?
        switch (entry.getValue().getActivation()) {
            case AchievementManager.ACTIVE_IF_GREATER_THAN:
                theValue = theValue > entry.getValue().getValue() ? theValue : entry.getValue().getValue();
                break;
            case AchievementManager.ACTIVE_IF_LESS_THAN:
                theValue = theValue < entry.getValue().getValue() ? theValue : entry.getValue().getValue();
                break;
        }
        entry.getValue().setValue(theValue);
    }

    public Vector checkAchievements() {
        Vector aRet = new Vector();

        for (Map.Entry<String, Achievement> achentry : achievementMap.entrySet()) {
            Achievement anAchievement = achentry.getValue();

            if (anAchievement.isUnlocked() == false) {
                int aActiveProps = 0;

                for (int i = 0; i < anAchievement.getProps().size(); i++) {
                    ArrayList <Property> a = anAchievement.getProps();

                    if (a.get(i).isActive()) {
                        aActiveProps++;
                    }
                }

                if (aActiveProps == anAchievement.getProps().size()) {
                    anAchievement.setUnlocked(true);
                    aRet.add(anAchievement);
                }
            }
        }
        return aRet;
    }

}
