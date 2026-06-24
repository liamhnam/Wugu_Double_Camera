package androidx.core.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.util.List;

public interface LocationListenerCompat extends LocationListener {
    @Override
    default void onFlushComplete(int i) {
    }

    @Override
    default void onProviderDisabled(String str) {
    }

    @Override
    default void onProviderEnabled(String str) {
    }

    @Override
    default void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override
    default void onLocationChanged(List<Location> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            onLocationChanged(list.get(i));
        }
    }
}
