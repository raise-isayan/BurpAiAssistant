package aiassistant.model;

import com.google.gson.annotations.Expose;

/**
 *
 * @author isayan
 */
public class PromptItem {

    @Expose
    private PromptProperty.Category promptCategory = PromptProperty.Category.ASSISTANT;

    /**
     * @return the promptCategory
     */
    public PromptProperty.Category getPromptCategory() {
        return promptCategory;
    }

    /**
     * @param promptCategory the promptCategory to set
     */
    public void setPromptCategory(PromptProperty.Category promptCategory) {
        this.promptCategory = promptCategory;
    }

    @Expose
    private String promptRequest;

    /**
     * @return the promptRequest
     */
    public String getPromptRequest() {
        return promptRequest;
    }

    /**
     * @param promptRequest the promptResponse to set
     */
    public void setPromptRequest(String promptRequest) {
        this.promptRequest = promptRequest;
    }

    @Expose
    private String promptResponse;

    /**
     * @return the promptResponse
     */
    public String getPromptResponse() {
        return promptResponse;
    }

    /**
     * @param promptResponse the promptResponse to set
     */
    public void setPromptResponse(String promptResponse) {
        this.promptResponse = promptResponse;
    }

}
