package br.com.java.compra.relampago.gearbest.pages.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tela {

	public void esperaImplicita(WebDriver navegador) {
		navegador.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
	}

	public void trocarParaJanelaPopUp(WebDriver navegador, WebElement elementoQueAbreNovaJanela) {
		// Guardando o windowHandle da janela inicial, porque vou precisar mudar pra ela
		// no final do teste e, além disso, vou usar esse valor para descobrir o
		// windowHandle da nova janela
		String windowHandleJanelaInicial = getwindowHandleJanelaAtual(navegador);
		
		

		// Clico no elemento que abre a nova janela
		elementoQueAbreNovaJanela.click();

		// Qual é o windowHandle da nova janela? Não sei. Vamos pegar então todos os
		// windowHandles e guardar numa lista. Se só temos a janela inicial e a nova
		// abertas, essa lista vai ter apenas 2 elementos. Para descobrir o windowHandle
		// da janela nova, basta percorrer a lista e achar o elemento que NÃO seja o
		// windowHandle da janela inicial.
		Set<String> windowHandles = navegador.getWindowHandles();

		// Para cada windowHandle dentro da lista de windowHandles...
		for (String windowHandle : windowHandles) {

			// Se o windowHandle NÃO é igual ao windowHandle da janela inicial, eu sei que é
			// o da nova janela, então mando o WebDriver mudar para ela
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				navegador.switchTo().window(windowHandle);
				break;
			}
		}
		
		maximarNavegador(navegador);

	}

	public String getwindowHandleJanelaAtual(WebDriver navegador) {
		String windowHandleJanelaInicial = navegador.getWindowHandle();
		return windowHandleJanelaInicial;
	}

	

	public void fecharJanela(WebDriver navegador) {
		navegador.close();
	}

	public void maximarNavegador(WebDriver navegador) {
		// Maximando o navegador
		navegador.manage().window().maximize();
	}

	public void esperarAteQuandoOElementoEstiverVisivel(WebDriver navegador, WebElement element) {

		WebDriverWait wait = new WebDriverWait(navegador, 20); // here, wait time is 20 seconds

		wait.until(ExpectedConditions.visibilityOf(element)); // this will wait for elememt to be visible for 20 seconds

	}
	
	
	public void esperaExplicitaPorElementoNaoVisivel(WebDriver navegador, String strXpath) {
		WebDriverWait wait = new WebDriverWait(navegador, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strXpath)));

		

	}
	
	public void preencherCampoPorXpath(WebElement field,String strValueByXpath ) {
		field.sendKeys(strValueByXpath);
	}
}
