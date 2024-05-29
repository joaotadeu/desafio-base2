package br.com.joaotadeu.mantis.tests;

import br.com.joaotadeu.mantis.pages.MantisLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@DisplayName("Testes Automatizados da Funcionalidade Login")
public class MantisLoginTest {

    private WebDriver navegador;
    private MantisLoginPage mantisLoginPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        navegador = new FirefoxDriver();
        navegador.manage().window().maximize();
        mantisLoginPage = new MantisLoginPage(navegador);
    }

    @AfterEach
    void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Quando faço login com sucesso na plataforma")
    public void testLoginUsuario() {
        // Abre a página de login
        navegador.get("https://mantis-prova.base2.com.br/login_page.php");

        // Verifica se o logotipo de login está visível
        WebElement logo = mantisLoginPage.waitForElementVisibility(By.className("login-logo"));
        Assertions.assertTrue(logo.isDisplayed(), "O logotipo de login não está visível.");

        // Realiza o login
        mantisLoginPage.preencherCampoUsuario("Joao_Pereira");
        mantisLoginPage.clicarNoBotaoEntrar();
        mantisLoginPage.preencherCampoSenha("1234qwer");
        mantisLoginPage.clicarNoBotaoLogar();

        // Verifica se o login foi bem-sucedido
        WebElement dashboard = mantisLoginPage.waitForElementVisibility(By.id("main-container"));
        Assertions.assertTrue(dashboard.isDisplayed(), "O login não foi bem-sucedido.");
    }
}