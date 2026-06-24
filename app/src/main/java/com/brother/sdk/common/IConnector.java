package com.brother.sdk.common;

import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.Device;
import java.io.Serializable;

public interface IConnector extends IUnknown, Serializable {

    public static final String f477ID = "IConnector";

    Object getConnectorIdentifier();

    Device getDevice();

    Job.JobState submit(Job job);

    boolean validate();
}
