package com.faceunity.core.callback;

import com.faceunity.core.enumeration.PosterFaceEnum;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J.\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\r\u0018\u0001`\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007H&¨\u0006\u0011"}, m1293d2 = {"Lcom/faceunity/core/callback/OnPosterRenderCallback;", "", "onMergeResult", "", "isSuccess", "", "texId", "", "onPhotoLoaded", "posterFaceEnum", "Lcom/faceunity/core/enumeration/PosterFaceEnum;", "array", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "onTemplateLoaded", "trackFace", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface OnPosterRenderCallback {
    void onMergeResult(boolean isSuccess, int texId);

    void onPhotoLoaded(PosterFaceEnum posterFaceEnum, ArrayList<float[]> array);

    void onTemplateLoaded(int trackFace);

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void onPhotoLoaded$default(OnPosterRenderCallback onPosterRenderCallback, PosterFaceEnum posterFaceEnum, ArrayList arrayList, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPhotoLoaded");
            }
            if ((i & 2) != 0) {
                arrayList = null;
            }
            onPosterRenderCallback.onPhotoLoaded(posterFaceEnum, arrayList);
        }
    }
}
