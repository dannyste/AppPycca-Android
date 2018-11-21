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
    public static String FIRESTORE_USER_TABLE           = "User";
    public static String FIRESTORE_CONTENT_TABLE        = "Content";

    public static String FIRESTORE_HOME_TABLE           = "Home";
    public static String FIRESTORE_HOME_HEADER_TABLE    = "Header";
    public static String FIRESTORE_HOME_CONTENT_TABLE   = "Content";

    public static String FIRESTORE_BUY_TABLE           = "Buy";
    public static String FIRESTORE_BUY_HEADER_TABLE    = "Header";
    public static String FIRESTORE_BUY_CONTENT_TABLE   = "Content";

    public static String FIRESTORE_COUPON_TABLE           = "Coupon";
    public static String FIRESTORE_COUPON_CONTENT_TABLE   = "Content";

    //TOPICS
    public static String PUSH_TOPIC_INVITADO               = "Invitado";
    public static String PUSH_TOPIC_RED_SOCIAL             = "RedSocial";
    public static String PUSH_TOPIC_NATIVO                 = "Nativo";
    public static String PUSH_TOPIC_SOCIO_CLUB_PYCCA       = "SocioClubPycca";
    public static String PUSH_TOPIC_NO_SOCIO_CLUB_PYCCA    = "NoSocioClubPycca";
    public static String PUSH_TOPIC_RED_SOCIAL_SOCIO_CP    = PUSH_TOPIC_RED_SOCIAL + "-" + PUSH_TOPIC_SOCIO_CLUB_PYCCA;
    public static String PUSH_TOPIC_RED_SOCIAL_NO_SOCIO_CP = PUSH_TOPIC_RED_SOCIAL + "-" + PUSH_TOPIC_NO_SOCIO_CLUB_PYCCA;
    public static String PUSH_TOPIC_NATIVO_SOCIO_CP        = PUSH_TOPIC_NATIVO + "-" + PUSH_TOPIC_SOCIO_CLUB_PYCCA;
    public static String PUSH_TOPIC_NATIVO_NO_SOCIO_CP     = PUSH_TOPIC_NATIVO + "-" + PUSH_TOPIC_NO_SOCIO_CLUB_PYCCA;

}

