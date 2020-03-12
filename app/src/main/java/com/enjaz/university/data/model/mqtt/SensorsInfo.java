
package com.enjaz.university.data.model.mqtt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SensorsInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("turnedOn")
    @Expose
    private Boolean turnedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(Boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

}
