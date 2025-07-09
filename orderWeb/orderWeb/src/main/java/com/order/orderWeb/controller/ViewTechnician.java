package com.order.orderWeb.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.order.orderWeb.model.Technician;
import com.order.orderWeb.repository.TechnicianRepository;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ViewTechnician {
    @Autowired
    TechnicianRepository technicianRepository;

    @GetMapping("/view/technician")
    public String list(Model model) {
        model.addAttribute("technician", technicianRepository.findAll());
        return "technician";
    }

    @GetMapping("viewT/form")
    public String form(Model model) {
        model.addAttribute("technician", new Technician());
        return "technician_form";
    }

    @PostMapping("/viewT/save")
    public String save(@ModelAttribute Technician technician, RedirectAttributes ra) {
        technicianRepository.save(technician);
        ra.addFlashAttribute("success", "Technician Saved");
        return "redirect:/view/technician";
    }

    @GetMapping("/viewT/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Technician technician = technicianRepository.findById(id).orElse(null);
        model.addAttribute("technician", technician);
        return "technician_form";
    }

    @PostMapping("/viewT/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        technicianRepository.deleteById(id);
        ra.addFlashAttribute("success", "Technician Deleted");
        return "redirect:/view/technician";
    }

    @GetMapping("/viewT/pdf")
    public void exportarPDF(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Technician.pdf");

        List<Technician> technicianList = technicianRepository.findAll(); // Asegúrate de tener fichaRepository

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("List of Tecnician"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5); // ajusta las columnas necesarias
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        // Encabezados
        table.addCell("ID Tecnician");
        table.addCell("Document");
        table.addCell("Name");
        table.addCell("Speciality");
        table.addCell("Phone");

        // Filas
        for (Technician f : technicianList) {
            table.addCell(f.getId().toString());
            table.addCell(f.getDocument().toString());
            table.addCell(f.getName());
            table.addCell(f.getSpeciality());
            table.addCell(f.getPhone());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/viewT/excel")
    public void exportarExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Technician.xlsx");

        List<Technician> technicianList = technicianRepository.findAll(); // Reemplaza con tu repositorio

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tecnician");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID Tecnician");
        headerRow.createCell(1).setCellValue("Document");
        headerRow.createCell(2).setCellValue("Name");
        headerRow.createCell(3).setCellValue("Speciality");
        headerRow.createCell(4).setCellValue("Phone");

        // Agregar datos
        int rowNum = 1;
        for (Technician technician : technicianList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(technician.getId());
            row.createCell(1).setCellValue(technician.getDocument());
            row.createCell(2).setCellValue(technician.getName());
            row.createCell(3).setCellValue(technician.getSpeciality());
            row.createCell(4).setCellValue(technician.getPhone());
        }

        // Autoajustar columnas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
