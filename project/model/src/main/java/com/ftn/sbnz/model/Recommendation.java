package com.ftn.sbnz.model;

public class Recommendation {

    public enum Category { TRADE, ROAM, DRAGON, RECALL, CS }

    private String ruleId;
    private String message;
    private int priority;
    private Category category;
    private ActionState suggestedAction;
    private int timestamp;

    public Recommendation() {}

    public Recommendation(String ruleId, String message, int priority,
                          Category category, ActionState suggestedAction) {
        this.ruleId = ruleId;
        this.message = message;
        this.priority = priority;
        this.category = category;
        this.suggestedAction = suggestedAction;
    }

    public String getRuleId() { return ruleId; }
    public void setRuleId(String ruleId) { this.ruleId = ruleId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public ActionState getSuggestedAction() { return suggestedAction; }
    public void setSuggestedAction(ActionState suggestedAction) { this.suggestedAction = suggestedAction; }

    public int getTimestamp() { return timestamp; }
    public void setTimestamp(int timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return String.format("[P%d][%s] %s (Rule:%s)", priority, category, message, ruleId);
    }
}