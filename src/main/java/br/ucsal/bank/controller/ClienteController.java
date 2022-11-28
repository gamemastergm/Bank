package br.ucsal.bank.controller;

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
import br.ucsal.bank.model.ContaCorrente;
import br.ucsal.bank.repository.ClienteRepository;
import br.ucsal.bank.repository.ContaCorrenteRepository;
import br.ucsal.bank.repository.ContaRepository;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@GetMapping("/list")
	public String listClientes(Model model) {
		Optional<Cliente> cliente = clienteRepository.findById(1L);
		model.addAttribute("clientes", cliente.get());
		return "/cliente";
	}
	
	@GetMapping("/in")
	public String inCliente(Model model,@Param(value = "id") Long id) {
		/*Optional<Cliente> cliente = clienteRepository.findById(id);
	       if( cliente.isPresent() ) {
	            model.addAttribute("clientes", cliente.get());
	}*/
		return "/cliente";
		//return "/cliente ?id="+id;
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		ContaCorrente cliente = new ContaCorrente(null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null);
		model.addAttribute("cliente", cliente);
		return "/login";
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
	public String salvar(@Valid ContaCorrente conta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("contas", contaRepository.findAll());
            return "clienteform";
        }
		contaCorrenteRepository.save(conta);
		return "index";
	}
	
	

	@GetMapping("/delete")
	public String delete(Long id) {
		clienteRepository.deleteById(id);
		return "redirect:/cliente/list";
	}

}
