package employee_management.export;

import employee_management.entity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class EmployeeExcelExporter {

    private final List<Employee> employees;

    public EmployeeExcelExporter(List<Employee> employees) {
        this.employees = employees;
    }

    public void export(HttpServletResponse response)
            throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Employees");

        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("Last Name");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Designation");
        headerRow.createCell(5).setCellValue("Salary");

        int rowCount = 1;

        for (Employee employee : employees) {

            Row row = sheet.createRow(rowCount++);

            row.createCell(0)
                    .setCellValue(employee.getId());

            row.createCell(1)
                    .setCellValue(employee.getFirstName());

            row.createCell(2)
                    .setCellValue(employee.getLastName());

            row.createCell(3)
                    .setCellValue(employee.getEmail());

            row.createCell(4)
                    .setCellValue(employee.getDesignation());

            row.createCell(5)
                    .setCellValue(employee.getSalary());
        }

        ServletOutputStream outputStream =
                response.getOutputStream();

        workbook.write(outputStream);

        workbook.close();

        outputStream.close();
    }
}