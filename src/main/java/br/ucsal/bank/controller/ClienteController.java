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

import br.ucsal.bank.model.Cliente;
import br.ucsal.bank.repository.ClienteRepository;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/list")
	public String listClientes(Model model) {
		List<Cliente> clientes = clienteRepository.findAll();
		model.addAttribute("clientes", clientes);
		return "/cliente";
	}

	@GetMapping("/form")
	public String form(Model model, @Param(value = "id") Long id) {
		Cliente cliente = new Cliente() {
		};
		if (id != null) {
			Optional<Cliente> op = clienteRepository.findById(id);
			if (op.isPresent()) {
				cliente = op.get();
			}
			
		}
		model.addAttribute("cliente", cliente);
		return "/register";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("clientes", clienteRepository.findAll());
            return "clienteform";
        }
		clienteRepository.save(cliente); 
		return "redirect:/cliente/list";
	}
	
	

	@GetMapping("/delete")
	public String delete(Long id) {
		clienteRepository.deleteById(id);
		return "redirect:/cliente/list";
	}

}
