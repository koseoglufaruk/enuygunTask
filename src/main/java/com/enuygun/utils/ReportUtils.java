package com.enuygun.utils;

import com.enuygun.exceptions.FrameworkException;
import com.enuygun.exceptions.InvalidPathForExtentReportFileException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.enuygun.constants.FrameworkConstants.EXTENT_REPORT_FILE_NAME;
import static com.enuygun.constants.FrameworkConstants.EXTENT_REPORT_FOLDER_PATH;
import static com.enuygun.constants.FrameworkConstants.NO;
import static com.enuygun.constants.FrameworkConstants.OPEN_REPORTS_AFTER_EXECUTION;
import static com.enuygun.constants.FrameworkConstants.OVERRIDE_REPORTS;
import static com.enuygun.constants.FrameworkConstants.YES;

public class ReportUtils {

    private ReportUtils() {

    }

    public static String createExtentReportPath() {
        String link = "";
        if (OVERRIDE_REPORTS.trim().equals(NO)) {
            System.out.println("OVERRIDE_REPORTS = " + OVERRIDE_REPORTS);
            link = EXTENT_REPORT_FOLDER_PATH + File.separator + DateUtils.getCurrentDate() + "_"
                    + EXTENT_REPORT_FILE_NAME;
            System.out.println("Created link report: " + link);
            return link;
        } else {
            System.out.println("OVERRIDE_REPORTS = " + OVERRIDE_REPORTS);
            link = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;
            System.out.println("Created link report: " + link);
            return link;
        }
    }

    public static void openReports(String linkReport) {
        if (OPEN_REPORTS_AFTER_EXECUTION.trim().equalsIgnoreCase(YES)) {
            try {
                Desktop.getDesktop().browse(new File(linkReport).toURI());
            } catch (FileNotFoundException fileNotFoundException) {
                throw new InvalidPathForExtentReportFileException("Extent Report file you are trying to reach is not found", fileNotFoundException.fillInStackTrace());
            } catch (IOException ioException) {
                throw new FrameworkException("Extent Report file you are trying to reach got IOException while reading the file", ioException.fillInStackTrace());
            }
        }
    }
}