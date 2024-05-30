package br.com.joaotadeu.mantis.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

public class MantisAreaLogadaPage {
    private final WebDriverWait wait;
    private final WebDriver navegador;

    public MantisAreaLogadaPage(WebDriver navegador) {
        this.navegador = navegador;
        this.wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
    }

    // esperar até que um elemento seja visível
    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // clicar no botão "Criar Tarefa"
    public void clicarBotaoCriarTarefa() {
        WebElement botaoCriarTarefa = waitForElementVisibility(By.cssSelector("div.btn-group.btn-corner.padding-right-8.padding-left-8 > a.btn.btn-primary.btn-sm"));
        botaoCriarTarefa.click();
    }

    // selecionar uma categoria
    public void selecionarCategoria(String categoria) {
        WebElement selectElement = waitForElementVisibility(By.id("category_id"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(categoria);
    }

    // selecionar uma opção de reprodutibilidade
    public void selecionarReprodutibilidade(String reprodutibilidade) {
        WebElement selectElement = waitForElementVisibility(By.id("reproducibility"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(reprodutibilidade);
    }

    // selecionar uma opção de severidade
    public void selecionarSeveridade(String severidade) {
        WebElement selectElement = waitForElementVisibility(By.id("severity"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(severidade);
    }

    // selecionar uma opção de prioridade
    public void selecionarPrioridade(String prioridade) {
        WebElement selectElement = waitForElementVisibility(By.id("priority"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(prioridade);
    }

    // selecionar uma opção de marcador
    public void selecionarMarcador(String marcador) {
        WebElement selectElement = waitForElementVisibility(By.id("tag_select"));
        Select select = new Select(selectElement);
        select.selectByVisibleText(marcador);
    }

    // preencher o campo de entrada platform
    public void preencherPlatform(String plataforma) {
        WebElement campoPlatform = waitForElementVisibility(By.id("platform"));
        campoPlatform.sendKeys(plataforma);
    }

    // preencher campo SO
    public void preencherSO(String so) {
        WebElement campoSO = waitForElementVisibility(By.id("os"));
        campoSO.sendKeys((so));
    }

    // preencher campo versão
    public void preencherVersao(String version) {
        WebElement campoVersao = waitForElementVisibility(By.id("os_build"));
        campoVersao.sendKeys(version);
    }

    // preencher campo resumo
    public void preencherResumo(String resumo) {
        WebElement campoResumo = waitForElementVisibility(By.id("summary"));
        campoResumo.sendKeys(resumo);
    }

    // preencher campo descricao
    public void preencherDescricao(String descricao) {
        WebElement campoDescricao = waitForElementVisibility(By.id("description"));
        campoDescricao.sendKeys(descricao);
    }

    // preencher campo passos
    public  void preencherPassosReproduzir(String passos){
        WebElement campoPassos = waitForElementVisibility(By.id("steps_to_reproduce"));
        campoPassos.sendKeys(passos);
    }

    // preencher campo dados adicionais
    public void preencherDadosAdicionais(String dadosAdicionais){
        WebElement campoDadosAdicionais = waitForElementVisibility(By.id("additional_info"));
        campoDadosAdicionais.sendKeys(dadosAdicionais);
    }

    // upload de um arquivo
    public void uploadArquivo(String caminhoArquivo) {
        WebElement inputFile = navegador.findElement(By.cssSelector("input[type='file'].dz-hidden-input"));
        inputFile.sendKeys(caminhoArquivo);
    }

    // selecionar a visibilidade entre público e privado
    public void selecionarVisibilidade(String visibilidade) {
        WebElement opcaoPublico = waitForElementVisibility(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(12) > td:nth-child(2) > label:nth-child(1) > span:nth-child(2)"));
        WebElement opcaoPrivado = waitForElementVisibility(By.cssSelector(".table > tbody:nth-child(1) > tr:nth-child(12) > td:nth-child(2) > label:nth-child(2) > span:nth-child(2)"));

        switch (visibilidade.toLowerCase()) {
            case "publico":
                opcaoPublico.click();
                break;
            case "privado":
                opcaoPrivado.click();
                break;
            default:
                throw new IllegalArgumentException("Opção de visibilidade inválida: " + visibilidade);
        }
    }

    // cria nova tarefa
    public void criaNovaTarefa(){
        WebElement botaoCriarNovaTarefa = waitForElementVisibility(By.cssSelector("input[type='submit']"));
        botaoCriarNovaTarefa.click();
    }

    // Método para tirar uma captura de tela e armazenar em uma pasta
    public void tirarPrint() {
        if (navegador != null) {
            try {
                String cenarioId = UUID.randomUUID().toString(); // Gera um identificador aleatório
                File file = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
                File destino = new File("evidencias/screenshot/" + cenarioId + ".jpg");
                FileUtils.copyFile(file, destino);
            } catch (IOException e) {
                System.err.println("Erro ao capturar screenshot: " + e.getMessage());
            }
        }
    }
}

