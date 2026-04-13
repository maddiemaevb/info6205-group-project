package model;

public class Alert {

    private String alertType;
    private String severity;
    private String message;

    public Alert() {
    }

    public Alert(String alertType, String severity, String message) {
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
    }

    public String getAlertType() {
        return this.alertType;
    }

    public String getSeverity() {
        return this.severity;
    }

    public String getMessage() {
        return this.message;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertType='" + alertType + '\'' +
                ", severity='" + severity + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}