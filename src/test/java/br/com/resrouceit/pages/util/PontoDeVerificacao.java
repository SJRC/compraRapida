package br.com.resrouceit.pages.util;

import org.junit.Assert;

public class PontoDeVerificacao {
	
	public void verificarValoresEsperados(Boolean valorEsperado,Boolean valorAtual) {
		Assert.assertEquals(valorEsperado, valorAtual);
	}

}
