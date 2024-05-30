package br.com.joaotadeu.mantis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MantisAreaLogadaPage {
    private final WebDriverWait wait;

    public MantisAreaLogadaPage(WebDriver navegador) {
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


}
