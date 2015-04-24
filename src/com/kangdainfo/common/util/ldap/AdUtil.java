package com.kangdainfo.common.util.ldap;

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

public class AdUtil {
	
	private DirContext fdaDirContext = null;
	private boolean _connected = false;
	private AdUser adUser;
	
	public boolean isConnected() {
		return _connected;
	}
	public DirContext getFdaDirContext() {
		return fdaDirContext;
	}
	
	public AdUser getAdUser() {
		return adUser;
	}

	public AdUtil(String userName, String userPwd) {
		Hashtable<Object,Object> env = new Hashtable<Object,Object>();		
		//String adminName = "aaron@hq.fda";
		//String adminPassword = "Abcd@1234";
		String ldapURL = "ldap://10.1.0.1:389";		
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_PRINCIPAL,userName+"@hq.fda");
		env.put(Context.SECURITY_CREDENTIALS,userPwd);
		env.put(Context.PROVIDER_URL,ldapURL);
		try {
			this.fdaDirContext = new InitialDirContext(env);						
			this.adUser = getAdUser(userName);
			if (this.adUser!=null) {
				adUser.setUserPwd(userPwd);
			}
			_connected = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public AdUser getAdUser(String userId) {
		try {
			SearchControls searchCtls = new SearchControls();
			String returnedAtts[]={"sn","givenName","mail","displayname","departmentnumber","department","samaccountname"};
			searchCtls.setReturningAttributes(returnedAtts);
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(&(objectClass=user)(mail=*)(cn=" + userId + "))";			
			String searchBase = "DC=hq,DC=fda"; 
			NamingEnumeration<SearchResult> rs = fdaDirContext.search(searchBase, searchFilter, searchCtls);
			while (rs.hasMoreElements()) {
				SearchResult sr = rs.next();
				Attributes attrs = sr.getAttributes();
				if (attrs!=null && attrs.size()>0) {
					AdUser user = new AdUser();
					user.setUserName(Common.get(attrs.get("displayname").get()));
					user.setUserAccount(Common.get(attrs.get("samaccountname").get()));
					user.setUserEmail(Common.get(attrs.get("mail").get()));
					user.setDeptNo(Common.get(attrs.get("departmentnumber").get()));
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
