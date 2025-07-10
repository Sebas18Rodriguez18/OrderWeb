package com.order.orderWeb.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.order.orderWeb.model.Activity;
import com.order.orderWeb.repository.ActivityRepository;
import com.order.orderWeb.repository.TechnicianRepository;
import com.order.orderWeb.repository.TypeActivityRepository;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ViewActivity {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    TypeActivityRepository typeActivityRepository;

    @GetMapping("/view/activity")
    public String list(Model model) {
        model.addAttribute("activity", activityRepository.findAll());
        return "activity";
    }

    @GetMapping("/viewA/form")
    public String form(Model model){
        model.addAttribute("activity", new Activity());
        model.addAttribute("technician", technicianRepository.findAll());
        model.addAttribute("type_activity", typeActivityRepository.findAll());
        return "activity_form";
    }

    @PostMapping("/viewA/save")
    public String save(@ModelAttribute("activity") Activity activity, RedirectAttributes ra){
        activityRepository.save(activity);
        ra.addFlashAttribute("success", "Actividad guardada correctamente");
        return "redirect:/view/activity";
    }

    @GetMapping("/viewA/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Activity activity = activityRepository.findById(id).orElse(null);
        model.addAttribute("activity", activity);
        model.addAttribute("technician", technicianRepository.findAll());
        model.addAttribute("type_activity", typeActivityRepository.findAll());
        return "activity_form";
    }

    @PostMapping("/viewA/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra){
        activityRepository.deleteById(id);
        ra.addFlashAttribute("success", "Actividad eliminada correctamente");
        return "redirect:/view/activity";
    }

    @GetMapping("/viewA/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ActivityList.pdf");

        List<Activity> activityList = activityRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Listado de Actividades"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        table.addCell("ID");
        table.addCell("Descripción");
        table.addCell("Fecha");
        table.addCell("Técnico");
        table.addCell("Tipo de Actividad");

        for (Activity a : activityList) {
            table.addCell(a.getId().toString());
            table.addCell(a.getDescription());
            table.addCell(String.valueOf(a.getHours()));
            table.addCell(a.getTechnician() != null ? a.getTechnician().getName() : "N/A");
            table.addCell(a.getType_activity() != null ? a.getType_activity().getDescription() : "N/A");
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/viewA/excel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ActivityList.xlsx");

        List<Activity> activityList = activityRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Actividades");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Descripción");
        header.createCell(2).setCellValue("Fecha");
        header.createCell(3).setCellValue("Técnico");
        header.createCell(4).setCellValue("Tipo de Actividad");

        int rowNum = 1;
        for (Activity a : activityList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(a.getId());
            row.createCell(1).setCellValue(a.getDescription());
            row.createCell(2).setCellValue(a.getHours().toString());
            row.createCell(3).setCellValue(a.getTechnician() != null ? a.getTechnician().getName() : "N/A");
            row.createCell(4).setCellValue(a.getType_activity() != null ? a.getType_activity().getDescription() : "N/A");
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
