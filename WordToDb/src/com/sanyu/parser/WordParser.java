/**
 * 
 */
package com.sanyu.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.sanyu.generator.ScriptsGenerator;
import com.sanyu.utils.Constants;

/**
 * @author sycheung
 * 
 */
public class WordParser {

	private String filePath;

	private File file;

	private WordExtractor extractor;

	private XWPFWordExtractor xExtractor;

	private boolean lvlFlag;

	private ScriptsGenerator generator;

	private Pattern patternTitle = Pattern.compile("^[1-9]d*");

	private Matcher matcher;

	public void parseProcess() {
		if (lvlFlag) {
			setFilePath(Constants.n1File);
			generator = new ScriptsGenerator();
			generator.setOutputPath(Constants.n1Out);
			generator.setTable(Constants.n1Table);
			ParseProcessN1();
		} else {
			setFilePath(Constants.n2File);
			generator = new ScriptsGenerator();
			generator.setOutputPath(Constants.n2Out);
			generator.setTable(Constants.n2Table);
			ParseProcessN2();
		}
	}

	public void ParseProcessN1() {
		try {
			file = new File(filePath);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			HWPFDocument document = new HWPFDocument(fis);
			extractor = new WordExtractor(document);
			String[] fileData = extractor.getParagraphText();
			String title = null;
			String text = "";
			for (int i = 0; i < fileData.length; i++) {
				matcher = patternTitle.matcher(fileData[i]);
				if (fileData[i] != null && matcher.find()) {
					generator.ScriptWriter(new String[] { title, text.replace(";", "；") });
					// title = Constants.gramQuoter +
					// fileData[i].split(Constants.gramQuoter)[1];
					title = fileData[i];
					text = "";
				} else if (fileData[i] != null) {
					text = text + fileData[i];
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ParseProcessN2() {
		try {
			file = new File(filePath);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			XWPFDocument document = new XWPFDocument(fis);
			xExtractor = new XWPFWordExtractor(document);
			String[] fileData = xExtractor.getText().split(Constants.lineWrapperN2);
			String title = null;
			String text = "";
			for (int i = 0; i < fileData.length; i++) {
				matcher = patternTitle.matcher(fileData[i]);
				if (fileData[i] != null && matcher.find()) {
					generator.ScriptWriter(new String[] { title, text.replace(";", "；") });
					title = fileData[i];
					text = "";
				} else if (fileData[i] != null) {
					text = text + fileData[i] + Constants.lineWrapper;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean getLvlFlag() {
		return lvlFlag;
	}

	public void setLvlFlag(boolean lvlFlag) {
		this.lvlFlag = lvlFlag;
	}

	public boolean levelGetter() {
		return lvlFlag == true ? true : false;
	}

}
