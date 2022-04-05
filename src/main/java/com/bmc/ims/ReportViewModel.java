package com.bmc.ims;

import edu.hm.hafner.echarts.JacksonFacade;
import edu.hm.hafner.echarts.Palette;
import edu.hm.hafner.echarts.PieChartModel;
import edu.hm.hafner.echarts.PieData;
import hudson.model.ModelObject;
import hudson.model.Run;
import io.jenkins.plugins.datatables.DefaultAsyncTableContentProvider;
import io.jenkins.plugins.datatables.TableModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportViewModel extends DefaultAsyncTableContentProvider implements ModelObject {
    JSONArray ja;
    Run owner;
    String rptType;

    public ReportViewModel(Run owner, JSONArray ja, String reportType) {
        super();
        this.ja = ja;
        this.owner=owner;
        this.rptType=reportType;
    }
    @Override
    public TableModel getTableModel(String s) {
        return new ReportTableModel(this.ja,this.rptType);
    }

    @Override
    public String getDisplayName() {
        if (this.rptType.equals("IMS"))
            return "Application Checkpoint";
        else
            return "Commit";
    }

    public String getCommitModel() {

        return create();

    }
    public Run getOwner(){
        return this.owner;
    }

    public String getRptType() {
        return rptType;
    }

   
    // Creates a pie chart based on data that resides on csv file  

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
        PieChartModel model = new PieChartModel("Commit Dist");

        model.add(new PieData("> 5 commits/sec", moreThen5), Palette.RED);
        model.add(new PieData("< 60 commits/min>", lessThen60), Palette.GREEN);
        model.add(new PieData("Others", others), Palette.YELLOW);

        String json = new JacksonFacade().toJson(model);

        return json;
    }
}
