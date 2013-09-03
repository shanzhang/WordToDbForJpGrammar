/**
 * 
 */
package com.sanyu.generator;

import java.io.FileWriter;
import java.io.IOException;

import com.sanyu.utils.Constants;

/**
 * @author sycheung
 * 
 */
public class ScriptsGenerator {

	private String outputPath;

	private String table;

	public void ScriptWriter(String[] values) throws IOException {
		String sql = Constants.insertionPrepend + table + Constants.insertionMidpend;
		boolean valid = true;
		for (String item : values) {
			if (item == null || item == "") {
				valid = false;
				break;
			}
			sql = sql + ",'" + item + "'";
		}
		if (valid) {
			sql = sql + Constants.insertionSubpend;
			FileWriter writer = new FileWriter(outputPath, true);
			writer.write(sql);
			writer.write(Constants.lineWrapper);
			writer.close();
		}
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
