package aiassistant.model;

import com.google.gson.annotations.Expose;
import extension.burp.IPropertyConfig;
import extension.helpers.json.JsonUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isayan
 */
public class PromptProperty implements IPropertyConfig {

    public enum Category {
        ASSISTANT, USER, SYSTEM
    };

    public static String PROMPT_PROPERTY = "PromptProperty";

    @Expose
    private final List<PromptItem> promptList = new ArrayList<>();

    /**
     * @return the subnetList
     */
    public List<PromptItem> getPromptList() {
        return this.promptList;
    }

    public void setPromptList(List<PromptItem> promptList) {
        this.promptList.clear();
        this.promptList.addAll(promptList);
    }

    public void setProperty(PromptProperty promptProperty) {
        this.setPromptList(promptProperty.getPromptList());
    }

    @Override
    public String getSettingName() {
        return PROMPT_PROPERTY;
    }

    @Override
    public void saveSetting(String value) {
        PromptProperty property = JsonUtil.jsonFromString(value, PromptProperty.class, true);
        this.setProperty(property);
    }

    @Override
    public String loadSetting() {
        return JsonUtil.jsonToString(this, true);
    }

    @Override
    public String defaultSetting() {
        PromptProperty property = new PromptProperty();
        return JsonUtil.jsonToString(property, true);
    }

}
