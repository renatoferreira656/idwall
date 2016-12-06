package br.com.idwall.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.idwall.util.FileUtil;
import br.com.idwall.util.StringFormmater;

public class AllTests {

	@Test
	public void testReadFile() {
		assertEquals("dummy", FileUtil.readFile("dummy.txt", "UTF-8"));
	}
	
	@Test
	public void testFourtyCharacters(){
		String fileInput = FileUtil.readFile("input.txt");
		String resultFourtyLines = FileUtil.readFile("resultFourtyCharacters.txt");
		String result = StringFormmater.build(fileInput).columns(40).result();
		assertEquals(resultFourtyLines, result);
	}
	
	@Test
	public void testFourtyCharactersJustified(){
		String fileInput = FileUtil.readFile("input.txt");
		String resultFourtyLines = FileUtil.readFile("resultFourtyCharactersJustified.txt");
		String result = StringFormmater.build(fileInput).columns(40).justify().result();
		assertEquals(resultFourtyLines, result);
	}
}