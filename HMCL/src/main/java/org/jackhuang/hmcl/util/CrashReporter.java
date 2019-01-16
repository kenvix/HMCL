/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2019  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl.util;

import javafx.application.Platform;
import org.jackhuang.hmcl.Metadata;
import org.jackhuang.hmcl.ui.CrashWindow;
import org.jackhuang.hmcl.ui.construct.MessageBox;
import org.jackhuang.hmcl.util.platform.OperatingSystem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import static java.util.Collections.newSetFromMap;
import static org.jackhuang.hmcl.util.Logging.LOG;
import static org.jackhuang.hmcl.util.i18n.I18n.i18n;

/**
 * @author huangyuhui
 */
public class CrashReporter implements Thread.UncaughtExceptionHandler {

    private static final Map<String, String> SOURCE = new HashMap<String, String>() {
        {
            put("javafx.fxml.LoadException", i18n("crash.NoClassDefFound"));
            put("Location is not set", i18n("crash.NoClassDefFound"));
            put("UnsatisfiedLinkError", i18n("crash.user_fault"));
            put("java.lang.NoClassDefFoundError", i18n("crash.NoClassDefFound"));
            put("java.lang.VerifyError", i18n("crash.NoClassDefFound"));
            put("java.lang.NoSuchMethodError", i18n("crash.NoClassDefFound"));
            put("java.lang.NoSuchFieldError", i18n("crash.NoClassDefFound"));
            put("netscape.javascript.JSException", i18n("crash.NoClassDefFound"));
            put("java.lang.IncompatibleClassChangeError", i18n("crash.NoClassDefFound"));
            put("java.lang.ClassFormatError", i18n("crash.NoClassDefFound"));
            put("com.sun.javafx.css.StyleManager.findMatchingStyles", i18n("launcher.update_java"));
            put("NoSuchAlgorithmException", "Has your operating system been installed completely or is a ghost system?");
        }
    };

    private boolean checkThrowable(Throwable e) {
        String s = StringUtils.getStackTrace(e);
        for (HashMap.Entry<String, String> entry : SOURCE.entrySet())
            if (s.contains(entry.getKey())) {
                if (StringUtils.isNotBlank(entry.getValue())) {
                    String info = entry.getValue();
                    LOG.severe(info);
                    try {
                        MessageBox.show(info);
                    } catch (Throwable t) {
                        LOG.log(Level.SEVERE, "Unable to show message", t);
                    }
                }
                return false;
            }
        return true;
    }

    private static Set<String> CAUGHT_EXCEPTIONS = newSetFromMap(new ConcurrentHashMap<>());

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOG.log(Level.SEVERE, "Uncaught exception in thread " + t.getName(), e);

        try {
            String stackTrace = StringUtils.getStackTrace(e);
            if (!stackTrace.contains("org.jackhuang"))
                return;

            if (CAUGHT_EXCEPTIONS.contains(stackTrace))
                return;
            CAUGHT_EXCEPTIONS.add(stackTrace);

            String text = "---- Hello Minecraft! (for MoeCraft) Crash Report ----\n" +
                    "  Version: " + Metadata.VERSION + "\n" +
                    "  Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n" +
                    "  Thread: " + t.toString() + "\n" +
                    "\n  Content: \n    " +
                    stackTrace + "\n\n" +
                    "-- System Details --\n" +
                    "  Operating System: " + System.getProperty("os.name") + ' ' + OperatingSystem.SYSTEM_VERSION + "\n" +
                    "  Java Version: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + "\n" +
                    "  Java VM Version: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor") + "\n";

            LOG.log(Level.SEVERE, text);

            if (checkThrowable(e))
                Platform.runLater(() -> new CrashWindow(text).show());
        } catch (Throwable handlingException) {
            LOG.log(Level.SEVERE, "Unable to handle uncaught exception", handlingException);
        }
    }
}
