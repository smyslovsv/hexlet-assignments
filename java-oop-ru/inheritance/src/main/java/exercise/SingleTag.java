package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{
    public SingleTag(String tagName, Map<String, String> tagProp) {
        super(tagName, tagProp);
    }


    @Override
    String getTagName() {
        return super.getTagName();
    }

    @Override
    void setTagName(String tagName) {
        super.setTagName(tagName);
    }

    @Override
    Map<String, String> getTagProp() {
        return super.getTagProp();
    }

    @Override
    void setTagProp(Map<String, String> tagProp) {
        super.setTagProp(tagProp);
    }

    @Override
    public String toString() {
        String res = "<" + super.getTagName();
        Map<String, String> toRes = super.getTagProp();
        for (String key : toRes.keySet()) {
            res += " " + key + "=\"" + toRes.get(key) + "\"";
        }
        res += ">";
        return res;
    }
}
// END
