package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.ApplicationProgrammingInterface;

public class InterbankBoundary {

  
  /** 
   * @param url
   * @param data
   * @return String
   */
  String query(String url, String data) {
    String response = null;
    try {
      response = ApplicationProgrammingInterface.post(url, data);
    } catch (Exception e) {
      throw new UnrecognizedException();
    }
    return response;
  }

}
