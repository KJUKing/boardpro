package kr.or.ddit.board.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class StreamData {

    public static  String dataChange(HttpServletRequest request) {

        StringBuffer strbuf = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                strbuf.append(line);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        // String reqdata =  strbuf.toString();
        String reqdata =  String.valueOf(strbuf);

        System.out.println("reqdata : " + reqdata);


        return reqdata;
    }
}
