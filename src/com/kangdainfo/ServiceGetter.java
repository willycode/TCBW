package com.kangdainfo;

import com.kangdainfo.web.listener.MyServletContext;

public class ServiceGetter {

	 private static ServiceGetter instance;

	    public ServiceGetter() {
	        if(ServiceGetter.instance != null) {
	            throw new RuntimeException(
	                    this.getClass().getName()
	                            + "is designed to be a Singleton, the instance already exist:"
	                            + ServiceGetter.instance);
	        }

	        ServiceGetter.instance = this;
	    }

	    public static ServiceGetter getInstance() {
	        return instance;
	    }
	    
		private com.kangdainfo.common.service.CommonService commonService;
		private com.kangdainfo.tcbw.model.service.TCBWService tcbwService;
		private com.kangdainfo.common.service.AuthenticationService authenticationService;
		private com.kangdainfo.web.util.WebContextInfo webContextInfo;
		private com.kangdainfo.tcbw.model.service.ScheduleService scheduleService;
				
		public com.kangdainfo.common.service.AuthenticationService getAuthenticationService() {
			return authenticationService;
		}
		public void setAuthenticationService(com.kangdainfo.common.service.AuthenticationService authenticationService) {
			this.authenticationService = authenticationService;
		}

		public com.kangdainfo.common.service.CommonService getCommonService() {
			return commonService;
		}
		public void setCommonService(com.kangdainfo.common.service.CommonService commonService) {
			this.commonService = commonService;
		}
		
		public com.kangdainfo.tcbw.model.service.TCBWService getTcbwService() {
			return tcbwService;
		}

		public void setTcbwService(com.kangdainfo.tcbw.model.service.TCBWService tcbwService) {
			this.tcbwService = tcbwService;
		}

		public com.kangdainfo.web.util.WebContextInfo getWebContextInfo() {
			return webContextInfo;
		}
		
		public void setWebContextInfo(com.kangdainfo.web.util.WebContextInfo webContextInfo) {
			this.webContextInfo = webContextInfo;
		}
		
		public MyServletContext getMyServletContext() {	return MyServletContext.getInstance();	}
		
		/**
		 * @param scheduleService the scheduleService to set
		 */
		public com.kangdainfo.tcbw.model.service.ScheduleService getScheduleService() {
			return scheduleService;
		}

		public void setScheduleService(	com.kangdainfo.tcbw.model.service.ScheduleService scheduleService) {
			this.scheduleService = scheduleService;
		}	
		
}
