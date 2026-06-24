package cc.uling.usdk.constants;

import com.p020hp.jipp.model.WhichPrinter;

public class CodeUtil {
    public static String getErrorMsg(int i) {
        return i == 1 ? "Illegal function code." : i == 2 ? "Invalid data address." : i == 3 ? "Invalid data." : i == 4 ? "Validation code does not match." : i == 6 ? "Device is busy" : i == 7 ? "The device has malfunctioned." : i == 8 ? "The slave has received the request for data processing from the master, but it requires a long processing time. In response, the master sends a \"query program complete\" message to determine whether the slave has completed the processing." : "Unknown error";
    }

    public static String getFaultMsg(int i) {
        return i == 0 ? "No errors." : i == 1 ? "Invalid channel number" : i == 2 ? "The motor is not rotating, no current passing through (plug not inserted properly or broken wire)." : i == 3 ? "Motor short circuit. (Excessive current or short circuit at the wire end)" : i == 4 ? "The motor can rotate, but there is no feedback signal (the motor feedback is not connected properly)." : i == 5 ? "Drop detection board status abnormal (Drop detection is enabled, but the drop detection board is not connected or malfunctioning)" : i == 6 ? "The motor is rotating normally, but no cargo is detected (turn on cargo detection)." : i == 7 ? "Vend lane type setting error (mismatch between setting and actual)." : i == 8 ? "Device communication failure (poor connection or incorrect address setting)" : i == 16 ? "Elevator communication failure (check the line)." : i == 17 ? "The lift platform has encountered a malfunction." : i == 18 ? "Platform is occupied, unable to dispense goods." : i == 19 ? "Lower limit switch failure of the elevation system." : i == 20 ? "Limit switch for the elevator system right limit position malfunction." : i == 21 ? "The anti-theft shield is malfunctioning." : i == 22 ? "The pick-up port is experiencing a malfunction." : i == 23 ? "The lift motor is not powered (no current, check the circuit)." : i == 24 ? "Short circuit (overload, circuit short or burnt motor) of the lift motor." : i == 25 ? "Motor encoder malfunction (check motor feedback line)." : i == 26 ? "Horizontal motor is not powered (no current, check the circuit)." : i == 27 ? "Horizontal motor short circuit (overload, line short circuit or motor burned out)" : i == 28 ? "Encoder failure of horizontal motor (Check motor feedback line)" : "Unknown Error";
    }

    public static String getShipmentStatusMsg(int i) {
        return i == 0 ? "Idle" : i == 1 ? "Shipping in progress (busy)" : i == 2 ? "Shipment completed (Shipment successful)" : i == 3 ? "Fault status (shipment failed)" : "Unknown status";
    }

    public static String getXYStatusMsg(int i) {
        return i == 0 ? WhichPrinter.idle : i == 1 ? "The motor is addressing." : i == 2 ? "Motor completes addressing." : i == 3 ? "Fault occurred." : "Unknown Status.";
    }
}
