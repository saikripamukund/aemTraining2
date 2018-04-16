package com.aemTraining2.core.use;
import com.aemTraining2.core.models.NavItems;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class NavigationUse extends WCMUsePojo {
	private List<NavItems> navitems = new ArrayList<NavItems>();
	private List<String> childrenPath = new ArrayList<String>();
	private List<String> pageName = new ArrayList<String>();
	Logger log = LoggerFactory.getLogger(NavigationUse.class); 
	// The activate() method of a Use class automatically is called every time this component is rendered.  It's a method you can
	// use to set stuff up, initialize variables, etc.
	@Override
	public void activate() throws Exception {
		// WCMUsePojo provides a bunch of stuff such as getters to get things like ResourceResolvers. You use ResourceResolvers
		// to resolve Sling Resources (the actual Resource class).  The Sling API considers every node in the repository to be 
		// a Resource.
		ResourceResolver resolver = getResourceResolver();
		
		// You can also get this component instance as a Resource like this.  This Resource will be the actual node that is created
		// when an author drags an instance of your navigation component onto a page (or if you programatically include one).
		Resource resource = getResource();
		//log.error("Value Map " + resource.getValueMap());
		
		// Per the requirement of the project, where you need to display the direct children of the current page in the nav, you could
		// do it a number of ways.  One is to do it in this Use class by havinig a local variable that holds the list of paths:
		Iterator<Page> it = getCurrentPage().listChildren();
		while(it.hasNext()) {
			Page current = it.next();
			String path = current.getPath();
			String name = current.getTitle();
			pageName.add(name);
			childrenPath.add(path);
			NavItems item = new NavItems(name, path);
			navitems.add(item);
		}
	}

	// This public method exposes a variable called "children" that happens to match what we named our private, local variable (that doesn't matter).
	// HTL automatically treates getters like this by stripping the "get" portion of the method name away and lower-casing the first letter of what
	// remains.  In your HTML file, you can then reference the variable "children" directly from your Use class instance.  Make sure you include
	// your Use class atop your HTML file with a data-sly-use block!  Since the children variable is iterable (implements the Iterable interface), 
	// the data-sly-list block statement can loop over it for you.
	public List<String> getChildrenPath() {
		return childrenPath;
	}
	public List<String> getPageName() {
		return pageName;
	}
	public List<NavItems> getNavItems(){
		return navitems;	
	}
}
