package subsystem.interbank;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.InvalidTransactionAmountException;
import common.exception.InvalidVersionException;
import common.exception.NotEnoughBalanceException;
import common.exception.NotEnoughTransactionInfoException;
import common.exception.SuspiciousTransactionException;
import common.exception.UnrecognizedException;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

import java.util.Map;

import utils.Configs;
import utils.MyMap;
import utils.Utils;

public class InterbankSubsystemController {

  private static final String PAY_COMMAND = "pay";
  private static final String VERSION = "1.0.0";

  private static InterbankBoundary interbankBoundary = new InterbankBoundary();

  
  /** 
   * @param card
   * @param amount
   * @param contents
   * @return PaymentTransaction
   */
  public PaymentTransaction refund(CreditCard card, int amount, String contents) {
    return null;
  }

  
  /** 
   * @param data
   * @return String
   */
  private String generateData(Map<String, Object> data) {
    return ((MyMap) data).toJSON();
  }

  
  /** 
   * @param card
   * @param amount
   * @param contents
   * @return PaymentTransaction
   */
  public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
    Map<String, Object> transaction = new MyMap();

    try {
      transaction.putAll(MyMap.toMyMap(card));
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new InvalidCardException();
    }
    transaction.put("command", PAY_COMMAND);
    transaction.put("transactionContent", contents);
    transaction.put("amount", amount);
    transaction.put("createdAt", Utils.getToday());

    Map<String, Object> requestMap = new MyMap();
    requestMap.put("version", VERSION);
    requestMap.put("transaction", transaction);

    String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
    MyMap response = null;
    try {
      response = MyMap.toMyMap(responseText, 0);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      throw new UnrecognizedException();
    }

    return makePaymentTransaction(response);
  }

  
  /** 
   * @param response
   * @return PaymentTransaction
   */
  private PaymentTransaction makePaymentTransaction(MyMap response) {
    if (response == null) {
      return null;
    }
    MyMap transcation = (MyMap) response.get("transaction");

    // Create credit card
    String cardCode = (String) transcation.get("cardCode");
    String owner = (String) transcation.get("owner");
    int ccvCode = Integer.parseInt((String) transcation.get("cvvCode"));
    String dateExpired = (String) transcation.get("dateExpired"); 
    CreditCard card = new CreditCard(cardCode, owner, ccvCode, dateExpired);

    // Create Payment transaction
    String errorCode = (String) response.get("errorCode"); 
    String transactionId = (String) transcation.get("transactionId");
    String transactionContent = (String) transcation.get("transactionContent");
    int amount = Integer.parseInt((String) transcation.get("amount"));
    String createdAt = (String) transcation.get("createdAt");
    PaymentTransaction trans = new 
        PaymentTransaction(errorCode, card, transactionId, transactionContent, amount, createdAt);

    switch (trans.getErrorCode()) {
      case "00":
        break;
      case "01":
        throw new InvalidCardException();
      case "02":
        throw new NotEnoughBalanceException();
      case "03":
        throw new InternalServerErrorException();
      case "04":
        throw new SuspiciousTransactionException();
      case "05":
        throw new NotEnoughTransactionInfoException();
      case "06":
        throw new InvalidVersionException();
      case "07":
        throw new InvalidTransactionAmountException();
      default:
        throw new UnrecognizedException();
    }

    return trans;
  }

}
