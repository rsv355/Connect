package com.webmyne.connect.Utils;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class APIConstants {
    public static final String BASE_URL = "http://ws-srv-net/Applications/Androids/MapleAppServices/";
    public static final String USER_LOGIN_URL = "Configuration.svc/json/UserLogin";
    public static final String USER_PROFILE_UPDATE_URL = "Configuration.svc/json/UserUpdate";
    public static final String FETCH_INDUSTRY_URL = "Configuration.svc/json/FetchIndustry/{Searchstr}";
    public static final String POST_LEAD_URL = "LeadsService.svc/json/PostLead";

    public static final String FETCH_LEAD_HISTORY = "LeadsService.svc/json/LeadHistory";
    public static final String FETCH_COMMISSION_HISTORY = "LeadsService.svc/json/CommissionHistory";
}