package model;

import java.time.LocalDate;

public class HeatModel {

    private LocalDate heatStart;
    private LocalDate heatEnd;

    public LocalDate getHeatStart() {
        return heatStart;
    }

    public void setHeatStart(LocalDate heatStart) {
        this.heatStart = heatStart;
    }

    public LocalDate getHeatEnd() {
        return heatEnd;
    }

    public void setHeatEnd(LocalDate heatEnd) {
        this.heatEnd = heatEnd;
    }
}
