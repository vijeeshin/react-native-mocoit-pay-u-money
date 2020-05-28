package com.mocoitlabs.payumoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;

import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

public class PayActivity extends AppCompatActivity {
    private String amount = null;
    private String txnId = null;
    private String productName = null;
    private String firstName = null;
    private String email = null;
    private String phone = null;
    private String merchantId = null;
    private String merchantKey = null;
    private String merchantSUrl = null;
    private String merchantFUrl = null;
    private Boolean merchantSandbox = false;
    private String hash = null;
    private String udf1 = "";
    private String udf2 = "";
    private String udf3 = "";
    private String udf4 = "";
    private String udf5 = "";
    private String udf6 = "";
    private String udf7 = "";
    private String udf8 = "";
    private String udf9 = "";
    private String udf10 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.loader_layout);
        //data from react native
        this.amount = extras.getString("amount");
        this.txnId = extras.getString("txnId");
        this.productName = extras.getString("productName");
        this.firstName = extras.getString("firstName");
        this.email = extras.getString("email");
        this.phone = extras.getString("phone");
        this.merchantId = extras.getString("merchantId");
        this.merchantKey = extras.getString("merchantKey");
        this.merchantSUrl = extras.getString("merchantSUrl");
        this.merchantFUrl = extras.getString("merchantFUrl");
        this.merchantSandbox = extras.getBoolean("merchantSandbox");


        this.udf1 = extras.getString("udf1");
        this.udf2 = extras.getString("udf2");
        this.udf3 = extras.getString("udf3");
        this.udf4 = extras.getString("udf4");
        this.udf5 = extras.getString("udf5");
        this.udf6 = extras.getString("udf6");
        this.udf7 = extras.getString("udf7");
        this.udf8 = extras.getString("udf8");
        this.udf9 = extras.getString("udf9");
        this.udf10 = extras.getString("udf10");
        this.hash = extras.getString("hash");
        try {
            this.MakePayment();
        } catch (Exception e) {
            Log.e("ERROR_PAYU", e.getMessage());
            e.printStackTrace();
        }
    }

    private void MakePayment() throws Exception {
        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
        if(!TextUtils.isEmpty(this.amount)) {
            builder.setAmount(this.amount);
        }
        if(!TextUtils.isEmpty(this.txnId)) {
            builder.setTxnId(this.txnId);
        }
        if(!TextUtils.isEmpty(this.productName)) {
            builder.setProductName(this.productName);
        }
        if(!TextUtils.isEmpty(this.firstName)) {
            builder.setFirstName(this.firstName);
        }
        if(!TextUtils.isEmpty(this.email)) {
            builder.setEmail(this.email);
        }
        if(!TextUtils.isEmpty(this.phone)) {
            builder.setPhone(this.phone);
        }
        if(!TextUtils.isEmpty(this.merchantId)) {
            builder.setMerchantId(this.merchantId);
        }
        if(!TextUtils.isEmpty(this.merchantKey)) {
            builder.setKey(this.merchantKey);
        }
        if(!TextUtils.isEmpty(this.merchantSUrl)) {
            builder.setsUrl(this.merchantSUrl);
        }
        if(!TextUtils.isEmpty(this.merchantFUrl)) {
            builder.setfUrl(this.merchantFUrl);
        }
        if(!TextUtils.isEmpty(this.udf1)) {
            builder.setUdf1(this.udf1);
        }
        if(!TextUtils.isEmpty(this.udf2)) {
            builder.setUdf2(this.udf2);
        }
        if(!TextUtils.isEmpty(this.udf3)) {
            builder.setUdf3(this.udf3);
        }
        if(!TextUtils.isEmpty(this.udf4)) {
            builder.setUdf4(this.udf4);
        }
        if(!TextUtils.isEmpty(this.udf5)) {
            builder.setUdf5(this.udf5);
        }
        if(!TextUtils.isEmpty(this.udf6)) {
            builder.setUdf6(this.udf6);
        }
        if(!TextUtils.isEmpty(this.udf7)) {
            builder.setUdf7(this.udf7);
        }
        if(!TextUtils.isEmpty(this.udf8)) {
            builder.setUdf8(this.udf8);
        }
        if(!TextUtils.isEmpty(this.udf9)) {
            builder.setUdf9(this.udf9);
        }
        if(!TextUtils.isEmpty(this.udf10)) {
            builder.setUdf10(this.udf10);
        }

        builder.setIsDebug(this.merchantSandbox);
        PayUmoneySdkInitializer.PaymentParam paymentParam = builder.build();
        paymentParam.setMerchantHash(this.hash);
        PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, PayActivity.this, R.style.AppTheme_default, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent();

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    intent.putExtra("success", true);
                } else {
                    intent.putExtra("success", false);
                }

                String payuResponse = transactionResponse.getPayuResponse();
                intent.putExtra("payuResponse", payuResponse);

            } else if (resultModel != null && resultModel.getError() != null) {
                intent.putExtra("success", false);
            } else {
                Log.e("ERROR_PAYU", "Error response : " + resultModel.getError().getTransactionResponse());
                intent.putExtra("success", false);
            }
        }
        setResult(RESULT_OK, intent);
        finish();
    }




}
