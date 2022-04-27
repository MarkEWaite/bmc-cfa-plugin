package com.bmc.ims;

import org.kohsuke.stapler.DataBoundConstructor;
import java.io.Serializable;
/**
 * Represents a single job that is associated with the input RECON data set 
 */

public class CfaJobname implements Serializable {

	private String jobname;
	
	@DataBoundConstructor
	public CfaJobname(String jobname) {
		this.jobname = jobname;		
	}
	
	public String getJobname() {
		return jobname;
	}
	
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}	
	
}
