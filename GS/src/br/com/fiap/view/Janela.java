package br.com.fiap.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.ScrollPane;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import javax.sound.sampled.LineEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
//import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.components.BotaoListener;
import br.com.fiap.components.InputText;
import br.com.fiap.components.MeuLabel;
import br.com.fiap.components.MeuRadioGroup;
import br.com.fiap.components.MeuTextArea;
import br.com.fiap.components.StarRater;
import br.com.fiap.dao.PostosDao;
import br.com.fiap.model.ListaPostos;
//import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;

public class Janela extends JFrame{
	

	
	private static final long serialVersionUID = 1L;
	MeuLabel nome = new MeuLabel("Nome do Posto");
	InputText inputText = new InputText();
	MeuLabel rua = new MeuLabel("Rua");
	MeuTextArea meuTextAreaRua = new MeuTextArea();
	MeuLabel bairro = new MeuLabel("Bairro");
	MeuTextArea meuTextAreaBairro = new MeuTextArea();
	MeuLabel cidade = new MeuLabel("Cidade");
	MeuTextArea meuTextAreaCidade = new MeuTextArea();
	MeuLabel preco = new MeuLabel("Preço do kWh");
	MeuTextArea meuTextAreaPreco = new MeuTextArea();
	
	JButton salvar = new JButton("Salvar");
	JButton cancelar = new JButton("Cancelar");
	
	MeuLabel estadosLabel = new MeuLabel("Estado");
	String[] estados = {"", "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF"};
	
	JComboBox<String> estado = new JComboBox<>(estados);
	JCheckBox favorito = new JCheckBox("Favorito");
	
	MeuLabel avalicao = new MeuLabel("Avaliação");
	StarRater starRater = new StarRater();
	
	
	
	MeuRadioGroup radio = new MeuRadioGroup(Arrays.asList("Tipo 1", "Tipo 2", "CSS2", "CHAdeMO"));
	String[] colunas =  {"Id", "Nome", "Rua", "Bairro", "Cidade", "Preço", "Estado", "Conector", "Favorito", "Avaliação"};
	DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
	JTable tabela = new JTable(tableModel);
	
	BotaoListener listenerSalvar = new BotaoListener(this); 
	
	
	
	JPanel centro = new JPanel(new GridLayout(0,1,0,0));
	
	JPanel lineEnd = new JPanel();

	JPanel lineStart = new JPanel();

	JPanel pageEnd = new JPanel(new FlowLayout());

	JTabbedPane abas = new JTabbedPane();
	
	public Janela() {
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init() {
	
	JPanel cadastro = new JPanel();
	cadastro.setLayout(new BorderLayout(20,30));
	
	
	
	//Painel do centro
	
	centro.add(nome);
	centro.add(inputText);
	centro.add(rua);
	centro.add(meuTextAreaRua);
	centro.add(bairro);
	centro.add(meuTextAreaBairro);
	centro.add(cidade);
	centro.add(meuTextAreaCidade);
	centro.add(preco);
	centro.add(meuTextAreaPreco);
	
	centro.add(estadosLabel);
	centro.add(estado);
    
    //Painel da Direita
    
    
	lineEnd.setLayout(new BoxLayout(lineEnd, BoxLayout.PAGE_AXIS));
	MeuLabel tipoConector = new MeuLabel("Tipo de Conector");
	lineEnd.add(Box.createVerticalStrut(10));
	lineEnd.add(tipoConector);
 	
    lineEnd.add(radio.getComponent(0));
    lineEnd.add(radio.getComponent(0));
    lineEnd.add(radio.getComponent(0));
    lineEnd.add(radio.getComponent(0));
  
   

    lineEnd.add(favorito);
    lineEnd.add(avalicao);

    lineEnd.add(starRater);
    
	//Painel da Esquerda
   
    JLabel img = new JLabel(new ImageIcon("src/Imagem/Capa.jpg"));
    lineStart.add(Box.createHorizontalStrut(10));
    lineStart.add(img);
    
    
    // Painel  de baixo 

	pageEnd.add(Box.createHorizontalStrut(15));
	pageEnd.add(salvar);
	pageEnd.add(cancelar);
	
	
	//Adicionando tudo a view cadastro
	
	cadastro.add(lineStart,BorderLayout.LINE_START);
	cadastro.add(centro, BorderLayout.CENTER);
	cadastro.add(lineEnd, BorderLayout.LINE_END);
	cadastro.add(pageEnd, BorderLayout.PAGE_END);
	

	//Adicionando abas
	abas.add("Cadastro", cadastro);
	
	
	
	
	abas.add("Lista", new JScrollPane(tabela));
	
	
	add(abas);
	setTitle("SE LIGA");
	
	CarregarDados();
	
	tabela.addMouseListener(listenerSalvar);
	tabela.setDefaultEditor(Object.class, null);
	
	
	setVisible(true);
	
	
	salvar.addActionListener(listenerSalvar);
	
	}
	public void CarregarDados() {
		
		tableModel.setRowCount(0);
		List<ListaPostos> lista = new PostosDao().listarTodos();
		lista.forEach(postos -> tableModel.addRow(postos.getData()));
		
	}

	public InputText getInputText() { //nome do posto
		return inputText;
	}

	public MeuTextArea getMeuTextAreaRua() {
		return meuTextAreaRua;
	}
	
	public MeuTextArea getMeuTextAreaBairro() {
		return meuTextAreaBairro;
	}
	
	public MeuTextArea getMeuTextAreaCidade() {
		return meuTextAreaCidade;
	}
	
	public MeuTextArea getMeuTextAreaPreco() {
		return meuTextAreaPreco;
	}

	public JComboBox<String> getEstado() { //VERIFICAR
		return estado;
	}

	public JCheckBox getFavorito() {
		return favorito;
	}

	public MeuRadioGroup getRadios() {
		return radio;
	}

	public StarRater getStarRater() {
		return starRater;
	}
}
	


