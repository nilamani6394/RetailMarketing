package com.cms.app.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.app.main.Repositatory.PageRepository;
import com.cms.app.main.model.Page;
@Controller
@RequestMapping("/admin/pages")
public class AdminPagesController {
	@Autowired
	private PageRepository pageRepo;
	/*public AdminPagesController(PageRepository pageRepo) {
		this.pageRepo=pageRepo;
	}*/
	
	@GetMapping
	public String Index(Model model) {
		List<Page>pages=pageRepo.findAllByOrderSortingAsc();
		model.addAttribute("pages", pages);
		return"admin/pages/Index";
	}
	@GetMapping("/add")
	public String add( @ModelAttribute Page page) {
		return "admin/pages/add";
		
	}
	
	@PostMapping("/add")
	public String add(@Valid Page page,BindingResult bindingres,RedirectAttributes rdat,Model model) {
		if (bindingres.hasErrors()) {
			return "admin/pages/add";
		}
		rdat.addFlashAttribute("message", "page added");
		rdat.addFlashAttribute("alertClass", "alert-success");
		String slug=page.getSlug()==""?page.getTitle().toLowerCase().replace("", "-"):page.getSlug().toLowerCase();
		Page slugExists = pageRepo.findBySlug(slug);
		if (slugExists!=null) {
			rdat.addFlashAttribute("message", "slug exists. choose another");
			rdat.addFlashAttribute("alertClass", "alert-danger");
			rdat.addFlashAttribute("page", page);
		} else {
           page.setSlug(slug);
           page.setSorting(100);
           pageRepo.save(page);
		}
		
		return "redirect:/admin/pages/add";
		
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Page page=pageRepo.getOne(id);
		model.addAttribute("page",page);
		return "admin/pages/edit";
		
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Page page,BindingResult bindingres,RedirectAttributes rdat,Model model) {
		Page pageCurr= pageRepo.getOne(page.getId());
		if (bindingres.hasErrors()) {
			model.addAttribute("pageTitle", pageCurr.getTitle());
			return "admin/pages/edit";
		}
		rdat.addFlashAttribute("message", "page edited");
		rdat.addFlashAttribute("alertClass", "alert-success");
		String slug=page.getSlug()==""?page.getTitle().toLowerCase().replace("", "-"):page.getSlug().toLowerCase();
		//Page slugExists = pageRepo.findbySlugAndIdNNot(slug,page.getId());
		Page slugExists =pageRepo.findBySlugAndIdNot(slug, page.getId());
		if (slugExists!=null) {
			rdat.addFlashAttribute("message", "slug exists. choose another");
			rdat.addFlashAttribute("alertClass", "alert-danger");
			rdat.addFlashAttribute("page", page);
		} else {
           page.setSlug(slug);
           pageRepo.save(page);
		}
		
		return "redirect:/admin/pages/edit"+page.getId();
		
	}
	@GetMapping("/delete/{id}")
	public String edit(@PathVariable int id, RedirectAttributes rdat) {
		pageRepo.deleteById(id);
		rdat.addFlashAttribute("message", "Delete Success ful");
		rdat.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/pages";
		
	}
	@PostMapping("/rder")
	public @ResponseBody String reorder(@RequestParam("id[]")int []id) {
		int count=1;
		Page page;
		for (int pageId : id) {
			page=pageRepo.getOne(pageId);
			page.setSorting(count);
			pageRepo.save(page);
			count++;
		}
		return "ok";
		}

}
