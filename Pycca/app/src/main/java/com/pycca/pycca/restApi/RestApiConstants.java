package com.pycca.pycca.restApi;

final class RestApiConstants {

    static final String SERVER_ROOT_URL = "http://pagos.pycca.com:7654/";

    //CONTROLLERS
    private static final String CLIENT                          = "client/";
    private static final String PYCCA                           = "pycca/";

    private static final String ACTION_VALIDATE_CLIENT          = "validate_client/";
    private static final String PARAMETER_VALIDATE_CLIENT       = "{documentType}/{identificationCard}/{clubPyccaCardNumber}";

    private static final String ACTION_OUR_SHOPS                = "our_shops";

    private static final String ACTION_GET_ACCOUNT_SATUS        = "account_status/";
    private static final String PARAMETER_GET_ACCOUNT_SATUS     = "{clubPyccaCardNumber}";

    private static final String ACTION_POST_CLUB_PYCCA_PARTNER  = "club_pycca_partner/";

    private static final String ACTION_POST_QUOTA_INCREASE      = "quota_increase/";

    private static final String ACTION_GET_CLUB_PYCCA_CARDS     = "club_pycca_cards/";
    private static final String PARAMETER_GET_CLUB_PYCCA_CARDS  = "{accountNumber}/{clubPyccaCardNumber}";

    private static final String ACTION_POST_CARD_BLOCKING     = "card_blocking/";

    private static final String ACTION_GET_QUOTA_CALCULATOR        = "quota_calculator/";
    private static final String PARAMETER_GET_QUOTA_CALCULATOR    = "{amount}";

    static final String SERVER_URL_GET_VALIDATE_CLIENT      = CLIENT + ACTION_VALIDATE_CLIENT + PARAMETER_VALIDATE_CLIENT;

    static final String SERVER_URL_GET_OUR_SHOPS            = PYCCA + ACTION_OUR_SHOPS;

    static final String SERVER_URL_GET_ACCOUNT_STATUS       = CLIENT + ACTION_GET_ACCOUNT_SATUS + PARAMETER_GET_ACCOUNT_SATUS;

    static final String SERVER_URL_POST_CLUB_PYCCA_PARTNER  = CLIENT + ACTION_POST_CLUB_PYCCA_PARTNER;

    static final String SERVER_URL_POST_QUOTA_INCREASE      = CLIENT + ACTION_POST_QUOTA_INCREASE;

    static final String SERVER_URL_GET_CLUB_PYCCA_CARDS      = CLIENT + ACTION_GET_CLUB_PYCCA_CARDS + PARAMETER_GET_CLUB_PYCCA_CARDS;

    static final String SERVER_URL_POST_CARD_BLOCKING      = CLIENT + ACTION_POST_CARD_BLOCKING;

    static final String SERVER_URL_GET_QUOTA_CALCULATOR           = CLIENT + ACTION_GET_QUOTA_CALCULATOR + PARAMETER_GET_QUOTA_CALCULATOR;
}
