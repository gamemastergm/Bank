package br.ucsal.bank.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bank.model.Cliente;
import br.ucsal.bank.model.Conta;
import br.ucsal.bank.model.ContaCorrente;
import br.ucsal.bank.model.ContaPoupanca;
import br.ucsal.bank.repository.ClienteRepository;
import br.ucsal.bank.repository.ContaCorrenteRepository;
import br.ucsal.bank.repository.ContaPoupancaRepository;
import br.ucsal.bank.repository.ContaRepository;



@Controller
@RequestMapping(value = "/funcao")
public class FuncoesConta {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@Autowired
	private ContaPoupancaRepository contaPoupancaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@PostMapping("/formSacar")
	public String formSacar(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente();
		if (id != null) {
			Optional<ContaCorrente> op = contaCorrenteRepository.findById(id);
			if (op.isPresent()) {
				contaCorrente = op.get();
			}
			
		}
		model.addAttribute("conta", contaCorrente);
		return "/sacar";
	}

	@PostMapping("/sacarSalvar")
	public String sacarSalvar(@Valid ContaCorrente contaCorrente, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		Optional<ContaCorrente> cliente = contaCorrenteRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") - Double.parseDouble(contaCorrente.getSaldo());
		cliente.get().setSaldo(resultado+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um saque de R$"+ contaCorrente.getSaldo()+"\n");
		contaCorrenteRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formDepositar")
	public String formDepositar(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente();
		if (id != null) {
			Optional<ContaCorrente> op = contaCorrenteRepository.findById(id);
			if (op.isPresent()) {
				contaCorrente = op.get();
			}
			
		}
		model.addAttribute("conta", contaCorrente);
		return "/depositar";
	}

	@PostMapping("/depositarSalvar")
	public String depositarSalvar(@Valid ContaCorrente contaCorrente, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		Optional<ContaCorrente> cliente = contaCorrenteRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") + Double.parseDouble(contaCorrente.getSaldo());
		cliente.get().setSaldo(resultado+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um deposito de R$"+ contaCorrente.getSaldo()+"\n");
		contaCorrenteRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formEmprestar")
	public String formEmprestar(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente();
		if (id != null) {
			Optional<ContaCorrente> op = contaCorrenteRepository.findById(id);
			if (op.isPresent()) {
				contaCorrente = op.get();
			}
			
		}
		model.addAttribute("conta", contaCorrente);
		return "/emprestimo";
	}

	@PostMapping("/emprestarSalvar")
	public String emprestarSalvar(@Valid ContaCorrente contaCorrente, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		Optional<ContaCorrente> cliente = contaCorrenteRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getDividas() == null ? "0" : cliente.get().getDividas()+"") + Double.parseDouble(contaCorrente.getDividas());
		cliente.get().setDividas(resultado+"");
		double resultado2 = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") + Double.parseDouble(contaCorrente.getDividas());
		cliente.get().setSaldo(resultado2+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um emprestimo de R$"+ contaCorrente.getDividas()+"\n");
		contaCorrenteRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formPagarDivida")
	public String formPagarDivida(Model model, @Param(value = "id") Long id) {
		ContaCorrente contaCorrente = new ContaCorrente();
		if (id != null) {
			Optional<ContaCorrente> op = contaCorrenteRepository.findById(id);
			if (op.isPresent()) {
				contaCorrente = op.get();
			}
			
		}
		model.addAttribute("conta", contaCorrente);
		return "/pagardivida";
	}

	@PostMapping("/pagarDividaSalvar")
	public String pagarDividaSalvar(@Valid ContaCorrente contaCorrente, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		Optional<ContaCorrente> cliente = contaCorrenteRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getDividas() == null ? "0" : cliente.get().getDividas()+"") - Double.parseDouble(contaCorrente.getSaldo());
		double resultado2 = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") - Double.parseDouble(contaCorrente.getSaldo());
		cliente.get().setDividas(resultado+"");
		cliente.get().setSaldo(resultado2+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um pagamento da divida de R$"+ contaCorrente.getSaldo()+"\n");
		contaCorrenteRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formDepositoPoupanca")
	public String formDepositoPoupanca(Model model, @Param(value = "id") Long id) {
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		if (id != null) {
			Optional<ContaPoupanca> op = contaPoupancaRepository.findById(id);
			if (op.isPresent()) {
				contaPoupanca = op.get();
			}
			
		}
		model.addAttribute("conta", contaPoupanca);
		return "/depositarpoupanca";
	}

	@PostMapping("/depositoPoupancaSalvar")
	public String depositoPoupancaSalvar(@Valid ContaPoupanca contaPoupanca, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		System.out.println("ENTROUUUU");
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		System.out.println(contaPoupanca);
		Optional<ContaPoupanca> cliente = contaPoupancaRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getReserva() == null ? "0" : cliente.get().getReserva()+"") + Double.parseDouble(contaPoupanca.getReserva());
		double resultado2 = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") - Double.parseDouble(contaPoupanca.getReserva());
		cliente.get().setReserva(resultado+"");
		cliente.get().setSaldo(resultado2+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um deposito na conta poupança de R$"+ contaPoupanca.getReserva()+"\n");
		contaPoupancaRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/formSacarPoupanca")
	public String formSacarPoupanca(Model model, @Param(value = "id") Long id) {
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		if (id != null) {
			Optional<ContaPoupanca> op = contaPoupancaRepository.findById(id);
			if (op.isPresent()) {
				contaPoupanca = op.get();
			}
			
		}
		model.addAttribute("conta", contaPoupanca);
		return "/sacarpoupanca";
	}

	@PostMapping("/sacarPoupancaSalvar")
	public String sacarPoupancaSalvar(@Valid ContaPoupanca contaPoupanca, BindingResult bindingResult, Model model, @Param(value = "id") Long id) {
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(a -> System.out.print(a));
            model.addAttribute("conta", contaCorrenteRepository.findAll());
            return "contaform";
        }
		Optional<ContaPoupanca> cliente = contaPoupancaRepository.findById(id);
		double resultado = Double.parseDouble(cliente.get().getReserva() == null ? "0" : cliente.get().getReserva()+"") - Double.parseDouble(contaPoupanca.getReserva());
		double resultado2 = Double.parseDouble(cliente.get().getSaldo() == null ? "0" : cliente.get().getSaldo()+"") + Double.parseDouble(contaPoupanca.getReserva());
		
		cliente.get().setReserva(resultado+"");
		cliente.get().setSaldo(resultado2+"");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		cliente.get().getHistorico().add(localDateTime + " Foi feito um saque na conta poupança de R$"+ contaPoupanca.getReserva()+"\n");
		contaPoupancaRepository.save(cliente.get()); 
		return "redirect:/cliente/in?id="+ id;
		//return "redirect:/cliente/in" + "?id="+ id;
	}
	
	@PostMapping("/historico")
	public String historico(Model model, @Param(value = "id") Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		Optional<Cliente> cliente = clienteRepository.findById(id);
		model.addAttribute("conta", conta.get());
		model.addAttribute("cliente", cliente.get());
		System.out.println(conta.get().getHistorico());
		return "/historico";
	}

}
