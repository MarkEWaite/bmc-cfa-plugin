package com.bmc.ims;

import edu.umd.cs.findbugs.annotations.CheckForNull;
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
    String ws;
    int buildNum;
    Run owner;
    String reportType;
    ResponseObject resp;
/*
    protected BmcCfaAction(Run owner, Object result)  {

          super(owner, result, false);

        this.run=owner;
    }
*/
    public BmcCfaAction(Run owner, int number, String workspace, ResponseObject resp) {
        super(owner, resp, false);
        this.ws=workspace;
        this.buildNum=number;
        this.owner=owner;
        this.resp=resp;
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
      * Returns the commit distribution view.
      *
      * @return the commit distribution view
      */

     @Override
     public Object getTarget() {

         return new ReportViewModel(this.owner,CsvFile.readCsvFile(getRelatedJob().getPath()),this.reportType);
     }

    @CheckForNull
    @SuppressWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
     public File getRelatedJob() {


             //File dir = new File(((FreeStyleBuild) this.run).getWorkspace()+"\\"+this.run.getNumber());
             File dir = new File(this.ws + "\\" + this.buildNum);
             File[] matches = dir.listFiles((dir1, name) -> name.contains("CSV"));

             if(matches.length!=0) {
                 if (matches[0].getPath().contains("IMS"))
                     this.reportType = "IMS";
                 else
                     this.reportType = "DB2";
                 return matches[0];
             }
            else
                return dir;
    }


    @Override
    @CheckForNull
    @SuppressWarnings("NP_NONNULL_RETURN_VIOLATION")
    protected AbstractXmlStream createXmlStream() {
        return null;
    }

    /*
        @CheckForNull
        @SuppressWarnings("UR_UNINIT_READ_CALLED_FROM_SUPER_CONSTRUCTOR")
        @Override
        protected AbstractXmlStream createXmlStream() {
            AbstractXmlStream s=new AbstractXmlStream(this.resp.getClass()) {
                @Override
                protected Object createDefaultValue() {
                     DummyXmlObj d= new DummyXmlObj() ;
                    return d;
                }
            };
            return s;
        }
    */
    @Override
    @CheckForNull
    @SuppressWarnings("NP_NONNULL_RETURN_VIOLATION")
    protected JobAction<? extends BuildAction> createProjectAction() {
        return null;
    }

    /*
        @Override
        protected  JobAction createProjectAction() {
            JobAction j=new JobAction(getOwner().getParent(),this.resp.getClass()) {
                @Override
                public String getIconFileName() {
                    return "Dummy";
                }

                @Override
                public String getDisplayName() {
                    return "Dummy";
                }

                @Override
                public String getUrlName() {
                    return "Dummy";
                }
            };
            return j ;
        }
    */
    /*
        @Override
        protected AbstractXmlStream createXmlStream() {
            return null;
        }

        @Override
        protected JobAction<? extends BuildAction> createProjectAction() {
            return null;
        }
    */
    @Override
    protected String getBuildResultBaseName() {
        return "cfa";
    }


 }
/*
class DummyXmlObj{
    String str="Dummy Obj";
}

 */