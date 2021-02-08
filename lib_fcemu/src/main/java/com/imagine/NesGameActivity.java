package com.imagine;

import android.app.Activity;
import android.app.NativeActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.yangtzeu.official.nes.R;

public class NesGameActivity extends NativeActivity implements OnAudioFocusChangeListener {

    private final static int GAME_NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle bundle) {
        Window window = getWindow();
        super.onCreate(bundle);

        window.setBackgroundDrawable(null);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //支持ARGB模式
        window.setFormat(PixelFormat.RGB_888);

        View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        // NesView
        NesView nesView = new NesView(this);
        // 载入视图
        setContentView(nesView);
        // 获得焦点
        nesView.requestFocus();
    }

    @Override
    protected void onResume() {
        removeNotification();
        super.onResume();
    }

    @Override
    public void onAudioFocusChange(int i) {
        //Native 调用，不能删除
    }

    @Override
    public void onGlobalLayout() {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean bt_on = true;
        if (requestCode == 1) {
            if (resultCode != Activity.RESULT_OK) {
                bt_on = false;
            }
            onBTOn(bt_on);
        }
    }

    @Override
    protected void onDestroy() {
        removeNotification();
        super.onDestroy();
    }

    public static native void onBTOn(boolean bt_on);

    public static native void onBTScanStatus(int i);

    public static native void onContentRectChanged(long j, int left, int top, int right, int bottom, int width, int height);

    public static native boolean onScanDeviceClass(int i);

    public static native void onScanDeviceName(String str, String str2);

    public static native void sysTextInputEnded(String input_str, boolean goBack, boolean is_input);

    public String libDir() {
        try {
            ComponentName component = getIntent().getComponent();
            if (component != null) {
                return getPackageManager().getActivityInfo(component, PackageManager.GET_META_DATA).applicationInfo.nativeLibraryDir;
            }
            throw new RuntimeException("Error getting activity info");
        } catch (Exception e) {
            throw new RuntimeException("Error getting activity info", e);
        }
    }

    /**
     * 获取So的路径
     *
     * @return mainSOPath
     */
    public String mainSOPath() {
        LogUtils.e(libDir(), libDir() + "/" + System.mapLibraryName("main"));
        return libDir() + "/" + System.mapLibraryName("main");
    }


    /**
     * 设置界面相关参数
     *
     * @param flag flag
     */
    public void setUIVisibility(int flag) {
        getWindow().getDecorView().setSystemUiVisibility(flag | View.SYSTEM_UI_LAYOUT_FLAGS);
    }


    /**
     * 向模拟器输入相关
     */
    public void startSysTextInput(String str, String str2, int x, int y, int input_view_width, int input_view_height, int i5) {
        InputDialogUtils.showInputDialog(this, str, str2, x, y, input_view_width, input_view_height, i5);
    }


    public void finishSysTextInput(boolean b) {
        InputDialogUtils.dismissInputDialog(b);
    }

    public void placeSysTextInput(int x, int y, int input_view_width, int input_view_height) {
        InputDialogUtils.placeSysTextInput(x, y, input_view_width, input_view_height);
    }

    public static void endSysTextInput(String str, boolean is_input) {
        sysTextInputEnded(str, true, is_input);
    }


    /**
     * 获取设备名称
     *
     * @return devName
     */
    public static String devName() {
        return Build.DEVICE;
    }


    /**
     * 获取内部存储路径
     *
     * @return extStorageDir
     */
    public static String extStorageDir() {
        return PathUtils.getExternalStoragePath();
    }


    public static boolean gbAnimatesRotation() {
        return Build.DISPLAY.contains("cyano");
    }


    /**
     * 添加桌面图标
     * 不可更改方法名，Native写死
     *
     * @param name 游戏名字
     * @param path 游戏路径
     */
    public void addViewShortcut(String name, String path) {
        Intent intent = new Intent(this, NesGameActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("file://" + path));

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            ShortcutInfoCompat info = new ShortcutInfoCompat.Builder(this, "nes_launcher_" + name)
                    .setIcon(IconCompat.createWithResource(this, R.drawable.icon_game))
                    .setShortLabel(name)
                    .setIntent(intent)
                    .build();

            ShortcutManagerCompat.requestPinShortcut(this, info, null);
            LogUtils.e("ShortcutManagerCompat");
        } else {
            Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            // 快捷方式的名字
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
            // 快捷方式的bitmap尽可能小，因为广播内容超过2MB会抛出异常
            Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.drawable.icon_game);
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconRes);
            // 设置是否允许重复创建快捷方式，该选项非必填，默认是允许
            shortcutIntent.putExtra("duplicate", false);
            shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
            this.sendBroadcast(shortcutIntent);
        }
    }


    /**
     * 获取音频管理器
     * 不可更改方法名，Native写死
     *
     * @return AudioManager
     */
    public AudioManager audioManager() {
        return (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 获取Assets文件夹下的图片资源
     *
     * @param str 资源名称
     * @return Bitmap
     */
    public Bitmap bitmapDecodeAsset(String str) {
        try {
            return BitmapFactory.decodeStream(getAssets().open("game/" + str));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取默认显示设备，Android屏幕为默认显示
     *
     * @return Display
     */
    public Display defaultDpy() {
        return getWindowManager().getDefaultDisplay();
    }

    public DisplayListenerHelper displayListenerHelper() {
        return new DisplayListenerHelper(this);
    }

    /**
     * DisplayMetrics
     * 是Android提供的记述屏幕的有关信息的一种结构，诸如其尺寸，密度和字体缩放的一般信息。
     *
     * @return DisplayMetrics
     */
    public DisplayMetrics displayMetrics() {
        return getResources().getDisplayMetrics();
    }

    /**
     * 其他设备
     */
    public DisplayMetrics getDisplayMetrics(Display display) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }


    /**
     * 获取私有目录
     *
     * @return filesDir
     */
    public String filesDir() {
        return getFilesDir().getAbsolutePath();
    }

    /**
     * 是否支持音频延时
     *
     * @return boolean
     */
    public boolean hasLowLatencyAudio() {
        //指示 45 毫秒或更短的持续输出延迟时间。
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUDIO_LOW_LATENCY);
    }

    /**
     * 检测是否有菜单操作
     * 有就不在游戏屏幕上面绘制菜单键，反之亦然（默认返回false,不管有没有都绘制）
     *
     * @return false
     */
    public boolean hasPermanentMenuKey() {
        return false;
    }

    /**
     * 输入设备相关
     *
     * @return InputDeviceListenerHelper
     */
    public InputDeviceListenerHelper inputDeviceListenerHelper() {
        return new InputDeviceListenerHelper(this);
    }

    /**
     * 获取启动时，附带的游戏路径
     * 不为空直接打开游戏，反之打开菜单
     *
     * @return 游戏路径
     */
    public String intentDataPath() {
        Uri data = getIntent().getData();
        if (data == null) {
            return null;
        }
        //获取游戏文件路径
        String path = data.getPath();
        getIntent().setData(null);
        return path;
    }

    /**
     * 传入游戏路径，直接启动游戏
     *
     * @param context  context
     * @param gamePath gamePath
     */
    public static void startGame(Context context, String gamePath) {
        Intent intent = new Intent(context, NesGameActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("file://" + gamePath));
        context.startActivity(intent);
    }

    /**
     * makeBitmap
     *
     * @return Bitmap
     */
    public Bitmap makeBitmap(int width, int height, int format) {
        Config config = Config.ARGB_8888;
        if (format == PixelFormat.RGB_565) {
            config = Config.RGB_565;
        }
        return Bitmap.createBitmap(width, height, config);
    }

    /**
     * MOGAHelper 手柄驱动
     */
    public MOGAHelper mogaHelper() {
        return new MOGAHelper(this);
    }

    /**
     * 帧率相关
     */
    public ChoreographerHelper newChoreographerHelper() {
        return new ChoreographerHelper();
    }

    /**
     * 字体渲染类
     */
    public FontRenderer newFontRenderer() {
        return new FontRenderer();
    }

    /**
     * IdleHelper
     */
    public IdleHelper newIdleHelper() {
        return new IdleHelper();
    }


    /**
     * 检测某个包名的应用是否安装
     *
     * @param pack_name pack_name
     * @return boolean
     */
    public boolean packageIsInstalled(String pack_name) {
        try {
            getPackageManager().getPackageInfo(pack_name, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 辅助屏幕，有外显设备时，输出画面到外显设备
     */
    public PresentationHelper presentation(Display display, long j) {
        return new PresentationHelper(this, display, j);
    }

    /**
     * 创建游戏相关的通知
     */
    public void addNotification(String str, String str2, String str3) {
        if (str.contains("NES.emu was suspended"))
            str = str.replace("NES.emu was suspended", "游戏已暂停");
        str = "新长大助手：" + str;
        if (StringUtils.isEmpty(str3))
            str3 = "点击回到游戏界面";
        else
            str3 = "游戏名称：" + str3;


        final String finalStr = str3;
        final String finalStr1 = str;
        NotificationUtils.notify("新长大助手—游戏", GAME_NOTIFICATION_ID, new Utils.Consumer<NotificationCompat.Builder>() {
            @Override
            public void accept(NotificationCompat.Builder builder) {
                builder.setContentText(finalStr);
                builder.setContentTitle(finalStr1);
                builder.build();
            }
        });
    }

    /**
     * 移除游戏相关的通知
     */
    public void removeNotification() {
        NotificationUtils.cancel(GAME_NOTIFICATION_ID);
    }

    /**
     * 获取权限
     *
     * @param permission permission
     * @return 用户授权结果
     */
    public boolean requestPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        if (PermissionUtils.isGranted(permission)) {
            return true;
        } else {
            PermissionUtils.permission(permission);
            return false;
        }
    }

    /**
     * 获取应用签名的hashCode
     *
     * @return 应用签名hashCode
     */
    public int sigHash() {
        Signature[] appSignature = AppUtils.getAppSignatures();
        return appSignature[0].hashCode();
    }

    /**
     * 获取震动管理类
     *
     * @return Vibrator
     */
    public Vibrator systemVibrator() {
        return (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 想你活动
     * UserActivityFaker
     */
    public UserActivityFaker userActivityFaker() {
        return new UserActivityFaker(this);
    }


    /**
     * 以下为窗口相关配置
     */
    public void setWinFlags(int flag, int mask) {
        getWindow().setFlags(flag, mask);
    }

    public void setWinFormat(int format) {
        getWindow().setFormat(format);
    }

    public int winFlags() {
        return getWindow().getAttributes().flags;
    }

    public int winFormat() {
        return getWindow().getAttributes().format;
    }

    /**
     * 游戏截屏
     *
     * @param bitmap bitmap
     * @param path   保存路径
     * @return 是否导出成功
     */
    public boolean writePNG(Bitmap bitmap, String path) {
        String fileName = FileUtils.getFileName(path);
        path = PathUtils.getExternalAppFilesPath() + "/yangtzeu/fc/" + fileName;
        boolean save = ImageUtils.save(bitmap, path, CompressFormat.PNG, true);
        ToastUtils.showLong("截屏保存位置：\n" + path);
        return save;
    }

    /**
     * 以下是蓝牙相关
     *
     * @param bluetoothAdapter bluetoothAdapter
     */
    public void btCancelScan(BluetoothAdapter bluetoothAdapter) {
        BluetoothClass.b(this, bluetoothAdapter);
    }

    public BluetoothAdapter btDefaultAdapter() {
        return BluetoothClass.getInstance();
    }

    public BluetoothSocket btOpenSocket(BluetoothAdapter bluetoothAdapter, String str, int i, boolean z) {
        return BluetoothClass.getInstance(bluetoothAdapter, str, i, z);
    }

    public int btStartScan(BluetoothAdapter bluetoothAdapter) {
        return BluetoothClass.getInstance(this, bluetoothAdapter) ? 1 : 0;
    }

    public int btState(BluetoothAdapter bluetoothAdapter) {
        return bluetoothAdapter.getState();
    }

    public void btTurnOn() {
        startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
    }

}
