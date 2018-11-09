package com.pycca.pycca.restApi;

final class RestApiConstants {

    static final String SERVER_ROOT_URL = "http://pagos.pycca.com:7654/";

    private static final String CLIENT                          = "client/";
    private static final String ACTION_VALIDATE_CLIENT          = "validate_client/";
    private static final String PARAMETER_VALIDATE_CLIENT       = "{documentType}/{identificationCard}/{clubPyccaCardNumber}";

    private static final String PYCCA                           = "pycca/";
    private static final String ACTION_OUR_SHOPS                = "our_shops";

    static final String SERVER_URL_GET_VALIDATE_CLIENT          = CLIENT + ACTION_VALIDATE_CLIENT + PARAMETER_VALIDATE_CLIENT;

    static final String SERVER_URL_GET_OUR_SHOPS                = PYCCA + ACTION_OUR_SHOPS;

}
