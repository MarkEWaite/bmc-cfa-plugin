package com.bmc.ims;

import hudson.FilePath;
import hudson.model.Run;
import io.jenkins.plugins.util.AbstractXmlStream;
import io.jenkins.plugins.util.BuildAction;
import io.jenkins.plugins.util.JobAction;
import org.kohsuke.stapler.StaplerProxy;

import java.io.File;
//import hudson.model.Run;
//@Extension
//@ExportedBean
//@SuppressWarnings("PMD.DataClass")
//public class BmcCfaAction implements BuildBadgeAction, RunAction2 {
//public class BmcCfaAction implements RunAction2 {
public class BmcCfaAction extends BuildAction implements StaplerProxy {
    //private transient Run run;
    //private JSONArray ja;
    FilePath ws;
    int buildNum;
    Run owner;
    String reportType;
/*
    protected BmcCfaAction(Run owner, Object result)  {

          super(owner, result, false);

        this.run=owner;
    }
*/
    public BmcCfaAction(Run owner, int number, FilePath workspace, ResponseObject resp) {
        super(owner, resp, false);
        this.ws=workspace;
        this.buildNum=number;
        this.owner=owner;
    }


    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "Commit Distribution";
    }

    @Override
    public String getUrlName() {
        return "stat";
    }


     /**
      * Returns the detail view for the forensics data for all Stapler requests.
      *
      * @return the detail view for the forensics data
      */

     @Override
     public Object getTarget() {
         return new ReportViewModel(this.owner,CsvFile.readCsvFile(getRelatedJob().getPath()),this.reportType);
     }


     public File getRelatedJob() {

        //File dir = new File(((FreeStyleBuild) this.run).getWorkspace()+"\\"+this.run.getNumber());
        File dir = new File(this.ws+"\\"+this.buildNum);
        File[] matches = dir.listFiles((dir1, name) -> name.contains("CSV"));

        if(matches[0].getPath().contains("IMS"))
            this.reportType="IMS";
        else
            this.reportType="DB2";


        return matches[0];
    }

    @Override
    protected AbstractXmlStream createXmlStream() {
        return null;
    }

    @Override
    protected JobAction<? extends BuildAction> createProjectAction() {
        return null;
    }

    @Override
    protected String getBuildResultBaseName() {
        return null;
    }


 }