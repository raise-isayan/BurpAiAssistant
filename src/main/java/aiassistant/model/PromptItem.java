package aiassistant.model;

import com.google.gson.annotations.Expose;

/**
 *
 * @author isayan
 */
public class PromptItem {

    public PromptItem() {
    }

    @Expose
    private PromptProperty.Category promptCategory = PromptProperty.Category.SYSTEM;

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
    private int temperature = 80;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public double asTemperature() {
        return (double) this.temperature / (double) 100.0;
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
