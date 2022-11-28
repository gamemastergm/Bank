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

import br.ucsal.bank.model.ContaCorrente;
import br.ucsal.bank.repository.ContaCorrenteRepository;

@Controller
@RequestMapping(value = "/contacorrente")
public class ContaCorrenteController {
	
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
		
	@GetMapping("/list")
	public String listClientes(Model model) {
		List<ContaCorrente> contaCorrentes = contaCorrenteRepository.findAll();
		model.addAttribute("contaCorrentes", contaCorrentes);
		return "/contacorrente";
	}
	
	@GetMapping("/in")
	public String inCliente(Model model,@Param(value = "id") Long id) {
		Optional<ContaCorrente> contaCorrente = contaCorrenteRepository.findById(id);
	       if( contaCorrente.isPresent() ) {
	            model.addAttribute("clientes", contaCorrente.get());
	}
	       
		return "/contacorrente";
	}
	
	@GetMapping("/login")
	public String login(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente(null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null);
		model.addAttribute("contaCorrente", contaCorrente);
		return "/register";
	}

	@GetMapping("/form")
	public String form(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente(null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null);
		if (id != null) {
			Optional<ContaCorrente> op = contaCorrenteRepository.findById(id);
			if (op.isPresent()) {
				contaCorrente = op.get();
			}
			
		}
		model.addAttribute("contaCorrente", contaCorrente);
		return "/register";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid ContaCorrente contaCorrente, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("contaCorrentes", contaCorrenteRepository.findAll());
            return "clienteform";
        }
		contaCorrenteRepository.save(contaCorrente);
		return "index";
	}
	
	

	@GetMapping("/delete")
	public String delete(Long id) {
		contaCorrenteRepository.deleteById(id);
		return "redirect:/contacorrente/list";
	}

}
