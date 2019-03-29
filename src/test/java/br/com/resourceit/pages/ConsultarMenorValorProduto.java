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

public class ConsultarMenorValorProduto {

	private WebDriver driver;
	private Tela tela = new Tela();
	private BuscaItem busca = new BuscaItem();
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
		driver.close();

	}

	@Test
	public void verificarMenorPrecoProduto() throws InterruptedException {
		

		busca.pesquisarProdutoNaPagina(tela, vp, driver);
		
		// Informando o produto eperado
		String produtoMaisBarato = "Console PlayStation 4 1TB Bundle + Game Fifa 19 - Sony";

		WebElement consoleMaisBarato = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/section[1]/div[2]/div[1]/h1[1]"));

		// Exibindo a descrição do produto da página no console
		tela.exibindoValorNoConsole(consoleMaisBarato.getText());

		// Verificar se o produto esta sendo exbido na página
		vp.verificarValoresEsperados(true, produtoMaisBarato.equalsIgnoreCase(consoleMaisBarato.getText()));

		// Aguadando até que o console mais barato esteja disponivel para compra
		tela.esperarAteOElementoSerPossivelOCick(driver, consoleMaisBarato);

		// Selecionando o console mais barato para compra
		consoleMaisBarato.click();

		// Verificar se o produto com menor valor foi selecionado , identificando se o
		// botão 'Comprar esta disponivel' e o preço e o valor esperado

		String precoEsperado = "R$ 1.899,99";
		WebElement precoParaCompra = driver.findElement(By.xpath(
				"//span[@class='sales-price main-offer__SalesPrice-sc-1oo1w8r-1 fiWaea TextUI-sc-1hrwx40-0 hbVZKK']"));

		tela.exibindoValorNoConsole(precoParaCompra.getText());

		vp.verificarValoresEsperados(true, precoEsperado.equalsIgnoreCase(precoParaCompra.getText()));

		// Verificando se o botão comprar foi selecionado

		WebElement botaoComprar = driver
				.findElement(By.xpath("//div[@class='Wrapper-sc-15y8pl7-3 cGBZPu ViewUI-oocyw8-6 kvewNe']"));

		vp.verificarValoresEsperados(true, botaoComprar.isEnabled());

	}

}
