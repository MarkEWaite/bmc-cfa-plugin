package com.bmc.ims;

import org.kohsuke.stapler.DataBoundConstructor;
import java.io.Serializable;
/**
 * Represents a single DELTA PLUS load library
 */

public class CfaLoadLib implements Serializable {

	private String lib;
	

	/**
	 * Constructor
	 */
	@DataBoundConstructor
	public CfaLoadLib(String lib) {
		this.lib = lib;
		
	}

	public String getLib() {
		return this.lib;
	}
	 public void setLib(String lib) {
		this.lib = lib;
	}
	
	
	
}
