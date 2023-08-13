package com.kakao_szbot.lib;

import static com.kakao_szbot.MainActivity.getAppContext;
import static com.kakao_szbot.MainActivity.getAppPackageName;
import static com.kakao_szbot.MainActivity.getAppResources;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class FileLibrary {
    public final static String TAG = "LibraryCSV";


    public void testWriteCSV(Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            String line = "Column 1, Column 2, Column 3\n";
            osw.write(line);

            line = "Value 1, Value 2, Value 3\n";
            osw.write(line);

            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testReadCSV(Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Do something with the
                for (int i = 0; i < data.length; i++) {
                    Log.d(TAG, data[i]);
                }
            }

            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChatWriteCSV(String fileName, String title, String text) {
        Context context = getAppContext();

        try {
            Date currentDate = new Date();
            SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat chatDateFormat = new SimpleDateFormat("HH:mm:ss");

            fileName += "_" + fileDateFormat.format(currentDate);

            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            String line = chatDateFormat.format(currentDate) + "," + title + "," + text + "\n";
            osw.write(line);

            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteCSV(String fileName, String cmd, String msg) {
        Context context = getAppContext();

        try {
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            String line = cmd + "," + msg + "\n";
            osw.write(line);

            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadCSV(String fileName) {
        Context context = getAppContext();
        String result = null;

        try {
            File file = new File(context.getFilesDir(), fileName);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                if (result == null)
                    result = line + "\n";
                else
                    result += line + "\n";
            }

            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void deleteLineCSV(String fileName, String cmd) {
        Context context = getAppContext();

        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists()) {
                return;
            }
            Scanner scanner = new Scanner(file);
            StringBuilder modifiedData = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values[0].equals(cmd)) {
                    // Modify the value if it meets the criteria
                    continue;
                }
                // Append the modified data to the StringBuilder
                modifiedData.append(String.join(",", values));
                modifiedData.append("\n");
            }
            scanner.close();

            // Write the modified data to a new file or overwrite the original file
            FileWriter writer = new FileWriter(file);
            writer.write(modifiedData.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePointCSV(String fileName, String sender, int point) {
        Context context = getAppContext();

        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists()) {
                WriteCSV(fileName, sender, String.valueOf(point));
                return;
            }
            Scanner scanner = new Scanner(file);
            StringBuilder modifiedData = new StringBuilder();
            int foundSender = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values[0].equals(sender)) {
                    // Modify the value if it meets the criteria
                    values[1] = String.valueOf(point);
                    foundSender = 1;
                }
                // Append the modified data to the StringBuilder
                modifiedData.append(String.join(",", values));
                modifiedData.append("\n");
            }
            scanner.close();

            // Write the modified data to a new file or overwrite the original file
            FileWriter writer = new FileWriter(file);
            writer.write(modifiedData.toString());
            writer.close();

            if (foundSender == 0) {
                WriteCSV(fileName, sender, String.valueOf(point));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteSurvivalCSV(String fileName, String player, String survant, int health, int rock, int paper, int scissors) {
        Context context = getAppContext();

        try {
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            String line = player + "," +
                    survant + "," +
                    String.valueOf(health) + "," +
                    String.valueOf(rock) + "," +
                    String.valueOf(paper) + "," +
                    String.valueOf(scissors) + "," +
                    "null,null,null\n";
            osw.write(line);

            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadResCSV(String fileName) throws IOException {
        // Get a reference to the application's resources
        Resources res = getAppResources();

        // Get the ID of the raw resource file
        int resourceId = res.getIdentifier(fileName, "raw", getAppPackageName());

        Log.d(TAG, "fileName: " + fileName +
                ", getAppPackageName: " + getAppPackageName() +
                ", resourceId: " + resourceId);

        // Open the raw resource file as an input stream
        InputStream inputStream = res.openRawResource(resourceId);

        // Read the contents of the file
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        String fileContents = new String(buffer);

        // Close the input stream
        inputStream.close();
        return fileContents;
    }

    public String ReadAssetsCSV(String fileName) throws IOException {
        AssetManager assetManager = getAppResources().getAssets();
        InputStream inputStream = assetManager.open(fileName);

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        String fileContents = new String(buffer);

        // Close the input stream
        inputStream.close();

        return fileContents;
    }
}
