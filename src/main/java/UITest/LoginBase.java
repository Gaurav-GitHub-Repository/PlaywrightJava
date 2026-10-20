package UITest;

import java.io.IOException;

public interface LoginBase {

    public void verifyUsername() throws IOException;

    public void verifyPassword() throws IOException;

    public void verifyButton();

    public void verifyLoginPage() throws IOException;

    public void verifyLogin(String emailValue, String passwordValue);

}
