package aiassistant.model;

import burp.api.montoya.ai.chat.Message;

/**
 *
 * @author isayan
 */
public class PromptMessage {

    private PromptProperty.Category category;
    private String prompt;

    public PromptMessage(PromptProperty.Category category, String prompt) {
        this.category = category;
        this.prompt = prompt;
    }

    /**
     * @return the category
     */
    public PromptProperty.Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(PromptProperty.Category category) {
        this.category = category;
    }

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Message asMessage() {
        Message message = null;
        switch (category) {
            case ASSISTANT:
                message = Message.assistantMessage(prompt);
                break;
            case USER:
                message = Message.userMessage(prompt);
                break;
            case SYSTEM:
                message = Message.systemMessage(prompt);
                break;
        }
        return message;
    }

}
