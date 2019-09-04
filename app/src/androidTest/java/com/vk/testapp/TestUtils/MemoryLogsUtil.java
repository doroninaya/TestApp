package com.vk.testapp.TestUtils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static android.os.Environment.getExternalStorageDirectory;

public class MemoryLogsUtil {
    public static final String LOG_TAG = "VK_TEST";

    public static final long MAX_PEAK_PSS_ALLOWED = 200000; //Around max memory for Vk app

    private static final String FOLDER_NAME_FOR_MEMORY_LOGS = "app_memory_logs";

    public static void getMemoryLogs(String fullOutput, long memory) {
        String logFileName = Long.toString(memory);

        //Create folder if needed
        File folder = new File(getExternalStorageDirectory(), FOLDER_NAME_FOR_MEMORY_LOGS);
        if (!folder.exists()) {
            Log.i(LOG_TAG, "Create folder: " + FOLDER_NAME_FOR_MEMORY_LOGS);
            folder.mkdirs();
        }

        //Write logs to the file
        try {
            File logFile = new File(getExternalStorageDirectory() + "/" + FOLDER_NAME_FOR_MEMORY_LOGS
                    + "/memory_is_" + logFileName + ".txt");
            logFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(logFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(fullOutput);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            Log.i(LOG_TAG, "Failed write logs to the file");
        }
    }
}
