package br.com.resourceit.pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.resrouceit.pages.util.PontoDeVerificacao;
import br.com.resrouceit.pages.util.Tela;

public class CompraSubmarinoTest {

	private WebDriver driver;
	private Tela tela = new Tela();
	private PontoDeVerificacao vp = new PontoDeVerificacao();

	@Before
	public void setUp() throws Exception {

		// Informando qual o driver e qual o caminho para inicialização do driver
		System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");

		// Instanciando o driver do Firefox
		driver = new FirefoxDriver();
		// Informando qual a 'URL' que será aberta
		String url = "https://www.submarino.com.br/";

		// Realizando a navegação até a URL desejada
		tela.navegarAteUrl(driver, url);

	}

	@After
	public void tearDown() throws Exception {
		// navegador.close();

	}

	@Test
	public void verificarMenorPrecoProduto() {
		// Aguardando até o campo de busca fique disponivel
		tela.esperaExplicitaPeloElementoAlocado(driver, "h_search-input");

		// Verificando se a busca foi exibida

		WebElement campoDeBusca = driver.findElement(By.id("h_search-input"));
		vp.verificarValoresEsperados(true, campoDeBusca.isEnabled());

		// Verificando se o botão de busca encontra-se disponivel

		WebElement botaoDeBusca = driver.findElement(By.id("h_search-btn"));
		vp.verificarValoresEsperados(true, botaoDeBusca.isEnabled());

		// Inforomando qual produto a ser buscado
		String produto = "Playstation 4 1TB";

		tela.esperarAteOElementoSerPossivelOCick(driver, "h_search-btn");

		botaoDeBusca.click();

		// Preenchendo o campo de busca do produto
		campoDeBusca.sendKeys(produto);

	}

}
