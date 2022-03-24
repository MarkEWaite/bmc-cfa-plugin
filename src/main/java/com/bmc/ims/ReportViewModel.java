package com.bmc.ims;

import edu.hm.hafner.echarts.JacksonFacade;
import edu.hm.hafner.echarts.Palette;
import edu.hm.hafner.echarts.PieChartModel;
import edu.hm.hafner.echarts.PieData;
import hudson.model.ModelObject;
import io.jenkins.plugins.datatables.DefaultAsyncTableContentProvider;
import io.jenkins.plugins.datatables.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportViewModel extends DefaultAsyncTableContentProvider implements ModelObject {
    JSONArray ja;

    public ReportViewModel(JSONArray ja) {
        super();
        this.ja = ja;
    }
    @Override
    public TableModel getTableModel(String s) {
        return new ReportTableModel(this.ja);
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    public String getCommitModel() {

        return create();

    }

    /**
     * Creates a pie chart based on data that resides on csv file
     *
     * @param \file in csv format
     *
     * @return the chart model
     */

    public String create() {
        int moreThen5=0, lessThen60=0,others=0;
        //JSONArray ja=CsvFile.readCsvFile(file.getPath());
        //skip the headers and start from 1
        for(int i=1;i<this.ja.length();i++)
        {
            JSONObject obj= (JSONObject) ja.get(i);
            if(obj.get("exceptions").toString().contains("More than 5 commits/ sec"))
                moreThen5++;
            else if(obj.get("exceptions").toString().contains("Less than 60 commits/min"))
                lessThen60++;
            else
                others++;
        }
        PieChartModel model = new PieChartModel("Marit");

        model.add(new PieData("> 5 commits/sec", moreThen5), Palette.RED);
        model.add(new PieData("< 60 commits/min>", lessThen60), Palette.GREEN);
        model.add(new PieData("Others", others), Palette.YELLOW);

        String json = new JacksonFacade().toJson(model);

        return json;
    }
}
