package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag{
    private String text;
    private List<Tag> list;

    private String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    private List<Tag> getList() {
        return list;
    }

    private void setList(List<Tag> list) {
        this.list = new ArrayList<>(list);
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
        Map<String, String> toRes = getTagProp();
        for (String key : toRes.keySet()) {
            res += " " + key + "=\"" + toRes.get(key) + "\"";
        }
        res += ">";
        List<Tag> toResArr = getList();
        for (int i = 0; i < toResArr.size(); i++) {
            res += toResArr.get(i).toString();
        }

        res += getText() + "</" + super.getTagName() + ">";
        return res;    }

    public PairedTag(String tagName, Map<String, String> tagProp, String text, List<Tag> includesTag) {
        super(tagName, tagProp);
        setText(text);
        setList(includesTag);
    }
}
// END
