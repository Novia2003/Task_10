package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.SwingUtils;

import java.util.Locale;

public class GuiMain {
    public static void main(String[] args) {
        winMain();
    }

    public static void winMain() {
        Locale.setDefault(Locale.ROOT);

        SwingUtils.setDefaultFont("Microsoft Sans Serif", 14);
        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
}
