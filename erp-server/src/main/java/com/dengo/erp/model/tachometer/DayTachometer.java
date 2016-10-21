package com.dengo.erp.model.tachometer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Day Tachometer
 *
 * @author Andrii Blyznuk
 */

@Entity
public class DayTachometer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private LocalTime startTachometer;

    private LocalTime finishTachometer;

    private String report;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BreakTachometer> breakTachometers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ScreenTachometer> screenTachometers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTachometer() {
        return startTachometer;
    }

    public void setStartTachometer(LocalTime startTachometer) {
        this.startTachometer = startTachometer;
    }

    public LocalTime getFinishTachometer() {
        return finishTachometer;
    }

    public void setFinishTachometer(LocalTime finishTachometer) {
        this.finishTachometer = finishTachometer;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Set<BreakTachometer> getBreakTachometers() {
        return breakTachometers;
    }

    public void setBreakTachometers(Set<BreakTachometer> breakTachometers) {
        this.breakTachometers = breakTachometers;
    }

    public void addBreakTachometer(BreakTachometer breakTachometer){
        this.breakTachometers.add(breakTachometer);
    }

    public Set<ScreenTachometer> getScreenTachometers() {
        return screenTachometers;
    }

    public void setScreenTachometers(Set<ScreenTachometer> screenTachometers) {
        this.screenTachometers = screenTachometers;
    }

    public void addScreenTachometer(ScreenTachometer screenTachometer){
        this.screenTachometers.add(screenTachometer);
    }
}
