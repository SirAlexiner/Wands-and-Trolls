package io.github.mimoguz.customwindow;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;

/**
 * A small collection of utility methods to customize a window.
 * Target Windows 11+ won't show any effect on unsupported OSes.
 */
@UtilityClass
public class StageOps {
  /**
   * A wrapper for HWND type.
   */
  private static class WindowHandle {
    private final WinDef.HWND value;

    private WindowHandle(WinDef.HWND hwnd) {
      value = hwnd;
    }
  }

  private interface DwmSupport extends Library {
    DwmSupport INSTANCE = Native.load("dwmapi", DwmSupport.class);
    @SuppressWarnings({"checkstyle:EmptyLineSeparator", "checkstyle:MethodName"})
    WinNT.HRESULT DwmSetWindowAttribute(
        WinDef.HWND hwnd,
        int dwAttribute,
        PointerType pvAttribute,
        int cbAttribute
    );
  }

  /**
   * A wrapper for DwmSetWindowAttribute.
   *
   * @param handle    WindowHandle for the window. Can be obtained by using findWindowHandle method.
   *                  Can be null.
   * @param attribute dwAttribute
   * @param value     pvAttribute
   */
  private static void dwmSetIntValue(final WindowHandle handle, final DwmAttribute attribute,
                                     final int value) {
    if (handle == null) {
      return;
    }
    isOk(
        DwmSupport.INSTANCE.DwmSetWindowAttribute(
            handle.value,
            attribute.value,
            new WinDef.DWORDByReference(new WinDef.DWORD(value)),
            WinDef.DWORD.SIZE
        )
    );
  }

  /**
   * Try to find the window handle.
   *
   * @param stage JavaFX Stage to search.
   * @return WindowHandle if it can find, null otherwise.
   */
  public static WindowHandle findWindowHandle(final Stage stage) {
    if (Platform.getOSType() != Platform.WINDOWS) {
      return null;
    }
    final var searchString = "stage_" + java.util.UUID.randomUUID();
    final var title = stage.getTitle();
    stage.setTitle(searchString);
    final var hwnd = User32.INSTANCE.FindWindow(null, searchString);
    stage.setTitle(title);
    if (hwnd != null) {
      return new WindowHandle(hwnd);
    }
    return null;
  }

  /**
   * Sets the border color of a window.
   *
   * @param handle WindowHandle for the window. Can be obtained by using findWindowHandle method.
   *               Can be null.
   * @param color  Border color
   */
  public static void setBorderColor(final WindowHandle handle, final Color color) {
    dwmSetIntValue(handle, DwmAttribute.DWMWA_BORDER_COLOR, rgb(color));
  }

  /**
   * Sets the title bar background color of a window.
   *
   * @param handle WindowHandle for the window. Can be obtained by using findWindowHandle method.
   *               Can be null.
   * @param color  Caption color
   */
  public static void setCaptionColor(final WindowHandle handle, final Color color) {
    dwmSetIntValue(handle, DwmAttribute.DWMWA_CAPTION_COLOR, rgb(color));
  }

  private static int floatingTo8Bit(final double n) {
    return (int) Math.min(255.0, Math.max(n * 255.0, 0.0));
  }

  private static void isOk(final WinNT.HRESULT result) {
    IntegerType.compare(result, WinError.S_OK);
  }

  private static int rgb(final Color color) {
    return (floatingTo8Bit(color.getBlue()) << 16)
        | (floatingTo8Bit(color.getGreen()) << 8)
        | floatingTo8Bit(color.getRed());
  }
}
/*