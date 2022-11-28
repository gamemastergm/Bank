package br.ucsal.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bank.model.Conta;
import br.ucsal.bank.repository.ContaRepository;

@Controller
@RequestMapping(value = "/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/list")
	public String listContas(Model model) {
		List<Conta> contas = contaRepository.findAll();
		model.addAttribute("contas", contas);
		return "/conta";
	}

	@GetMapping("/form")
	public String form(Model model, @Param(value = "id") Long id) {
		Conta conta = new Conta(id, null, null, null, 0, null, null, null, null, null, null, null, null, null);
		if (id != null) {
			Optional<Conta> op = contaRepository.findById(id);
			if (op.isPresent()) {
				conta = op.get();
			}
			
		}
		model.addAttribute("conta", conta);
		return "/contaform";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Conta conta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaRepository.findAll());
            return "contaform";
        }
		contaRepository.save(conta); 
		return "redirect:/conta";
	}
	
	

	@GetMapping("/delete")
	public String delete(Long id) {
		contaRepository.deleteById(id);
		return "redirect:/conta";
	}
	
}
