package com.carme.ums.component;

import com.carme.common.service.SpringContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropComponent {

    /**
     * request日志开关
     */
    @Value(value = "${ums.request.switch}")
    private boolean requestSwitch = true;

    public boolean isRequestSwitch() {
        return requestSwitch;
    }

    public void setRequestSwitch(boolean requestSwitch) {
        this.requestSwitch = requestSwitch;
    }

    public static PropComponent getProp() {
        return SpringContext.getBean(PropComponent.class);
    }
}
