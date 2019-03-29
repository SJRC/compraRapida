package br.com.resourceit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.resrouceit.pages.util.PontoDeVerificacao;
import br.com.resrouceit.pages.util.Tela;

public class BuscaItem {

	public void pesquisarProdutoNaPagina(Tela tela, PontoDeVerificacao vp, WebDriver driver) {

		// Aguardando até o campo de busca fique disponivel
		tela.esperaExplicitaPeloElementoAlocado(driver, "h_search-input");

		// Verificando se a busca foi exibida

		WebElement campoDeBusca = driver.findElement(By.id("h_search-input"));
		vp.verificarValoresEsperados(true, campoDeBusca.isEnabled());

		// Verificando se o botão de busca encontra-se disponivel

		WebElement botaoDeBusca = driver.findElement(By.id("h_search-btn"));
		vp.verificarValoresEsperados(true, botaoDeBusca.isEnabled());

		// Inforomando qual produto a ser buscado
		String produto = "console playstation 4 1tb";

		tela.esperarAteOElementoSerPossivelOCick(driver, "h_search-btn");

		botaoDeBusca.click();

		// Preenchendo o campo de busca do produto
		campoDeBusca.sendKeys(produto);

		// Realizando a busca no site
		tela.esperarAteOElementoSerPossivelOCick(driver, "h_search-input");
		botaoDeBusca.click();

	}

}
