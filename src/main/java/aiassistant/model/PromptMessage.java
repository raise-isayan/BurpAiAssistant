package aiassistant.model;

import burp.api.montoya.ai.chat.Message;

/**
 *
 * @author isayan
 */
public class PromptMessage {

    private final PromptItem item;

    public PromptMessage(PromptItem item) {
        this.item = item;
    }

    /**
     * @return the category
     */
    public PromptProperty.Category getPromptCategory() {
        return item.getPromptCategory();
    }

    /**
     * @param category the category to set
     */
    public void setPromptCategory(PromptProperty.Category category) {
        this.item.setPromptCategory(category);
    }

    /**
     * @return the prompt
     */
    public String getPromptRequest() {
        return item.getPromptRequest();
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPromptRequest(String prompt) {
        this.item.setPromptRequest(prompt);
    }

    public String getPromptResponse() {
        return this.item.getPromptResponse();
    }

    public void setPromptResponse(String prompt) {
        this.item.setPromptResponse(prompt);
    }

    public PromptItem getPromptItem() {
        return this.item;
    }

    public Message asMessage() {
        Message message = null;
        switch (this.item.getPromptCategory()) {
            case ASSISTANT:
                message = Message.assistantMessage(this.item.getPromptRequest());
                break;
            case USER:
                message = Message.userMessage(this.item.getPromptRequest());
                break;
            case SYSTEM:
                message = Message.systemMessage(this.item.getPromptRequest());
                break;
        }
        return message;
    }

}
