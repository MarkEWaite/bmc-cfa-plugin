package com.bmc.ims;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;

public class CsvFile {

    public static JSONArray readCsvFile(String csvFilePath) {

        BufferedReader br = null;
        File file = new File(csvFilePath);
        //List<ReportView> report = new Vector<ReportView>();

        JSONArray reportJsonArrayObj = new JSONArray();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
           // br = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = br.readLine()) != null) {
                String values[] = line.split(",");
                reportJsonArrayObj.put(createJsonModel(values,file.getName()));


            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return reportJsonArrayObj;

    }
    private static JSONObject createJsonModel(String values[], String rptType)
    {
        JSONObject obj = new JSONObject();
        obj.put("jobName",values[0]);
        if(rptType.contains("DB2"))
        {
            obj.put("planName", values[1]);
            obj.put("startTime", values[2]);
            obj.put("commits#", values[3]);
            //#4 is blank
            obj.put("jobDuration", values[5]);
            obj.put("freqPerMin", values[6]);
            obj.put("freqPerSec",values[7]);
            obj.put("exceptions", values[8]);
        }
        else if(rptType.contains("IMS") || rptType.contains("DLI") )
        {
            obj.put("psbName", values[1]);
            obj.put("startTime", values[2]);
            obj.put("chkpt#", values[3]);
            obj.put("chkptType", values[4]);
            obj.put("jobDuration", values[5]);
            obj.put("freqPerMin", values[6]);
            obj.put("freqPerSec",values[7]);
            obj.put("exceptions", values[8]);

        }


        return obj;
    }
}
