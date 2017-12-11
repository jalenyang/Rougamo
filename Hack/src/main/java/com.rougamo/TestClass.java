package com.rougamo;


import com.nokia.oss.libs.preferences.RtePrefsFactory;

import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

public class TestClass {
    public static final String NODE_START_PAGE = "startpage";
    public static final String NODE_ADAPTATION_UI = "/startpage/applications/AdaptationUI";

    public static void main(String[] args) {
        PreferencesFactory factory = RtePrefsFactory.loadAppData(NODE_START_PAGE, false);
        Preferences desktopPref = factory.systemRoot().node(NODE_ADAPTATION_UI);
        System.out.println("Hello World..." + desktopPref);
    }
}
