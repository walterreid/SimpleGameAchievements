
import java.util.ArrayList;

/**
 * Created by wreid on 1/8/16.
 */
public class Achievement
{
    private String name; // achievement name
    private String desc; // achievement desc
    private ArrayList <Property> props; // array of related properties
    private boolean unlocked; // achievement is unlocked or not

    public Achievement(String theId, String desc, ArrayList <Property> theRelatedProps) {
        this.name = theId;
        this.desc = desc;
        this.props = theRelatedProps;
        this.unlocked = false;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList getProps() {
        return props;
    }

    public void setProps(ArrayList<Property> props) {
        this.props = props;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

}
