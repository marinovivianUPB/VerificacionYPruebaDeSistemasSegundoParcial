package examenFilaB.ejercicio4CambiarPasswordTodoly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selenium.pagesTodoLy.LoginSection;
import selenium.pagesTodoLy.MainPage;
import selenium.pagesTodoLy.MenuSection;
import selenium.pagesTodoLy.SettingsPopUp;
import selenium.session.Session;
import tareaCRUDTOKEN.ConfigurationTOKEN;

public class ChangePasswordTodoly {
    MenuSection menuSection = new MenuSection();
    MainPage mainPage = new MainPage();
    LoginSection loginSection = new LoginSection();

    SettingsPopUp settingsPopUp = new SettingsPopUp();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }

    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("http://todo.ly/");
    }
    @Test
    public void loginTesting(){
        String oldPassword = "12345";
        String newPassword="123456";

        mainPage.loginButton.click();
        loginSection.emailTextBox.setText(ConfigurationTOKEN.user);
        loginSection.passwordTextBox.setText(oldPassword);
        loginSection.loginButton.click();

        menuSection.settings.click();
        settingsPopUp.oldPassword.setText(oldPassword);
        settingsPopUp.newPassword.setText(newPassword);
        settingsPopUp.okButton.click();

        menuSection.logoutButton.click();
        mainPage.loginButton.click();
        loginSection.emailTextBox.setText(ConfigurationTOKEN.user);
        loginSection.passwordTextBox.setText(newPassword);
        loginSection.loginButton.click();

        Assertions.assertTrue(menuSection.logoutButton.isControlDisplayed(), "ERROR no se pudo iniciar sesion");
    }
}
