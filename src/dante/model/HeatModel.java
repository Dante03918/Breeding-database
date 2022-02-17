package model;

import java.time.LocalDate;

public class HeatModel {

    private LocalDate heatStart;
    private LocalDate heatEnd;

    public HeatModel(LocalDate heatStart, LocalDate heatEnd) {
        this.heatStart = heatStart;
        this.heatEnd = heatEnd;
    }

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
