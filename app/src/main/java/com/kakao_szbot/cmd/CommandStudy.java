package com.kakao_szbot.cmd;


import android.util.Log;

import com.kakao_szbot.lib.FileLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandStudy {
    public final static String TAG = "CommandStudy";

    public static List<String> STUDY_CMD_LIST = new ArrayList<String>();
    public static List<String> STUDY_MSG_LIST = new ArrayList<String>();


    public String studyMessage(String msg) {
        String result = null;

        int first_msg_start_index = msg.indexOf('[') + 1;
        int first_msg_end_index = msg.indexOf(']');
        int second_msg_start_index = msg.lastIndexOf('[') + 1;
        int second_msg_end_index = msg.lastIndexOf(']');

        if ((first_msg_start_index != -1 && first_msg_end_index != -1 &&
            second_msg_start_index != -1 && second_msg_end_index != -1) &&
            (first_msg_start_index < first_msg_end_index &&
            first_msg_end_index < second_msg_start_index &&
            second_msg_start_index < second_msg_end_index)) {

            String study_cmd = msg.substring(first_msg_start_index, first_msg_end_index);
            String study_msg = msg.substring(second_msg_start_index, second_msg_end_index);

            if (Collections.frequency(STUDY_CMD_LIST, study_cmd) == 0) {
                FileLibrary csv = new FileLibrary();
                csv.WriteCSV("studyCommand.csv", study_cmd, study_msg);

                STUDY_CMD_LIST.add(study_cmd);
                STUDY_MSG_LIST.add(study_msg);

                result = "명심하겠습니다";
            } else {
                result = "이미 공부한 명령어에요";
            }
        }
        else {
            result = "다시 말해주세요 (공부하기 [배울 문장] [응답 문장])";
        }

        return result;
    }

    public String forgotStudyMessage(String msg) {
        String result = null;

        int first_msg_start_index = msg.indexOf('[') + 1;
        int first_msg_end_index = msg.indexOf(']');
        if ((first_msg_start_index != -1 && first_msg_end_index != -1) &&
                (first_msg_start_index < first_msg_end_index)) {

            String study_cmd = msg.substring(first_msg_start_index, first_msg_end_index);

            if (Collections.frequency(STUDY_CMD_LIST, study_cmd) == 0) {
                result = "공부한적 없는 명령어에요";
            } else {
                FileLibrary csv = new FileLibrary();
                csv.deleteLineCSV("studyCommand.csv", study_cmd);

                int index = STUDY_CMD_LIST.indexOf(study_cmd);
                STUDY_CMD_LIST.remove(index);
                STUDY_MSG_LIST.remove(index);

                result = "잊어버렸습니다";
            }

        } else {
            result = "다시 말해주세요 (잊어버려 [잊을 문장])";
        }

        return result;
    }

    public String getStudyMessage() {
        String result = null;

        if (STUDY_CMD_LIST.size() == 0) {
            result = "공부목록이 텅 비었습니다";
            return result;
        }

        result = "공부한 목록은 아래와 같습니다\n";
        for (int i = 0; i < STUDY_CMD_LIST.size(); i++) {
            result += "\n - " + STUDY_CMD_LIST.get(i);
            result += " : " + STUDY_MSG_LIST.get(i);
        }

        return result;
    }

    public void loadStudyMessage() {
        FileLibrary csv = new FileLibrary();
        String allData = csv.ReadCSV("studyCommand.csv");
        if (allData == null)
            return;

        String[] parts = allData.split("\n");
        for (String part : parts) {
            String[] data = part.split(",");

            STUDY_CMD_LIST.add(data[0]);
            STUDY_MSG_LIST.add(data[1]);
        }
    }

    public String checkStudyMessage(String msg) {
        for (int i = 0; i < STUDY_CMD_LIST.size(); i++) {
            if (msg.indexOf(STUDY_CMD_LIST.get(i)) != -1) {
                return STUDY_MSG_LIST.get(i);
            }
        }

        return null;
    }
}
