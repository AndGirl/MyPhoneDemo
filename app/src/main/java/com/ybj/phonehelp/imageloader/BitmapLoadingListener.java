package com.ybj.phonehelp.imageloader;

import android.graphics.Bitmap;

/**
 * @author Ivan
 */

public interface BitmapLoadingListener {

    void onSuccess(Bitmap b);

    void onError();
}
