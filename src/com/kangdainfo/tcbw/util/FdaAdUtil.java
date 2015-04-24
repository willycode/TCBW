package com.kangdainfo.tcbw.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.kangdainfo.common.util.Common;

public class FdaAdUtil {
	
	private DirContext fdaDirContext = null;
	private boolean _connected = false;
	private FdaAdUser fdaUser;
	
	public boolean isConnected() {
		return _connected;
	}
	public DirContext getFdaDirContext() {
		return fdaDirContext;
	}
	public FdaAdUser getFdaUser() {
		return fdaUser;
	}
	
	public FdaAdUtil(String userName, String userPwd) {
		Hashtable<Object,Object> env = new Hashtable<Object,Object>();		
		//String adminName = "aaron@hq.fda";
		//String adminPassword = "Abcd@1234";
		String ldapURL = "ldap://fda.gov.tw:389";		
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_PRINCIPAL,userName+"@fda.gov.tw");
		env.put(Context.SECURITY_CREDENTIALS,userPwd);
		env.put(Context.PROVIDER_URL,ldapURL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		try {
			this.fdaDirContext = new InitialDirContext(env);						
			this.fdaUser = getFdaUser(userName);
			if (this.fdaUser!=null) {
				fdaUser.setUserPwd(userPwd);
			}
			_connected = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public FdaAdUtil(String userName) {
		Hashtable<Object,Object> env = new Hashtable<Object,Object>();		
		//String adminName = "aaron@hq.fda";
		//String adminPassword = "Abcd@1234";
		String ldapURL = "ldap://fda.gov.tw:389";		
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_PRINCIPAL,userName+"@fda.gov.tw");
		//env.put(Context.SECURITY_CREDENTIALS,userPwd);
		env.put(Context.PROVIDER_URL,ldapURL);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		try {
			this.fdaDirContext = new InitialDirContext(env);						
			this.fdaUser = getFdaUser(userName);
			if (this.fdaUser!=null) {
				//fdaUser.setUserPwd(userPwd);
			}
			_connected = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public FdaAdUser getFdaUser(String userId) {
		try {
			SearchControls searchCtls = new SearchControls();
			String returnedAtts[]={"sn","givenName","mail","displayname","departmentnumber","department","samaccountname"};
			searchCtls.setReturningAttributes(returnedAtts);
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(&(objectClass=user)(mail=*)(cn=" + userId + "))";			
			String searchBase = "DC=fda,DC=gov,DC=tw"; 
			NamingEnumeration<SearchResult> rs = fdaDirContext.search(searchBase, Common.get(searchFilter), searchCtls);
			while (rs.hasMoreElements()) {
				SearchResult sr = rs.next();
				Attributes attrs = sr.getAttributes();
				if (attrs!=null && attrs.size()>0) {
					FdaAdUser user = new FdaAdUser();
					user.setUserName(Common.get(attrs.get("displayname").get()));
					user.setUserAccount(Common.get(attrs.get("samaccountname").get()));
					user.setUserEmail(Common.get(attrs.get("mail").get()));
					//user.setDeptNo(Common.get(attrs.get("departmentnumber").get()));
					user.setDeptName(Common.get(attrs.get("department").get()));					
					return user;
				}
			}				 			
		} catch (NamingException e) {
			System.err.println("Problem searching directory: " + e);
		}	
		return null;
	}
	
}
