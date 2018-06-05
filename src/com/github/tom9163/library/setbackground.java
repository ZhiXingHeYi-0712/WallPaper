package com.github.tom9163.library;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import com.sun.jna.win32.StdCallLibrary;

public class setbackground {
    public static final String OUTPUT_PATH = "C:\\Users\\tom_9.DESKTOP-31N42JU\\Documents\\adb.png";
    public static void set(){

        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
                "Control Panel\\Desktop", "Wallpaper", OUTPUT_PATH);
        //WallpaperStyle = 10 (Fill), 6 (Fit), 2 (Stretch), 0 (Tile), 0 (Center)
        //For windows XP, change to 0
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
                "Control Panel\\Desktop", "WallpaperStyle", "10"); //fill
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER,
                "Control Panel\\Desktop", "TileWallpaper", "0");   // no tiling

        // refresh the desktop using User32.SystemParametersInfo(), so avoiding an OS reboot
        int SPI_SETDESKWALLPAPER = 0x14;
        int SPIF_UPDATEINIFILE = 0x01;
        int SPIF_SENDWININICHANGE = 0x02;

        // User32.System
        boolean result = MyUser32.INSTANCE.SystemParametersInfoA(SPI_SETDESKWALLPAPER, 0,
                OUTPUT_PATH, SPIF_UPDATEINIFILE | SPIF_SENDWININICHANGE );
    }

    private interface MyUser32 extends StdCallLibrary {

        MyUser32 INSTANCE = (MyUser32) Native.loadLibrary("user32", MyUser32.class);
        boolean SystemParametersInfoA(int uiAction, int uiParam, String fnm, int fWinIni);
    }
}
