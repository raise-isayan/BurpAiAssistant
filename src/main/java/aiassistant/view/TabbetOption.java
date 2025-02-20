package aiassistant.view;

import extension.burp.IBurpTab;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import aiassistant.model.OptionProperty;
import aiassistant.model.PromptProperty;
import burp.BurpExtension;
import extension.burp.BurpConfig;
import extension.helpers.json.JsonUtil;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author isayan
 */
public class TabbetOption extends javax.swing.JTabbedPane implements IBurpTab, PropertyChangeListener {

    private final static Logger logger = Logger.getLogger(TabbetOption.class.getName());

    public final static String LOAD_CONFIG_PROPERTY = "loadConfigProperty";

    /**
     * Creates new form Options
     */
    public TabbetOption() {
        super();
        customizeComponents();
    }

    public TabbetOption(int tabPlacement) {
        super(tabPlacement, WRAP_TAB_LAYOUT);
        customizeComponents();
    }

    private final AiAssistant tabAiAssistant = new AiAssistant();

    private void customizeComponents() {
        this.addTab(this.tabAiAssistant.getTabCaption(), this.tabAiAssistant);
        this.tabAiAssistant.addPropertyChangeListener(PromptProperty.PROMPT_PROPERTY, this);
}

    @Override
    public String getTabCaption() {
        return "Burp Ai Assistant";
    }

    @Override
    public Component getUiComponent() {
        return this;
    }

    public void setProperty(OptionProperty property) {
        this.setPromptProperty(property.getPromptProperty());
    }

    public PromptProperty getPromptProperty() {
        return this.tabAiAssistant.getPromptProperty();
    }

    public void setPromptProperty(PromptProperty promptProperty) {
        this.tabAiAssistant.setPromptProperty(promptProperty);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    public void generatePromptHistory() {
        this.tabAiAssistant.generatePromptHistory();
    }

    private File currentConigDirectory = null;

    public void importAction() {
        File file = null;
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechooser.addChoosableFileFilter(BurpConfig.BURP_CONFIG_FILTER);
        filechooser.setFileFilter(BurpConfig.BURP_CONFIG_FILTER);
        if (this.currentConigDirectory != null) {
            filechooser.setCurrentDirectory(this.currentConigDirectory);
        }
        int selected = filechooser.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            try {
                file = filechooser.getSelectedFile();
                OptionProperty option = BurpExtension.getInstance().getProperty();
                Map<String, String> config = option.loadConfigSetting();
                JsonUtil.loadFromJson(file, config);
                option.setProperty(config);
                this.currentConigDirectory = file.getParentFile();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        this.firePropertyChange(TabbetOption.LOAD_CONFIG_PROPERTY, null, this);
    }

    public void exportAction() {
        File file = null;
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechooser.addChoosableFileFilter(BurpConfig.BURP_CONFIG_FILTER);
        filechooser.setFileFilter(BurpConfig.BURP_CONFIG_FILTER);
        if (file != null && file.exists()) {
            filechooser.setSelectedFile(file);
        } else {
            filechooser.setCurrentDirectory(this.currentConigDirectory);
        }
        int selected = filechooser.showSaveDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            try {
                file = filechooser.getSelectedFile();
                if (!BurpConfig.BURP_CONFIG_FILTER.accept(file)) {
                    file = new File(file.getAbsolutePath() + ".json");
                }
                OptionProperty option = BurpExtension.getInstance().getProperty();
                Map<String, String> config = option.loadConfigSetting();
                JsonUtil.saveToJson(file, config);
                this.currentConigDirectory = file.getParentFile();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }


}
