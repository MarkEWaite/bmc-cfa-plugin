package com.bmc.ims;

import io.jenkins.plugins.datatables.TableColumn;
import io.jenkins.plugins.datatables.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the dynamic model for the details table that shows the cfa report.
 *
 * <p>
 * This model consists of the following columns:
 * </p>
 * <ul>
 * <li>file name</li>
 * <li>total number of different authors</li>
 * <li>total number of commits</li>
 * <li>time of last commit</li>
 * <li>time of first commit</li>
 * </ul>
 *
 * @author Marit cohen
 */

public class ReportTableModel extends TableModel {

    private JSONArray ja;
    String rptType;


    public ReportTableModel(JSONArray ja, String rptType) {
        super();
        this.ja = ja;
        this.rptType=rptType;
    }

    @Override
    public String getId() {
        //return ForensicsJobAction.FORENSICS_ID;
        return "cfa";
    }

    @Override
    public List<TableColumn> getColumns() {
        List<TableColumn> columns = new ArrayList<>();

        columns.add(new TableColumn("Job name", "jobName"));
        if(this.rptType.equals("IMS"))
            columns.add(new TableColumn("PSB Name", "psbName"));
        else
            columns.add(new TableColumn("Plan Name", "planName"));
  //      columns.add(new TableColumn("Start time", "startTime").setHeaderClass(ColumnCss.DATE));
        columns.add(new TableColumn("Start time", "startTime"));
        if(this.rptType.equals("IMS"))
            columns.add(new TableColumn("#Checkpoints", "chkpt#"));
        else
            columns.add(new TableColumn("#Commits", "commits#"));
        if(this.rptType.equals("IMS"))
            columns.add(new TableColumn("Checkpoint Type", "chkptType"));
        columns.add(new TableColumn("Job Duration", "jobDuration"));
        columns.add(new TableColumn("Commit per min", "freqPerMin"));
        columns.add(new TableColumn("Commit per sec", "freqPerSec"));
        columns.add(new TableColumn("Exceptions", "exceptions"));

        return columns;
    }


    @Override
    public List<Object> getRows() {

        List<Object> rows = new ArrayList<>();

        for (int i = 0; i < ja.length(); i++)
        {
            JSONObject obj= (JSONObject) ja.get(i);
            Map<String, String> rowsMap = new HashMap<>();
            rowsMap.put("jobName",obj.getString("jobName"));
            if(this.rptType.equals("IMS"))
                rowsMap.put("psbName",obj.getString("psbName"));
            else
                rowsMap.put("planName",obj.getString("planName"));
            rowsMap.put("startTime",obj.getString("startTime"));
            if(this.rptType.equals("IMS")) {
                rowsMap.put("chkpt#", obj.getString("chkpt#"));
                rowsMap.put("chkptType#", obj.getString("chkptType"));
            }
            else
                rowsMap.put("commits#", obj.getString("commits#"));

            rowsMap.put("jobDuration",obj.getString("jobDuration"));
            rowsMap.put("freqPerMin",obj.getString("freqPerMin"));
            rowsMap.put("freqPerSec",obj.getString("freqPerSec"));
            rowsMap.put("exceptions",obj.getString("exceptions"));
            rows.add(rowsMap);

        }
        //return rowsMap.entrySet().stream().filter(e -> e.getValue().matches(".*")).map(Map.Entry::getKey).collect(Collectors.toList());
        return rows;
    }
}
