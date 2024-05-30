package br.com.joaotadeu.mantis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MantisLoginPage {

    private final WebDriverWait wait;

    public MantisLoginPage(WebDriver navegador) {
        this.wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
    }

    // espera até que um elemento seja visível
    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // preencher o campo de usuário
    public void preencherCampoUsuario(String usuario) {
        WebElement campoUsuario = waitForElementVisibility(By.id("username"));
        campoUsuario.sendKeys(usuario);
    }

    // clicar no botão "Entrar"
    public void clicarNoBotaoEntrar() {
        WebElement botaoEntrar = waitForElementVisibility(By.cssSelector("input[type='submit'].btn-success"));
        botaoEntrar.click();
    }

    // preencher o campo de senha
    public void preencherCampoSenha(String senha) {
        WebElement campoSenha = waitForElementVisibility(By.id("password"));
        campoSenha.sendKeys(senha);
    }

}