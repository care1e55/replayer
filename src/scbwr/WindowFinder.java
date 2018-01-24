package scbwr;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

public class WindowFinder {
public interface User32 extends StdCallLibrary {
    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
    boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
    WinDef.HWND SetFocus(WinDef.HWND hWnd);
    int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
    boolean SetForegroundWindow(WinDef.HWND hWnd);
}

public static void findWindow() {
    final User32 user32 = User32.INSTANCE;
    user32.EnumWindows(new WNDENUMPROC() {
        int count = 0;
        public boolean callback(HWND hWnd, Pointer arg1) {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText);
            if (wText.isEmpty()) {
                return true;
            }
            System.out.println("Found window with text " + hWnd
                    + ", total " + ++count + " Text: " + wText);
            if (wText
                    .equals("Brood War")) {
                user32.SetForegroundWindow(hWnd);
                return false;
            }
            return true;
        }
    }, null);
}
}
