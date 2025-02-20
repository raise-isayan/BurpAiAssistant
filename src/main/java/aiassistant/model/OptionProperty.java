package aiassistant.model;

import com.google.gson.annotations.Expose;
import extension.burp.IOptionProperty;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author isayan
 */
public class OptionProperty implements IOptionProperty {


    private final Map<String, String> config = new HashMap();

    @Override
    public void saveConfigSetting(final Map<String, String> value) {
        this.config.putAll(value);
    }

    @Override
    public Map<String, String> loadConfigSetting() {
        return this.config;
    }

    @Expose
    private final PromptProperty promptProperty = new PromptProperty();

    public PromptProperty getPromptProperty() {
        return this.promptProperty;
    }

    public void setPromptProperty(PromptProperty promptProperty) {
        this.promptProperty.setProperty(promptProperty);
    }

    /**
     *
     * @return
     */
    public Map<String, String> getProperty() {
        this.config.put(this.promptProperty.getSettingName(), this.promptProperty.loadSetting());
        return this.config;
    }

    public void setProperty(Map<String, String> config) {
        String configPromptProperty = config.get(this.promptProperty.getSettingName());
        if (configPromptProperty != null) {
            this.promptProperty.saveSetting(configPromptProperty);
        }
    }

    private boolean debug = false;

    public boolean getDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
