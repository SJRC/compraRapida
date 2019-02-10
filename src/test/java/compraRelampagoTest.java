
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

		WebElement botaoLogin = navegador.findElement(By.xpath("//a[@class='btn middle line1 cart_btn']"));

		if (botaoLogin != null && botaoLogin.isDisplayed()) {
			botaoLogin.click();
		}

		WebElement formularioLogin = navegador.findElement(By.xpath("//a[@class='authTab_link active']"));
		WebElement botaoLoginPeloGoogle = navegador.findElement(By.xpath("//i[@class='icon-gplus']"));

		if (formularioLogin.isDisplayed() && botaoLoginPeloGoogle.isEnabled()) {

			tela.trocarParaJanelaPopUp(navegador, botaoLoginPeloGoogle);

			tela.esperaImplicita(navegador);
			WebElement campoUsuario = navegador.findElement(By.id("identifierId"));
			String valorCampoUsuario = "jefferson@inovadorsite.com.br";

			campoUsuario.click();
			tela.esperaImplicita(navegador);
			campoUsuario.sendKeys(valorCampoUsuario);

			WebElement botaoProximo = navegador.findElement(By.id("identifierNext"));
			botaoProximo.click();
			tela.esperaExplicitaPorElementoNaoVisivel(navegador, "//input[@name='password']");

			WebElement campoSenha = navegador.findElement(By.xpath("//input[@name='password']"));
			tela.esperaImplicitaPorId(navegador, campoSenha);

			String valorCampoSenha = "j2468101";

			tela.preencherCampoPorXpath(campoSenha, valorCampoSenha);
			tela.esperaImplicita(navegador);
			WebElement botaoProxima = navegador.findElement(By.xpath("//span[contains(text(),'Próxima')]"));
			botaoProxima.click();
			tela.esperaImplicita(navegador);

			botaoProxima.click();

			tela.esperaImplicita(navegador);
			
			tela.esperaImplicitaPorId(navegador, navegador.findElement(By.xpath("//a[@class='cart_link']")));

			Double precoEsperado = 28.99;
			String precoProdutoTela = "";
			if (navegador.findElement(By.xpath("//a[@class='cart_link']")).isDisplayed()) {
				WebElement preco = navegador.findElement(By.xpath("//p[@class='cart_price']"));
				if (preco.isDisplayed()) {
					precoProdutoTela = preco.getText().trim().replace("$", "");
				}
			}

			if (!precoProdutoTela.isEmpty() && Double.valueOf(precoProdutoTela) > 0) {

				if (precoEsperado == Double.valueOf(precoProdutoTela)) {
					System.out.println("Preço ok");
				} else if (precoEsperado != Double.valueOf(precoProdutoTela)) {
					navegador.navigate().refresh();
				}

			}

		}

	}

}
