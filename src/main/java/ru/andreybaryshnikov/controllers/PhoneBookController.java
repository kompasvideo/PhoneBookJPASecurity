package ru.andreybaryshnikov.controllers;

import ru.andreybaryshnikov.models.PhoneBook;
import ru.andreybaryshnikov.services.PhoneBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PhoneBookController {
    private PhoneBookService phoneBookService;

    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping(value = "/")
    public String showFirstView(Model model) {
        Iterable<PhoneBook> phoneBooks =  phoneBookService.getPhoneBooks();
        model.addAttribute("phoneBooks",phoneBooks);
        model.addAttribute("header", "Notebook");
        model.addAttribute("addRecord","Add record");
        model.addAttribute("editRecord","Edit");
        model.addAttribute("delRecord","Delete");
        return "index";
    }

    @PostMapping(value = "/viewRecord")
    public String viewRecord(@RequestParam("id") int id, Model model) {
        model.addAttribute("phoneBook", phoneBookService.getPhoneBook(id));
        model.addAttribute("header", "Notebook");
        return "view-record";
    }

    @PostMapping(value = "/deleteRecord")
    public String deleteRecord(@RequestParam("id") int id, Model model) {
        phoneBookService.deleteRecordToPhoneBooks(id);
        return "redirect:/";
    }

    @PostMapping(value = "/editRecord")
    public String editRecord(@RequestParam("id") int id, Model model) {
        model.addAttribute("phoneBook", phoneBookService.getPhoneBook(id));
        model.addAttribute("header", "Notebook");
        model.addAttribute("editRecord","Edit record");//"Добавить запись");
        return "edit-record";
    }

    @PostMapping(value = "/editSaveRecord")
    public String editSaveRecord(@ModelAttribute("phoneBook") PhoneBook phoneBook, Model model) {
        phoneBookService.editRecordToPhoneBooks(phoneBook);
        return "redirect:/";
    }
    @GetMapping("/viewAddRecord")
    public String viewAddRecord(Model model) {
        model.addAttribute("phoneBook", phoneBookService.getNewPhoneBook());
        model.addAttribute("header", "Notebook");
        model.addAttribute("addRecord","Add record");//"Добавить запись");
        return "view-add-record";
    }
    @PostMapping(value = "/adaSaveRecord")
    public String addSaveRecord(@ModelAttribute("phoneBook") PhoneBook phoneBook, Model model) {
        phoneBookService.addRecordToPhoneBooks(phoneBook);
        return "redirect:/";
    }
}
