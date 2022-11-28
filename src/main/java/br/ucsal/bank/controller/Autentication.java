package br.ucsal.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bank.model.Cliente;
import br.ucsal.bank.model.ContaCorrente;
import br.ucsal.bank.repository.ClienteRepository;

@Controller
@RequestMapping(value = "/auth")
public class Autentication {

	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping("/in")
	public String authentication(@Valid ContaCorrente cliente, BindingResult bindingResult, Model model) {
		System.out.println(cliente.toString());
		if(bindingResult.hasErrors()) {
	        bindingResult.getAllErrors().forEach(a -> System.out.print(a));
	        model.addAttribute("contas", clienteRepository.findAll());
	        return "redirect:/cliente/login";
	    }
		Cliente clienteBackend =clienteRepository.findByCpf(cliente.getCpf());
		if (cliente.getSenhaCliente() == clienteBackend.getSenhaCliente()) {
			return "redirect:/cliente/in ?id="+ clienteBackend.getId();
		}

		return "redirect:/cliente/login";
	}

}
	/*
@PostMapping("/salvar")
public String salvar(@Valid ContaCorrente conta, BindingResult bindingResult, Model model) {
	if(bindingResult.hasErrors()) {
        bindingResult.getAllErrors().forEach(a -> System.out.print(a));
        model.addAttribute("contas", contaRepository.findAll());
        return "clienteform";
    }
	contaCorrenteRepository.save(conta);
	return "index";
}*/