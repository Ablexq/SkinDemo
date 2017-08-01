package demo.linjw.masterapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by linjw on 2017/8/1.
 * e-mail : bluesky466@qq.com
 */

public class SkinHelper {
    private final Resources mResources;
    private final String mSkinApkPackage;

    public SkinHelper(Context context, String skinApkPackage) {
        mSkinApkPackage = skinApkPackage;
        mResources = getResourcesByContext(context, skinApkPackage);
    }

    public SkinHelper(Context context, String skinApkPackage, File skinApk) {
        mSkinApkPackage = skinApkPackage;
        mResources = getResourcesByAssetManager(context, skinApk.getPath());
    }

    /**
     * 使用Context.createPackageContext加载Resource
     *
     * @param context
     * @return
     */
    private Resources getResourcesByContext(Context context, String skinApkPackage) {
        try {
            return context.createPackageContext(skinApkPackage, Context.CONTEXT_IGNORE_SECURITY)
                    .getResources();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 使用反射创建AssertManager加载Resource
     *
     * @param context
     * @return
     */
    private Resources getResourcesByAssetManager(Context context, String skinApkPath) {
        try {
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            AssetManager assetManager = AssetManager.class.newInstance();
            method.invoke(assetManager, skinApkPath);

            return new Resources(
                    assetManager,
                    context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration()
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getString(String name) {
        int id = mResources.getIdentifier(name, "string", mSkinApkPackage);
        if (id == 0) {
            return null;
        }
        return mResources.getString(id);
    }


    public Drawable getDrawable(String name) {
        int id = mResources.getIdentifier(name, "drawable", mSkinApkPackage);
        if (id == 0) {
            return null;
        }
        return mResources.getDrawable(id);
    }
}
