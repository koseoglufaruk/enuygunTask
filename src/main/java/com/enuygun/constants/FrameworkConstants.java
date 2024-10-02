package com.enuygun.constants;

import com.enuygun.helpers.PropertiesHelpers;
import com.enuygun.helpers.SystemHelpers;
import com.enuygun.utils.ReportUtils;

import java.io.File;

public final class FrameworkConstants {

    public static final String URL = "https://www.enuygun.com";
    public static final String HOMEPAGETITLE = "ENUYGUN Türkiye'nin Seyahat Sitesi - Uçak Bileti, Otobüs, Otel";
    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String BROWSER = PropertiesHelpers.getValue("BROWSER");
    public static final String REMOTE_URL = PropertiesHelpers.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelpers.getValue("REMOTE_PORT");
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static final String TARGET = PropertiesHelpers.getValue("TARGET");
    public static final String HEADLESS = PropertiesHelpers.getValue("HEADLESS");
    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public static final String SCREENSHOT_PASSED_TCS = PropertiesHelpers.getValue("SCREENSHOT_PASSED_TCS");
    public static final String SCREENSHOT_FAILED_TCS = PropertiesHelpers.getValue("SCREENSHOT_FAILED_TCS");
    public static final String SCREENSHOT_SKIPPED_TCS = PropertiesHelpers.getValue("SCREENSHOT_SKIPPED_TCS");
    public static final String SCREENSHOT_ALL_STEPS = PropertiesHelpers.getValue("SCREENSHOT_ALL_STEPS");
    public static final String ZIP_FOLDER = PropertiesHelpers.getValue("ZIP_FOLDER");
    public static final String ZIP_FOLDER_PATH = PropertiesHelpers.getValue("ZIP_FOLDER_PATH");
    public static final String ZIP_FOLDER_NAME = PropertiesHelpers.getValue("ZIP_FOLDER_NAME");
    public static final String VIDEO_RECORD = PropertiesHelpers.getValue("VIDEO_RECORD");
    public static final String RETRY_TEST_FAIL = PropertiesHelpers.getValue("RETRY_TEST_FAIL");
    public static final int WAIT_DEFAULT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_DEFAULT"));
    public static final int WAIT_IMPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelpers.getValue("WAIT_PAGE_LOADED"));
    public static final int WAIT_SLEEP_STEP = Integer.parseInt(PropertiesHelpers.getValue("WAIT_SLEEP_STEP"));
    public static final String ACTIVE_PAGE_LOADED = PropertiesHelpers.getValue("ACTIVE_PAGE_LOADED");
    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
    public static String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;
    //Zip file for Report folder
    public static final String ZIPPED_EXTENT_REPORTS_FOLDER = EXTENT_REPORT_FOLDER + ".zip";
    public static final String YES = "yes";
    public static final String NO = "no";

    /* ICONS - START */
    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
    public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";

    static {
        PropertiesHelpers.loadAllFiles();
    }
    private FrameworkConstants() {
    }

    public static String getExtentReportFilePath() {
        if (EXTENT_REPORT_FILE_PATH.isEmpty()) {
            EXTENT_REPORT_FILE_PATH = ReportUtils.createExtentReportPath();
        }
        return EXTENT_REPORT_FILE_PATH;
    }

}
