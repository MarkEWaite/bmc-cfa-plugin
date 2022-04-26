package com.bmc.ims;

import org.kohsuke.stapler.DataBoundConstructor;
import java.io.Serializable;
/**
 * Represents a single IMS system that is associated with the input RECON 
 */

public class CfaImsid  implements Serializable{

	private String imsid;
	
	@DataBoundConstructor
	public CfaImsid(String imsid) {
		this.imsid = imsid;		
	}
	
	public String getImsid() {
		return imsid;
	}
	
	public void setImsid(String imsid) {
		this.imsid = imsid;
	}	
	
}
