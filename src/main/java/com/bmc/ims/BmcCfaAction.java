package com.bmc.ims;

import hudson.model.FreeStyleBuild;
import hudson.model.Run;
import io.jenkins.plugins.util.AbstractXmlStream;
import io.jenkins.plugins.util.BuildAction;
import io.jenkins.plugins.util.JobAction;
import org.json.JSONArray;
import org.kohsuke.stapler.StaplerProxy;

import java.io.File;
//import hudson.model.Run;
//@Extension
//@ExportedBean
//@SuppressWarnings("PMD.DataClass")
//public class BmcCfaAction implements BuildBadgeAction, RunAction2 {
//public class BmcCfaAction implements RunAction2 {
public class BmcCfaAction extends BuildAction implements StaplerProxy {
    private transient Run run;
    private JSONArray ja;

    protected BmcCfaAction(Run owner, Object result)  {

          super(owner, result, false);

        this.run=owner;
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
         return new ReportViewModel(CsvFile.readCsvFile(getRelatedJob().getPath()));
     }


     public File getRelatedJob() {

        File dir = new File(((FreeStyleBuild) this.run).getWorkspace()+"\\"+this.run.getNumber());
        File[] matches = dir.listFiles((dir1, name) -> name.contains("CSV"));

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