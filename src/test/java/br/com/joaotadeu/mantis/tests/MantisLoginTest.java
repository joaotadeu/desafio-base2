package br.com.joaotadeu.mantis.tests;

import br.com.joaotadeu.mantis.DriverFactory.DriverFactory;
import br.com.joaotadeu.mantis.pages.MantisAreaLogadaPage;
import br.com.joaotadeu.mantis.pages.MantisLoginPage;
import br.com.joaotadeu.mantis.util.TestReport;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@DisplayName("Testes Automatizados da Funcionalidade Login")
public class MantisLoginTest {

    private WebDriver navegador;
    private MantisLoginPage mantisLoginPage;
    private MantisAreaLogadaPage mantisAreaLogadaPage;
    private TestReport testReport;

    @BeforeEach
    void setUp() {
        navegador = DriverFactory.getDriver();
        navegador.manage().window().maximize();
        mantisLoginPage = new MantisLoginPage(navegador);
        mantisAreaLogadaPage = new MantisAreaLogadaPage(navegador);
        testReport = new TestReport("evidencias/test-report.html");
    }

    @AfterEach
    void tearDown() {
        DriverFactory.killDriver();
        testReport.close();
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
        mantisLoginPage.clicarNoBotaoEntrar();

        // Verifica se o login foi bem-sucedido
        WebElement dashboard = mantisLoginPage.waitForElementVisibility(By.id("main-container"));
        Assertions.assertTrue(dashboard.isDisplayed(), "O login não foi bem-sucedido.");

        // Criação de tarefa
        mantisAreaLogadaPage.clicarBotaoCriarTarefa();
        mantisAreaLogadaPage.selecionarCategoria("[Todos os Projetos] categoria teste");
        mantisAreaLogadaPage.selecionarReprodutibilidade("sempre");
        mantisAreaLogadaPage.selecionarSeveridade("grande");
        mantisAreaLogadaPage.selecionarPrioridade("alta");
        mantisAreaLogadaPage.selecionarMarcador("Desenvolvimento");
        mantisAreaLogadaPage.preencherPlatform("Windows 10");
        mantisAreaLogadaPage.preencherSO("Linux");
        mantisAreaLogadaPage.preencherVersao("2.0");
        mantisAreaLogadaPage.preencherResumo("Erro no Weblogic");
        mantisAreaLogadaPage.preencherDescricao("Ao efetuar tentativa de validação é retornado erro");
        mantisAreaLogadaPage.preencherPassosReproduzir("Seguir o template de execução");
        mantisAreaLogadaPage.preencherDadosAdicionais("Poderia priorizar?");
        mantisAreaLogadaPage.uploadArquivo("/Users/joaotadeu/Documents/Workspace/Java/teste-tecnico/evidencias/img.png");
        mantisAreaLogadaPage.selecionarVisibilidade("privado");
        mantisAreaLogadaPage.criaNovaTarefa();

    }
}