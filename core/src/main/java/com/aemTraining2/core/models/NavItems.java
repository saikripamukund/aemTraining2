package com.aemTraining2.core.models;

public class NavItems {
	private String pageName;
	private String pagePath;
	
	
	 public NavItems(String name, String path) {
		 this.pageName = name;
	     this.pagePath = path;
	     }

	

	    public String getName() {
	        return pageName;
	    }
	    
	    public String getPath(){
	    	return pagePath;
	    }
}
