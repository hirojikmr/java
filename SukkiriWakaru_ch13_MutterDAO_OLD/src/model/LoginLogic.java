package model;

public class LoginLogic {
  public boolean execute(User user) {
    if (user.getPass().equals("1")) {
      return true;
    }
    return false;
  }
}