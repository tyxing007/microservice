package com.dengo.erp.model.tachometer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User Tachometer
 *
 * @author Andrii Blyznuk
 */

@Entity
public class UserTachometer {

    @Id
    private Long userId;

    private String token;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DayTachometer> dayTachometers= new HashSet<>();

    public UserTachometer() {
    }

    public UserTachometer(Long user) {
        this.userId = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<DayTachometer> getDayTachometers() {
        return dayTachometers;
    }

    public void setDayTachometers(Set<DayTachometer> dayTachometers) {
        this.dayTachometers = dayTachometers;
    }

    public void addDayTachometer (DayTachometer dayTachometer){
        this.dayTachometers.add(dayTachometer);
    }
}
