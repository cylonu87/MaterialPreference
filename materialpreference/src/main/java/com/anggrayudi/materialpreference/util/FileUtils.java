package com.anggrayudi.materialpreference.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import com.anggrayudi.materialpreference.R;

public final class FileUtils {
    public static final int REQUEST_CODE_STORAGE_GET_FOLDER = 11;
    public static final int REQUEST_CODE_REQUIRE_SDCARD_ROOT_PATH_PERMISSIONS = 14;

    public static String resolvePathFromUri(Intent data) {
        String decodedPath = data.getData().getPath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            switch (data.getData().getAuthority()) {
                case "com.android.externalstorage.documents":
                    String withoutUri = TextUtil.getStringBetween(decodedPath, '/', ':')
                            + decodedPath.substring(decodedPath.indexOf(':'), decodedPath.length());
                    String sdcardId = withoutUri.substring(0, withoutUri.indexOf(':') + 1);
                    String subFolder = withoutUri.substring(withoutUri.indexOf(':') + 1, withoutUri.length());
                    if (sdcardId.equals("primary:")) {
                        sdcardId = SaveDir.EXTERNAL + "/";
                    }
                    return sdcardId + subFolder;

                case "com.android.providers.downloads.documents":
                case "com.android.providers.media.documents":
                case "com.google.android.apps.docs.storage":
            }
        } else if ("file".equals(data.getData().getScheme())) {
            return data.getData().getPath();
        }
        return SaveDir.EXTERNAL;
    }

    public static boolean saveUriPermission(Context context, Intent data) {
        String path = data.getData().getPath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && path.startsWith("/tree/")
                && !path.startsWith("/tree/primary")) {
            Uri root = Uri.parse("content://com.android.externalstorage.documents/tree/" +
                    TextUtil.getStringBetween(data.getData().getPath(), '/', ':') + "%3A");
            if (data.getData().equals(root)) {
                try {
                    int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                    context.getContentResolver().takePersistableUriPermission(root, takeFlags);
                    return true;
                } catch (SecurityException e) {
                    Toast.makeText(context, R.string.please_grant_storage_permission, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, R.string.not_root_path, Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isSdCardUriPermissionsGranted(Context context, Intent data) {
        Uri root = Uri.parse("content://com.android.externalstorage.documents/tree/" +
                TextUtil.getStringBetween(data.getData().getPath(), '/', ':') + "%3A");
        for (UriPermission permission : context.getContentResolver().getPersistedUriPermissions()) {
            if (permission.isReadPermission() && permission.isWritePermission() &&
                    permission.getUri().equals(root))
                return true;
        }
        if (root.equals(data.getData())) {
            try {
                int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                context.getContentResolver().takePersistableUriPermission(root, takeFlags);
                return true;
            } catch (SecurityException ignore) {
            }
        }
        return false;
    }

}
