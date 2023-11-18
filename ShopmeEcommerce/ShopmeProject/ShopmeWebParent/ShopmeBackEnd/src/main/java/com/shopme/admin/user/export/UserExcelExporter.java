package com.shopme.admin.user.export;

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
import com.shopme.common.entity.User;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class UserExcelExporter extends AbstractExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public UserExcelExporter() {
		workbook = new XSSFWorkbook();
		
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");
		XSSFRow row = sheet.createRow(0);
		
		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStype.setFont(font);
		
		createCell(row, 0, "User ID", cellStype);
		createCell(row, 1, "E-mail", cellStype);
		createCell(row, 2, "First Name", cellStype);
		createCell(row, 3, "Last Name", cellStype);
		createCell(row, 4, "Roles", cellStype);
		createCell(row, 5, "Enabled", cellStype);
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

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setReponseHeader(response, "application/octet-stream", ".xlsx","users_");

		writeHeaderLine();
		writeDataLines(listUsers);
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<User> listUsers) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		cellStype.setFont(font);
		
		for (User user : listUsers) {
		XSSFRow row = sheet.createRow(rowIndex++);
		int columnIndex = 0;
		
		createCell(row, columnIndex++,user.getId(),cellStype);
		createCell(row, columnIndex++,user.getEmail(),cellStype);
		createCell(row, columnIndex++,user.getFirstName(),cellStype);
		createCell(row, columnIndex++,user.getLastName(),cellStype);
		createCell(row, columnIndex++,user.getRoles().toString(),cellStype);
		createCell(row, columnIndex++,user.isEnabled(),cellStype);
		}
		
	}

}
