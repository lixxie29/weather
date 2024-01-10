package domain;

public class Weather {
    private int startHour;
    private int endHour;
    private int temp;
    private int precipProb;
    private String description;

    public Weather(int startHour, int endHour, int temp, int precipProb, String description) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.temp = temp;
        this.precipProb = precipProb;
        this.description = description;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getPrecipProb() {
        return precipProb;
    }

    public void setPrecipProb(int precipProb) {
        this.precipProb = precipProb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "startHour=" + startHour +
                ", endHour=" + endHour +
                ", temp=" + temp +
                ", precipProb=" + precipProb +
                ", description='" + description + '\'' +
                '}';
    }
}
