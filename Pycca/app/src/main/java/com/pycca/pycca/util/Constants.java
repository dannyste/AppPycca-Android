package com.pycca.pycca.util;

public class Constants {

    public static int ANIMATION_DURATION                = 1000;
    public static int LOCATION_REQUEST_INTERVAL         = 1000;
    public static int LOCATION_REQUEST_FASTEST_INTERVAL = 500;
    public static int GOOGLE_MAP_RADIUS                 = 10000;
    public static int GOOGLE_MAP_ZOOM                   = 16;
    public static int GOOGLE_MAP_ZOOM_RADIUS            = 13;
    public static int SPLASH_TIME_OUT                   = 3000;

    //FIRESTORE
    public static String FIRESTORE_PARAMETER_TABLE        = "Parameter";
    public static String FIRESTORE_PARAMETER_APP_TABLE    = "App";

    public static String FIRESTORE_USER_TABLE             = "User";

    public static String FIRESTORE_CONTENT_TABLE          = "Content";

    public static String FIRESTORE_HOME_TABLE             = "Home";
    public static String FIRESTORE_HOME_HEADER_TABLE      = "Header";
    public static String FIRESTORE_HOME_CONTENT_TABLE     = "Content";

    public static String FIRESTORE_COUPON_TABLE           = "Coupon";
    public static String FIRESTORE_COUPON_CONTENT_TABLE   = "Content";

    public static String FIRESTORE_BUY_TABLE              = "Buy";
    public static String FIRESTORE_BUY_HEADER_TABLE       = "Header";
    public static String FIRESTORE_BUY_CONTENT_TABLE      = "Content";

    //TOPICS
    public static String TOPIC_INVITED                               = "Invitado";
    public static String TOPIC_SOCIAL_NETWORK                        = "RedSocial";
    public static String TOPIC_NATIVE                                = "Nativo";
    public static String TOPIC_CLUB_PYCCA_PARTNER                    = "SocioClubPycca";
    public static String TOPIC_NOT_CLUB_PYCCA_PARTNER                = "NoSocioClubPycca";
    public static String TOPIC_SOCIAL_NETWORK_CLUB_PYCCA_PARTNER     = TOPIC_SOCIAL_NETWORK + "-" + TOPIC_CLUB_PYCCA_PARTNER;
    public static String TOPIC_SOCIAL_NETWORK_NOT_CLUB_PYCCA_PARTNER = TOPIC_SOCIAL_NETWORK + "-" + TOPIC_NOT_CLUB_PYCCA_PARTNER;
    public static String TOPIC_NATIVE_CLUB_PYCCA_PARTNER             = TOPIC_NATIVE + "-" + TOPIC_CLUB_PYCCA_PARTNER;
    public static String TOPIC_NATIVE_NOT_CLUB_PYCCA_PARTNER         = TOPIC_NATIVE + "-" + TOPIC_NOT_CLUB_PYCCA_PARTNER;

}
