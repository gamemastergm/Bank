package br.ucsal.bank.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bank.model.Conta;
import br.ucsal.bank.repository.ContaRepository;

@Controller
@RequestMapping(value = "/funcao")
public class FuncoesConta {
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	@PostMapping("/formSacar")
	public String formSacar(Model model, @Param(value = "id") Long id) {
		Conta conta = new Conta(id, null, null, null, 0, null, null, null, null, null, null, null, null, null);
		if (id != null) {
			Optional<Conta> op = contaRepository.findById(id);
			if (op.isPresent()) {
				conta = op.get();
			}
			
		}
		model.addAttribute("conta", conta);
		return "/sacar";
	}

	@PostMapping("/sacarSalvar")
	public String sacarSalvar(@Valid Conta conta, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaRepository.findAll());
            return "contaform";
        }
		Optional<Conta> cliente = contaRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getSaldo()) - Double.parseDouble(conta.getSaldo());
		cliente.get().setSaldo(resultado+"");
		contaRepository.save(cliente.get()); 

		return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formDepositar")
	public String formDepositar(Model model, @Param(value = "id") Long id) {
		Conta conta = new Conta();
		if (id != null) {
			Optional<Conta> op = contaRepository.findById(id);
			if (op.isPresent()) {
				conta = op.get();
			}
			
		}
		model.addAttribute("conta", conta);
		return "/depositar";
	}

	@PostMapping("/depositarSalvar")
	public String depositarSalvar(@Valid Conta conta, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaRepository.findAll());
            return "contaform";
        }
		Optional<Conta> cliente = contaRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getSaldo()) + Double.parseDouble(conta.getSaldo());
		cliente.get().setSaldo(resultado+"");
		contaRepository.save(cliente.get()); 
		
		return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formEmprestar")
	public String formEmprestar(Model model, @Param(value = "id") Long id) {
		Conta conta = new Conta();
		if (id != null) {
			Optional<Conta> op = contaRepository.findById(id);
			if (op.isPresent()) {
				conta = op.get();
			}
			
		}
		model.addAttribute("conta", conta);
		return "/emprestimo";
	}

	@PostMapping("/emprestarSalvar")
	public String emprestarSalvar(@Valid Conta conta, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaRepository.findAll());
            return "contaform";
        }
		Optional<Conta> cliente = contaRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getDividas()) + Double.parseDouble(conta.getDividas());
		cliente.get().setDividas(resultado+"");
		contaRepository.save(cliente.get()); 

		return "redirect:/cliente/in" + "?id="+ id;
	}

}
