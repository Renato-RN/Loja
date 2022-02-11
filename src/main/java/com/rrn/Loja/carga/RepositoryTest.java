package com.rrn.Loja.carga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rrn.Loja.domain.Cliente;
import com.rrn.Loja.domain.Item;
import com.rrn.Loja.domain.Pedido;
import com.rrn.Loja.repository.ClienteRepository;

@Component
public class RepositoryTest 
implements ApplicationRunner 
{

	private static final long ID_CLIENTE_A = 11l;
	private static final long ID_CLIENTE_B = 22l;
	
	private static final long ID_ITEM1 = 100l;
	private static final long ID_ITEM2 = 101l;
	private static final long ID_ITEM3 = 102l;
	
	private static final long ID_PEDIDO1 = 1000l;
	private static final long ID_PEDIDO2 = 1001l;
	private static final long ID_PEDIDO3 = 1002l;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
    public void run(ApplicationArguments applicationArguments) throws Exception {

    	System.out.println(">>> Iniciando carga de dados...");
    	Cliente ClienteA = new Cliente(ID_CLIENTE_A,"Cliente A","SP");
    	Cliente ClienteB = new Cliente(ID_CLIENTE_B,"Cliente B","RJ");    	
    	
    	Item lanche1 = new Item(ID_ITEM1,"Lanche tradicional",25d);
    	Item lanche2 = new Item(ID_ITEM2,"Lanche tradicional picante",27d);
		Item lanche3 = new Item(ID_ITEM3,"Lanche max salada",30d);
    	
    	List<Item> listaPedidoClienteA1 = new ArrayList<Item>();
    	listaPedidoClienteA1.add(lanche1);

    	List<Item> listaPedidoClienteB1 = new ArrayList<Item>();
    	listaPedidoClienteB1.add(lanche2);
    	listaPedidoClienteB1.add(lanche3);
    	
    	Pedido pedidoDoClienteA = new Pedido(ID_PEDIDO1,ClienteA,listaPedidoClienteA1,lanche1.getPreco());
    	ClienteA.novoPedido(pedidoDoClienteA);
    	
    	Pedido pedidoDoClienteB = new Pedido(ID_PEDIDO2,ClienteB,listaPedidoClienteB1, lanche2.getPreco()+lanche3.getPreco());
    	ClienteB.novoPedido(pedidoDoClienteB);
    	
    	System.out.println(">>> Pedido 1 - ClienteA : "+ pedidoDoClienteA);
    	System.out.println(">>> Pedido 2 - Ze Pequeno: "+ pedidoDoClienteB);
    	
       
		clienteRepository.saveAndFlush(ClienteB);
		System.out.println(">>> Gravado cliente 2: "+ClienteB);

		List<Item> listaPedidoClienteA2 = new ArrayList<Item>();
		listaPedidoClienteA2.add(lanche2);
    	Pedido pedido2DoClienteA  = new Pedido(ID_PEDIDO3,ClienteA,listaPedidoClienteA2,lanche2.getPreco());
    	ClienteA.novoPedido(pedido2DoClienteA);
    	clienteRepository.saveAndFlush(ClienteA);
    	System.out.println(">>> Pedido 2 - ClienteA : "+ pedido2DoClienteA);
    	System.out.println(">>> Gravado cliente 1: "+ClienteA);
		
    }
 
}