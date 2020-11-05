package com.senthu.jpademo10;

import com.senthu.jpademo10.model.Owner;
import com.senthu.jpademo10.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
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
    @RequestMapping("/delete/{id}")
    public String deleteOwner(@PathVariable(value = "id") long id){
        System.out.println("slet Owner");
        ownerService.deleteById(id);
        return "redirect:/owners/index";
    }

}
