package br.com.idwall.util;

public class StringFormmater {

	private String text;
	private Integer columns;
	private boolean justify = false;

	public static StringFormmater build(String text) {
		StringFormmater formmater = new StringFormmater();
		formmater.text = text;
		return formmater;

	}

	public StringFormmater columns(Integer columns) {
		this.columns = columns;
		return this;
	}

	public String result() {
		String result = text;
		if(columns != null){
			result = formatCharactersSize(result);
			if(justify){
				result = justify(result);
			}
		}
		return result;
	}

	private String justify(String text) {
		StringBuilder result = new StringBuilder();
		String[] paragraphs = getParagraphs(text);
		for (String paragraph : paragraphs) {
			String[] lines = getLines(paragraph);
			for (String line : lines) {
				if(line.length() == this.columns){
					result.append(line);
				} else {
					line = addWhiteSpaces(line);
					result.append(line);
				}
				result.append("\n");
			}
			result.append("\n");
		}
		return result.toString().trim();
	}

	private String addWhiteSpaces(String line) {
		String[] words = getWordsFromLines(line);
		Integer missingChars = this.columns - line.length();
		int maxSize = words.length -1;
		Integer addingToEachSpace = missingChars / maxSize;
		Integer extraSpace = missingChars % maxSize;
		StringBuilder newLine = new StringBuilder();
		for (String word : words) {
			newLine.append(word);
			addSpace(addingToEachSpace, newLine);
			if(extraSpace > 0){
				addExtraSpace(1, newLine);
				extraSpace--;
			}
		}
		return newLine.toString().trim();
	}

	private void addSpace(Integer extraSpace, StringBuilder newLine) {
		for(int i = extraSpace; i >= 0 ; i--){
			newLine.append(" ");
		}
	}
	
	private void addExtraSpace(Integer extraSpace, StringBuilder newLine) {
		for(int i = extraSpace; i > 0 ; i--){
			newLine.append(" ");
		}
	}
	
	private String formatCharactersSize(String text) {
		StringBuilder result = new StringBuilder();
		String[] paragraphs = getParagraphs(text);
		for (String paragraph : paragraphs) {
			String[] words = getWordsFromLines(paragraph);
			for (String word : words) {
				int sizeLastLine = result.length() - result.lastIndexOf("\n");
				if(sizeLastLine + word.length() <= this.columns){
					if(sizeLastLine != 1){
						result.append(" ");
					}
				} else {
					removeLastCharacter(result);
					result.append("\n");
				}
				result.append(word);
			}
			result.append("\n\n");
		}
		return result.toString().trim();
	}

	private String[] getWordsFromLines(String lines) {
		return lines.split("\\r|\\n|\\s");
	}

	private String[] getParagraphs(String text) {
		return text.split("\\n{2}|\\r{2}");
	}
	
	private String[] getLines(String paragraph) {
		return paragraph.split("\\n|\\r");
	}


	private void removeLastCharacter(StringBuilder result) {
		if(result.lastIndexOf(" ") == result.length() -1){
			result.replace(result.length() -1, result.length(), "");
		}
	}

	public StringFormmater justify() {
		this.justify = true;
		return this;
	}
}
