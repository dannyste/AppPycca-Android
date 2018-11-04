package com.pycca.pycca.restApi.model;

public class BaseResponse {

    private boolean status;
    private String message;
    private Data data;

    public BaseResponse(boolean status, String message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        public status_error status_error;
        public Object result;

        public Data() {

        }

        public Data(BaseResponse.status_error status_error, Object result) {
            this.status_error = status_error;
            this.result = result;
        }

        public BaseResponse.status_error getStatus_error() {
            return status_error;
        }

        public void setStatus_error(BaseResponse.status_error status_error) {
            this.status_error = status_error;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

    }

    public class status_error {

        private int co_error;
        private String tx_error;

        public status_error() {

        }

        public status_error(int co_error, String tx_error) {
            this.co_error = co_error;
            this.tx_error = tx_error;
        }

        public int getCo_error() {
            return co_error;
        }

        public void setCo_error(int co_error) {
            this.co_error = co_error;
        }

        public String getTx_error() {
            return tx_error;
        }

        public void setTx_error(String tx_error) {
            this.tx_error = tx_error;
        }

    }

}
