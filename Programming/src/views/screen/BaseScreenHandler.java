package views.screen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import controller.BaseController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

public class BaseScreenHandler extends FXMLScreenHandler {

  private Scene scene;
  private BaseScreenHandler prev;
  protected final Stage stage;
  protected HomeScreenHandler homeScreenHandler;
  protected Hashtable<String, String> messages;
  private BaseController bController;

  private BaseScreenHandler(String screenPath) throws IOException {
    super(screenPath);
    this.stage = new Stage();
  }

  
  /** 
   * @param prev
   */
  public void setPreviousScreen(BaseScreenHandler prev) {
    this.prev = prev;
  }

  
  /** 
   * @return BaseScreenHandler
   */
  public BaseScreenHandler getPreviousScreen() {
    return this.prev;
  }

  public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
    super(screenPath);
    this.stage = stage;
  }

  public void show() {
    if (this.scene == null) {
      this.scene = new Scene(this.content);
    }
    this.stage.setScene(this.scene);
    this.stage.show();
  }

  
  /** 
   * @param string
   */
  public void setScreenTitle(String string) {
    this.stage.setTitle(string);
  }

  
  /** 
   * @param bController
   */
  public void setBController(BaseController bController) {
    this.bController = bController;
  }

  
  /** 
   * @return BaseController
   */
  public BaseController getBController() {
    return this.bController;
  }

  
  /** 
   * @param messages
   */
  public void forward(Hashtable messages) {
    this.messages = messages;
  }

  
  /** 
   * @param HomeScreenHandler
   */
  public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
    this.homeScreenHandler = HomeScreenHandler;
  }

}
