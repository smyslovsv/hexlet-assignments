package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String tagName;
    private Map<String, String> tagProp = new LinkedHashMap<>();
    String getTagName() {
        return tagName;
    }
    void setTagName(String tagName) {
        this.tagName = tagName;
    }

    Map<String, String> getTagProp() {
        return tagProp;
    }

    void setTagProp(Map<String, String> tagProp) {
        this.tagProp = tagProp;
    }


    public Tag(String tagName, Map<String, String> tagProp) {
        setTagName(tagName);
        setTagProp(tagProp);
    }

    public abstract String toString();
}
// END
