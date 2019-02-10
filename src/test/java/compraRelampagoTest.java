
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.java.compra.relampago.gearbest.pages.util.Tela;

public class compraRelampagoTest {

	private WebDriver navegador;
	private Tela tela = new Tela();

	@Before
	public void setUp() throws Exception {

		// Abrindo o navegador
		System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver\\geckodriver.exe");

		navegador = new FirefoxDriver();
		tela.esperaImplicita(navegador);

		tela.maximarNavegador(navegador);

		navegador.get("https://cart.gearbest.com/cart/index");

	}

	@After
	public void tearDown() throws Exception {
		// navegador.close();

	}

	@Test
	public void comprasRelampagoTest() {

		WebElement botaoFormularioLogin = navegador.findElement(By.xpath("//a[@class='btn middle line1 cart_btn']"));

		if (botaoFormularioLogin != null && botaoFormularioLogin.isDisplayed()) {
			botaoFormularioLogin.click();
		}

		tela.esperarAteQuandoOElementoEstiverVisivel(navegador,
				navegador.findElement(By.xpath("//a[@class='authTab_link active']")));

		tela.esperarAteQuandoOElementoEstiverVisivel(navegador,
				navegador.findElement(By.xpath("//form[@action='javascript:;']//input[@placeholder='Email']")));

		WebElement campoUsuario = navegador
				.findElement(By.xpath("//form[@action='javascript:;']//input[@placeholder='Email']"));

		tela.esperarAteQuandoOElementoEstiverVisivel(navegador,
				navegador.findElement(By.xpath("//input[@id='password']")));

		WebElement campoSenha = navegador.findElement(By.xpath("//input[@id='password']"));

		String usuario = "saviojrc.1988@gmail.com";
		String senha = "s2468101";

		campoUsuario.click();
		campoUsuario.sendKeys(usuario);
		campoSenha.click();
		campoSenha.sendKeys(senha);

		WebElement botaoLogin = navegador.findElement(By.xpath("//input[@id='js-btnSubmit']"));

		botaoLogin.click();

		tela.esperaImplicita(navegador);

		WebElement produto = navegador.findElement(By.xpath("//a[@class='cart_link']"));
		WebElement preco = navegador.findElement(By.xpath("//p[@class='cart_subtotal']"));

		if (produto.isDisplayed() && preco.isDisplayed()) {
			Double valorProdutoTela = Double.valueOf(preco.getText().replace("$", ""));
			WebElement botaoProcederParaCompra = navegador
					.findElement(By.xpath("//a[@class='btn middle strong proceed_checkout']"));

			while (valorProdutoTela > 1000.00) {
				navegador.navigate().refresh();
			}

			if ((valorProdutoTela < 1000.00) && botaoProcederParaCompra.isEnabled()) {
				botaoProcederParaCompra.click();
			}

		}

	}

}
