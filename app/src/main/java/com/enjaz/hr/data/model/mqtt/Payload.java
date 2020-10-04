
package com.enjaz.hr.data.model.mqtt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("sensors_info")
    @Expose
    private List<SensorsInfo> sensorsInfo = null;

    public List<SensorsInfo> getSensorsInfo() {
        return sensorsInfo;
    }

    public void setSensorsInfo(List<SensorsInfo> sensorsInfo) {
        this.sensorsInfo = sensorsInfo;
    }

}
