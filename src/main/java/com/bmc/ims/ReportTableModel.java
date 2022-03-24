package com.bmc.ims;

import io.jenkins.plugins.datatables.TableColumn;
import io.jenkins.plugins.datatables.TableColumn.ColumnCss;
import io.jenkins.plugins.datatables.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public ReportTableModel(JSONArray ja) {
        super();
        this.ja = ja;
    }

    @Override
    public String getId() {
        //return ForensicsJobAction.FORENSICS_ID;
        return "cfa";
    }

    @Override
    public List<TableColumn> getColumns() {
        List<TableColumn> columns = new ArrayList<>();

        columns.add(new TableColumn("Job name", "job"));
        columns.add(new TableColumn("PSB/PLAN Name", "psb"));
        columns.add(new TableColumn("Start time", "startTime").setHeaderClass(ColumnCss.DATE));
        columns.add(new TableColumn("Commits number", "#Commits"));
        columns.add(new TableColumn("Job Duration", "jobDration"));
        columns.add(new TableColumn("Commit per min", "comPerMin"));
        columns.add(new TableColumn("Commit per sec", "comPerSec"));
        columns.add(new TableColumn("Exceptions", "Exceptions"));

        return columns;
    }


    @Override
    public List<Object> getRows() {

        List<Object> rows = new ArrayList<>();

        for (int i = 0; i < ja.length(); i++)
        {
            JSONObject obj= (JSONObject) ja.get(i);

            rows.add(new SingleRow( obj.getString("jobName"),
                                    obj.getString("planName"),
                                    obj.getString("startTime"),
                                    obj.getString("commits#"),
                                    obj.getString("jobDuration"),
                                    obj.getString("freqPerMin"),
                                    obj.getString("freqPerSec"),
                                    obj.getString("exceptions")
                                    ));

        }
        return rows;
    }


    @SuppressWarnings("PMD.DataClass") // Used to automatically convert to JSON object
    public static class SingleRow {

        private final String jobname,planName,start,com,dur,frePmin,frePersec,exc;


        public SingleRow(String jobname,String planName,String start,String com,String dur,String freqPmin,String freqPsec,String Ex) {
            this.jobname = jobname;
            this.planName=planName;
            this.start=start;
            this.com=com;
            this.dur=dur;
            this.frePersec=freqPsec;
            this.frePmin=freqPmin;
            this.exc=Ex;

        }


    }
}
