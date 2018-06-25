package learningleaflets.com.processlib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class Misc {
    public static boolean isPackageInstalled(String package_name, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(package_name, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    public static void openGooglePlay(final String appPackageName, Context context){
        try {
            Intent appStoreIntent = new Intent(
                    Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
            appStoreIntent.setPackage("com.android.vending");
            context.startActivity(appStoreIntent);
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
