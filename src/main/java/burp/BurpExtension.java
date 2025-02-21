package burp;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.ExtensionUnloadingHandler;
import extension.burp.BurpExtensionImpl;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import aiassistant.model.OptionProperty;
import aiassistant.model.PromptProperty;
import aiassistant.view.TabbetOption;
import burp.api.montoya.EnhancedCapability;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JRadioButtonMenuItem;

public class BurpExtension extends BurpExtensionImpl implements ExtensionUnloadingHandler {

    private final static Logger logger = Logger.getLogger(BurpExtension.class.getName());

    private TabbetOption tabbetOption = null;

    @Override
    public void initialize(MontoyaApi api) {
        super.initialize(api);
        api().extension().setName("Burp Ai Assistant");
        api().extension().registerUnloadingHandler(this);
        registerMenu();
        registerView();
//        Preferences property = api().persistence().preferences();
//        // 設定ファイル読み込み
//        Map<String, String> config = this.option.loadConfigSetting();
//        Set<String> keySet = property.stringKeys();
//        for (String key : keySet) {
//            config.put(key, property.getString(key));
//        }
//        this.option.setProperty(config);
//        this.tabbetOption.setProperty(this.option);
    }

    @Override
    public Set<EnhancedCapability> enhancedCapabilities() {
        return Set.of(EnhancedCapability.AI_FEATURES);
    }

    public static JMenuItem createMenuItem(String caption, int mnemonic, ActionListener actionListener) {
        final JMenuItem yaguraMenuItem = new JMenuItem();
        yaguraMenuItem.setText(caption + " (" + (char) mnemonic + ")");
        yaguraMenuItem.setMnemonic(mnemonic);
        yaguraMenuItem.addActionListener(actionListener);
        return yaguraMenuItem;
    }

    private final ActionListener loadAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabbetOption.importAction();
        }
    };

    private final ActionListener saveAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabbetOption.exportAction();
        }
    };

    private final ActionListener debugAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JRadioButtonMenuItem aiDebugItem) {
                getProperty().setDebug(aiDebugItem.isSelected());
                applyOptionProperty();
            }
        }
    };

    private void registerMenu() {
        final JMenu aiMenu = new JMenu();
        aiMenu.setText("Ai Assistant");
        JMenuItem aiLoadItem = createMenuItem("Load", KeyEvent.VK_L, this.loadAction);
        aiMenu.add(aiLoadItem);
        JMenuItem aiSaveItem = createMenuItem("Save", KeyEvent.VK_S, this.saveAction);
        aiMenu.add(aiSaveItem);
        aiMenu.addSeparator();
        JRadioButtonMenuItem aiDebugItem = new JRadioButtonMenuItem();
        aiDebugItem.setText("Test Mode (T)");
        aiDebugItem.setMnemonic(KeyEvent.VK_T);
        aiDebugItem.addActionListener(debugAction);
        aiMenu.add(aiDebugItem);
        api().userInterface().menuBar().registerMenu(aiMenu);
    }

    public void registerView() {
        final MontoyaApi api = api();
        this.tabbetOption = new TabbetOption();
        this.tabbetOption.setProperty(this.option);
        this.tabbetOption.addPropertyChangeListener(newPropertyChangeListener());
        api.extension().registerUnloadingHandler(this);
        api.userInterface().registerSuiteTab(this.tabbetOption.getTabCaption(), this.tabbetOption);
    }

    @SuppressWarnings("unchecked")
    public static BurpExtension getInstance() {
        return BurpExtensionImpl.<BurpExtension>getInstance();
    }

    protected PropertyChangeListener newPropertyChangeListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (PromptProperty.PROMPT_PROPERTY.equals(evt.getPropertyName())) {
                    option.setPromptProperty(tabbetOption.getPromptProperty());
                    applyOptionProperty();
                }
                else if (TabbetOption.LOAD_CONFIG_PROPERTY.equals(evt.getPropertyName())) {
                    tabbetOption.setProperty(option);
                    tabbetOption.generatePromptHistory();
                    applyOptionProperty();
                }
            }
        };
    }

    public Component getUiComponent() {
        return this.tabbetOption;
    }

    private final OptionProperty option = new OptionProperty();

    public OptionProperty getProperty() {
        return option;
    }

    protected void applyOptionProperty() {
        try {
            Map<String, String> config = this.option.getProperty();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void extensionUnloaded() {
        this.applyOptionProperty();
    }

}
