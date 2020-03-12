
package com.enjaz.university.data.model.mqtt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MQTTResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}
