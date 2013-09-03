package com.sanyu.entrance;

import com.sanyu.parser.WordParser;
import com.sanyu.utils.Constants;

public class Main {

	private static WordParser n1Parser;

	private static WordParser n2Parser;

	public static void n1Parse() {
		n1Parser = new WordParser();
		n1Parser.setLvlFlag(Constants.n1Flag);
		n1Parser.parseProcess();
	}

	public static void n2Parse() {
		n2Parser = new WordParser();
		n2Parser.setLvlFlag(Constants.n2Flag);
		n2Parser.parseProcess();
	}

	public static void main(String[] args) {
		n1Parse();
		n2Parse();
	}
}