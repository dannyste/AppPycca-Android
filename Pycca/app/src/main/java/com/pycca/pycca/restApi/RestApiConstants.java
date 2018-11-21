package com.pycca.pycca.restApi;

final class RestApiConstants {

    static final String SERVER_ROOT_URL = "http://pagos.pycca.com:7654/";

    //CONTROLLERS
    private static final String CLIENT                          = "client/";
    private static final String PYCCA                           = "pycca/";

    private static final String ACTION_VALIDATE_CLIENT          = "validate_client/";
    private static final String PARAMETER_VALIDATE_CLIENT       = "{documentType}/{identificationCard}/{clubPyccaCardNumber}";

    private static final String ACTION_OUR_SHOPS                = "our_shops";

    private static final String ACTION_GET_BALANCE              = "balance/";
    private static final String PARAMETER_GET_BALANCE           = "{clubPyccaCardNumber}";

    private static final String ACTION_POST_CLUB_PYCCA_PARTNER  = "club_pycca_partner/";

    static final String SERVER_URL_GET_VALIDATE_CLIENT          = CLIENT + ACTION_VALIDATE_CLIENT + PARAMETER_VALIDATE_CLIENT;

    static final String SERVER_URL_GET_OUR_SHOPS                = PYCCA + ACTION_OUR_SHOPS;

    static final String SERVER_URL_GET_BALANCE                  = CLIENT + ACTION_GET_BALANCE + PARAMETER_GET_BALANCE;

    static final String SERVER_URL_POST_CLUB_PYCCA_PARTNER      = CLIENT + ACTION_POST_CLUB_PYCCA_PARTNER;
}
