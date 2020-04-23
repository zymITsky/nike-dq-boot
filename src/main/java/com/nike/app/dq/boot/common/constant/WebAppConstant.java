
package com.nike.app.dq.boot.common.constant;

import java.util.ArrayList;
import java.util.List;

public class WebAppConstant {

	public final static List<String> GBL_ALL_BUSINESS_FUNCTION = new ArrayList<String>();
	static {
		GBL_ALL_BUSINESS_FUNCTION.add("Digital");
		GBL_ALL_BUSINESS_FUNCTION.add("Finance");
		GBL_ALL_BUSINESS_FUNCTION.add("SupplyChain");
		GBL_ALL_BUSINESS_FUNCTION.add("Merchandising");
		GBL_ALL_BUSINESS_FUNCTION.add("DSM");
		GBL_ALL_BUSINESS_FUNCTION.add("Master");
		GBL_ALL_BUSINESS_FUNCTION.add("Retail");
		GBL_ALL_BUSINESS_FUNCTION.add("Sales");
	}
	
	public final static String USER_CONFIG             = "USER_CONFIG";
	public final static String USER_SERVICE_REF        = "USER_SERVICE_REF";
	public final static String REMOTE_USER_ID          = "REMOTE_USER_ID";
	public final static String CAPTCHA_CODE_IN_SESSION = "CAPTCHA_CODE_IN_SESSION";
	public final static String LIST_OF_DATA_SOURCE     = "LIST_OF_DATA_SOURCE";
	public final static String LIST_OF_CASE_OWNER      = "LIST_OF_CASE_OWNER";
	public final static String LIST_OF_BIZ_FUNC        = "LIST_OF_BIZ_FUNC";

	public final static String ERROR_DETAIL             = "ERROR_DETAIL";
	public final static String GLOBAL_SYSTEM_ERROR_PAGE = "GLOBAL_SYSTEM_ERROR_PAGE";
	public final static String MENU_TITLE               = "MENU_TITLE";
	public final static String RULE_TC_STEP_ID          = "RULE_TC_STEP_ID";
	public final static String INIT_TC_RULE_CASE_NAME   = "INIT_TC_RULE_CASE_NAME";
	public final static String RULE_QS_STEP_ID          = "RULE_QS_STEP_ID";
	public final static String RULE_R21FC_STEP_ID       = "RULE_R21FC_STEP_ID";
	public final static String RULE_R33MC_STEP_ID       = "RULE_R33MC_STEP_ID";
	public final static String SCHEDULE_STEP_ID         = "SCHEDULE_STEP_ID";
	public final static String DATASOURCE_STEP_ID       = "DATASOURCE_STEP_ID";

	public final static String USER_LOGIN_FORM_PAGE      = "USER_LOGIN_FORM_PAGE";
	public final static String USER_DASHBOARD_HOME_PAGE  = "USER_DASHBOARD_HOME_PAGE";
	public final static String RULE_TC_LIST_FORM_PAGE    = "RULE_TC_LIST_FORM_PAGE";
	public final static String RULE_TC_NEW_FORM_PAGE     = "RULE_TC_NEW_FORM_PAGE";
	public final static String RULE_TC_VIEW_DETAIL_PAGE  = "RULE_TC_VIEW_DETAIL_PAGE";
	public final static String RULE_QS_LIST_FORM_PAGE    = "RULE_QS_LIST_FORM_PAGE";
	public final static String RULE_QS_NEW_FORM_PAGE     = "RULE_QS_NEW_FORM_PAGE";
	public final static String RULE_R21FC_LIST_FORM_PAGE = "RULE_R21FC_LIST_FORM_PAGE";
	public final static String RULE_R21FC_NEW_FORM_PAGE  = "RULE_R21FC_NEW_FORM_PAGE";
	public final static String RULE_R33MC_LIST_FORM_PAGE = "RULE_R33MC_LIST_FORM_PAGE";
	public final static String RULE_R33MC_NEW_FORM_PAGE  = "RULE_R33MC_NEW_FORM_PAGE";
	public final static String SCHEDULE_LIST_FORM_PAGE   = "SCHEDULE_LIST_FORM_PAGE";
	public final static String DATASOURCE_LIST_FORM_PAGE = "DATASOURCE_LIST_FORM_PAGE";

	public final static String URL_ENCODED_TC_RC_ACTION_FORM = "TC_RC_ACTION_FORM";
}