package br.com.resrouceit.pages.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tela {

	public void esperaExplicitaPeloElementoAlocado(WebDriver driver, String propriedadePorId) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(propriedadePorId)));
	}

	public void esperarAteOElementoSerPossivelOCick(WebDriver driver, String propriedadePorId) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(propriedadePorId)));
	}
	
	public void esperarAteOElementoSerPossivelOCick(WebDriver driver, WebElement elementHtml) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(elementHtml));
	}

	public void navegarAteUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public void exibindoValorNoConsole(String stringExibida) {
		System.out.println(stringExibida);
	}
	
	

}
