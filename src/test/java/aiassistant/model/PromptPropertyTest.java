package aiassistant.model;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author isayan
 */
public class PromptPropertyTest {

    public PromptPropertyTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPromptList method, of class PromptProperty.
     */
    @Test
    public void testGetPromptList() {
        System.out.println("getPromptList");
        final OptionProperty option = new OptionProperty();

        Map<String, String> config = option.getProperty();
        PromptProperty prompt = new PromptProperty();
        prompt.saveSetting(config.get(PromptProperty.PROMPT_PROPERTY));

        List<PromptItem> list = prompt.getPromptList();
        {
            PromptItem item = new PromptItem();
            item.setPromptCategory(PromptProperty.Category.ASSISTANT);
            item.setPromptRequest("request message1");
            item.setPromptResponse("reposense message1");
            list.add(item);
        }
        {
            PromptItem item = new PromptItem();
            item.setPromptCategory(PromptProperty.Category.ASSISTANT);
            item.setPromptRequest("request message2");
            item.setPromptResponse("reposense message2");
            list.add(item);
        }
        String loadConfig = prompt.loadSetting();
        System.out.println("config:" + loadConfig);

    }

}
