package com.shopme.admin.category.export;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shopme.admin.AbstractExporter;
import com.shopme.common.entity.Category;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryExcelExporter extends AbstractExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public CategoryExcelExporter() {
		workbook = new XSSFWorkbook();
		
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Category");
		XSSFRow row = sheet.createRow(0);
		
		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStype.setFont(font);
		
		createCell(row, 0, "Category ID", cellStype);
		createCell(row, 1, "Category Name", cellStype);
	}

	private void createCell(XSSFRow row, int columnIndex, Object value,CellStyle style) {
		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);
		
		if(value instanceof Integer) {
			cell.setCellValue((Integer)value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String)value);
		}
		
		cell.setCellStyle(style);
	}

	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {
		super.setReponseHeader(response, "application/octet-stream", ".xlsx","categories_");

		writeHeaderLine();
		writeDataLines(listCategories);
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Category> listCategories) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		cellStype.setFont(font);
		
		for (Category category : listCategories) {
		XSSFRow row = sheet.createRow(rowIndex++);
		int columnIndex = 0;
		
		createCell(row, columnIndex++,category.getId(),cellStype);
		createCell(row, columnIndex++,category.getName(),cellStype);
		}
		
	}

}
