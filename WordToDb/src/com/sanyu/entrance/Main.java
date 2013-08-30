/**
 * 
 */
package com.sanyu.entrance;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

/**
 * @author sycheung
 * 
 */
public class Main {

	public static void main(String arg[]) {
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File());
			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
