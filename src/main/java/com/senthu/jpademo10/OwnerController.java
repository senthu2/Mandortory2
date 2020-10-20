package com.senthu.jpademo10;

import com.senthu.jpademo10.model.Owner;
import com.senthu.jpademo10.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class OwnerController {

    private OwnerService ownerService; // Spring vil selv komme med objektet hertil

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners/","/owners", "owners/index"})
    public String getOwners(Model model){
        System.out.print("Owners, size: ");
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        System.out.println(owners.size());
        return "owners/index";
    }

    @GetMapping("/createOwner")
    public String createOwner(Model model){
        System.out.println("vis owner side");
        Owner owner = new Owner();
        model.addAttribute("owner",owner);
        return "owners/createOwner";
    }
    @PostMapping("/createOwner")
    public String createOwner(@ModelAttribute("owner") Owner owner){
        System.out.println("Opret owner");
        owner.setFirstName(owner.getFirstName());
        ownerService.save(owner);
        return "redirect:/owners/index";
    }
}