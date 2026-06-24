package com.google.android.material.color;

import com.google.android.material.C1156R;
import com.google.android.material.color.utilities.Scheme;
import java.util.HashMap;
import java.util.Map;

final class MaterialColorUtilitiesHelper {
    private MaterialColorUtilitiesHelper() {
    }

    static Map<Integer, Integer> createColorResourcesIdsToColorValues(Scheme scheme) {
        HashMap map = new HashMap();
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_primary), Integer.valueOf(scheme.getPrimary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_primary), Integer.valueOf(scheme.getOnPrimary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_primary_inverse), Integer.valueOf(scheme.getInversePrimary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_primary_container), Integer.valueOf(scheme.getPrimaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_primary_container), Integer.valueOf(scheme.getOnPrimaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_secondary), Integer.valueOf(scheme.getSecondary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_secondary), Integer.valueOf(scheme.getOnSecondary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_secondary_container), Integer.valueOf(scheme.getSecondaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_secondary_container), Integer.valueOf(scheme.getOnSecondaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_tertiary), Integer.valueOf(scheme.getTertiary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_tertiary), Integer.valueOf(scheme.getOnTertiary()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_tertiary_container), Integer.valueOf(scheme.getTertiaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_tertiary_container), Integer.valueOf(scheme.getOnTertiaryContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_background), Integer.valueOf(scheme.getBackground()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_background), Integer.valueOf(scheme.getOnBackground()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_surface), Integer.valueOf(scheme.getSurface()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_surface), Integer.valueOf(scheme.getOnSurface()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_surface_variant), Integer.valueOf(scheme.getSurfaceVariant()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_surface_variant), Integer.valueOf(scheme.getOnSurfaceVariant()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_surface_inverse), Integer.valueOf(scheme.getInverseSurface()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_surface_inverse), Integer.valueOf(scheme.getInverseOnSurface()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_surface_outline), Integer.valueOf(scheme.getOutline()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_error), Integer.valueOf(scheme.getError()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_error), Integer.valueOf(scheme.getOnError()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_error_container), Integer.valueOf(scheme.getErrorContainer()));
        map.put(Integer.valueOf(C1156R.color.material_personalized_color_on_error_container), Integer.valueOf(scheme.getOnErrorContainer()));
        return map;
    }
}
