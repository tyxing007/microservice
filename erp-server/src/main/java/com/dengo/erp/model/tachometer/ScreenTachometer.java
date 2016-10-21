package com.dengo.erp.model.tachometer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

/**
 * Screen Tachometer
 *
 * @author Andrii Blyznuk
 */

@Entity
public class ScreenTachometer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalTime time;

    private String pathToScreen;

    private String titleWindow;

    private Integer persentMouse;

    private Integer persentKeyboard;

    private LocalTime removeScreen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPathToScreen() {
        return pathToScreen;
    }

    public void setPathToScreen(String pathToScreen) {
        this.pathToScreen = pathToScreen;
    }

    public String getTitleWindow() {
        return titleWindow;
    }

    public void setTitleWindow(String titleWindow) {
        this.titleWindow = titleWindow;
    }

    public Integer getPersentMouse() {
        return persentMouse;
    }

    public void setPersentMouse(Integer persentMouse) {
        this.persentMouse = persentMouse;
    }

    public Integer getPersentKeyboard() {
        return persentKeyboard;
    }

    public void setPersentKeyboard(Integer persentKeyboard) {
        this.persentKeyboard = persentKeyboard;
    }

    public LocalTime getRemoveScreen() {
        return removeScreen;
    }

    public void setRemoveScreen(LocalTime removeScreen) {
        this.removeScreen = removeScreen;
    }
}
