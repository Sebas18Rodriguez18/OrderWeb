package com.order.orderWeb.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.order.orderWeb.model.Order;
import com.order.orderWeb.repository.OrderRepository;
import com.order.orderWeb.repository.CausalRepository;
import com.order.orderWeb.repository.ObservationRepository;

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
public class ViewOrder {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CausalRepository causalRepository;

    @Autowired
    ObservationRepository observationRepository;

    @GetMapping("/view/order")
    public String list(Model model) {
        model.addAttribute("order", orderRepository.findAll());
        return "order";
    }

    @GetMapping("/viewO/form")
    public String form(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("causals", causalRepository.findAll());
        model.addAttribute("observations", observationRepository.findAll());
        return "order_form";
    }

    @PostMapping("/viewO/save")
    public String save(@ModelAttribute Order order, RedirectAttributes ra) {
        orderRepository.save(order);
        ra.addFlashAttribute("success", "Orden guardada correctamente");
        return "redirect:/view/order";
    }

    @GetMapping("/viewO/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        model.addAttribute("order", order);
        model.addAttribute("causals", causalRepository.findAll());
        model.addAttribute("observations", observationRepository.findAll());
        return "order_form";
    }

    @PostMapping("/viewO/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        orderRepository.deleteById(id);
        ra.addFlashAttribute("success", "Orden eliminada correctamente");
        return "redirect:/view/order";
    }

    @GetMapping("/viewO/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=OrderList.pdf");

        List<Order> orders = orderRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Listado de Órdenes"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        table.addCell("ID");
        table.addCell("Fecha Legalización");
        table.addCell("Dirección");
        table.addCell("Ciudad");
        table.addCell("Causal");
        table.addCell("Observación");

        for (Order o : orders) {
            table.addCell(o.getId().toString());
            table.addCell(o.getLegalizationDate().toString());
            table.addCell(o.getAddress());
            table.addCell(o.getCity());
            table.addCell(o.getCausal() != null ? o.getCausal().getDescription() : "N/A");
            table.addCell(o.getObservation() != null ? o.getObservation().getDescription() : "N/A");
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/viewO/excel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=OrderList.xlsx");

        List<Order> orders = orderRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Órdenes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Fecha");
        header.createCell(2).setCellValue("Dirección");
        header.createCell(3).setCellValue("Ciudad");
        header.createCell(4).setCellValue("Causal");
        header.createCell(5).setCellValue("Observación");

        int rowNum = 1;
        for (Order o : orders) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(o.getId());
            row.createCell(1).setCellValue(o.getLegalizationDate().toString());
            row.createCell(2).setCellValue(o.getAddress());
            row.createCell(3).setCellValue(o.getCity());
            row.createCell(4).setCellValue(o.getCausal() != null ? o.getCausal().getDescription() : "N/A");
            row.createCell(5).setCellValue(o.getObservation() != null ? o.getObservation().getDescription() : "N/A");
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
