package web.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.practice.domain.Item;
import web.practice.domain.ItemRepository;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/product")
public class Controller {

    private final ItemRepository itemRepository;

    @Autowired // 생성자 주입
    public Controller(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String products(Model model){
        List<Item> products = itemRepository.findAll();
        model.addAttribute("products", products);
        return "product/items";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("product", new Item());
        return "product/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("product") Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "product/addForm";
        }

        if(!StringUtils.hasText(item.getTitle())){
            bindingResult.rejectValue("title", "required.item.itemName");
        }
        if(!StringUtils.hasText(item.getContent())){
            bindingResult.rejectValue("content", "range.item.price");
        }

        if(bindingResult.hasErrors()){
            return "/product/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("productId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/product/{productId}";
    }

    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable long productId, Model model){
        Item item = itemRepository.findById(productId);
        model.addAttribute("product", item);
        return "product/editForm";
    }

    @PostMapping("/{productId}/edit")
    public String edit(@PathVariable long productId, @ModelAttribute("product") Item item, BindingResult bindingResult){

        if(!StringUtils.hasText(item.getTitle())){
            bindingResult.rejectValue("title", "required");
        }
        if(!StringUtils.hasText(item.getContent())){
            bindingResult.rejectValue("content", "range.item.price");
        }
        if(bindingResult.hasErrors()){
            return "product/editForm";
        }

        itemRepository.update(productId, item);
        return "redirect:/product/{productId}";
    }

    @GetMapping("/{productId}")
    public String item(@PathVariable long productId, Model model){
        Item item = itemRepository.findById(productId);
        model.addAttribute("product", item);
        return "product/item";
    }
}
