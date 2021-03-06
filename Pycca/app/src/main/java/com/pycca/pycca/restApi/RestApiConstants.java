package com.pycca.pycca.restApi;

public final class RestApiConstants {

    public static final String SERVER_ROOT_URL                    = "http://pagos.pycca.com:7654/";

    private static final String CLIENT                            = "client/";
    private static final String PYCCA                             = "pycca/";

    private static final String ACTION_VALIDATE_CLIENT            = "validate_client/";
    private static final String PARAMETER_VALIDATE_CLIENT         = "{documentType}/{identificationCard}/{clubPyccaCardNumber}";

    private static final String ACTION_GET_ACCOUNT_STATUS         = "account_status/";
    private static final String PARAMETER_GET_ACCOUNT_STATUS      = "{clubPyccaCardNumber}";

    private static final String ACTION_POST_QUOTA_INCREASE        = "quota_increase/";

    private static final String ACTION_GET_QUOTA_CALCULATOR       = "quota_calculator/";
    private static final String PARAMETER_GET_QUOTA_CALCULATOR    = "{amount}";

    private static final String ACTION_GET_CARDS                  = "cards/";
    private static final String PARAMETER_GET_CARDS               = "{accountNumber}/{clubPyccaCardNumber}";

    private static final String ACTION_POST_CARD_LOCKING          = "card_locking/";

    private static final String ACTION_POST_CLUB_PYCCA_PARTNER    = "club_pycca_partner/";

    private static final String ACTION_OUR_SHOPS                  = "our_shops";

    private static final String ACTION_GET_PDF_ACCOUNT_STATUS     = "pdf_account_status/";
    private static final String PARAMETER_GET_PDF_ACCOUNT_STATUS  = "{accountNumber}/{cutDate}";


    static final String SERVER_URL_GET_VALIDATE_CLIENT            = CLIENT + ACTION_VALIDATE_CLIENT + PARAMETER_VALIDATE_CLIENT;

    static final String SERVER_URL_GET_ACCOUNT_STATUS             = CLIENT + ACTION_GET_ACCOUNT_STATUS + PARAMETER_GET_ACCOUNT_STATUS;

    static final String SERVER_URL_POST_QUOTA_INCREASE            = CLIENT + ACTION_POST_QUOTA_INCREASE;

    static final String SERVER_URL_GET_CARDS                      = CLIENT + ACTION_GET_CARDS + PARAMETER_GET_CARDS;

    static final String SERVER_URL_POST_CARD_LOCKING              = CLIENT + ACTION_POST_CARD_LOCKING;

    static final String SERVER_URL_POST_CLUB_PYCCA_PARTNER        = CLIENT + ACTION_POST_CLUB_PYCCA_PARTNER;

    static final String SERVER_URL_GET_OUR_SHOPS                  = PYCCA + ACTION_OUR_SHOPS;

    static final String SERVER_URL_GET_QUOTA_CALCULATOR           = CLIENT + ACTION_GET_QUOTA_CALCULATOR + PARAMETER_GET_QUOTA_CALCULATOR;

    static final String SERVER_URL_GET_PDF_ACCOUNT_STATUS         = CLIENT + ACTION_GET_PDF_ACCOUNT_STATUS + PARAMETER_GET_PDF_ACCOUNT_STATUS;

}
