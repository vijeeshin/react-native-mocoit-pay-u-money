"use strict";
import { NativeModules, NativeEventEmitter } from "react-native";

const PayuPay = NativeModules.MocoitPayUMoney;
const PayuEvent = new NativeEventEmitter(PayuPay);

const removeSubscriptions = () => {
  PayuEvent.removeAllListeners("PAYU_PAYMENT_SUCCESS");
  PayuEvent.removeAllListeners("PAYU_PAYMENT_FAILED");
};

export default class PayuMoney {
  static pay(options) {
    let data = {
      amount: options.amount,
      txid: options.txid,
      productId: options.productId,
      name: options.name,
      email: options.email,
      phone: options.phone,
      id: options.id,
      key: options.key,
      surl: options.surl,
      furl: options.furl,
      sandbox: options.sandbox,
      hash: options.hash,
      udf1: options.udf1,
      udf2: options.udf2,
      udf3: options.udf3,
      udf4: options.udf4,
      udf5: options.udf5,
      udf6: options.udf6,
      udf7: options.udf7,
      udf8: options.udf8,
      udf9: options.udf9,
      udf10: options.udf10
    };
    return new Promise(function(resolve, reject) {
      PayuEvent.addListener("PAYU_PAYMENT_SUCCESS", data => {
        resolve(data);
        removeSubscriptions();
      });
      PayuEvent.addListener("PAYU_PAYMENT_FAILED", data => {
        reject({ success: false });
        removeSubscriptions();
      });

      PayuPay.makePayment(
        data.amount,
        data.txid,
        data.productId,
        data.name,
        data.email,
        data.phone,
        data.id,
        data.key,
        data.surl,
        data.furl,
        data.sandbox,
        data.hash,
        data.udf1,
        data.udf2,
        data.udf3,
        data.udf4,
        data.udf5,
        data.udf6,
        data.udf7,
        data.udf8,
        data.udf9,
        data.udf10
      );
    });
  }
}
