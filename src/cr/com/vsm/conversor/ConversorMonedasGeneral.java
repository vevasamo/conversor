package cr.com.vsm.conversor;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConversorMonedasGeneral extends JFrame implements ItemListener {

	private JComboBox comboOriginal;
	private JComboBox comboResultado;
	private JLabel labelTitulo, labelOrigen, origenSel, labelMedidaOrg, labelMedidaRes;
	private JLabel labelResultado, resultadoSel;
	private JTextField medidaOrg;
	private JButton btnsalir, btncalcular;
	
	private void numerico(JTextField m) {
		m.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)&& c!='.') {
					e.consume();					
				}
				if (c == '.' && medidaOrg.getText().contains(".")) {
					e.consume();
				}
			}
		});
	}

	public ConversorMonedasGeneral() {
		/*
		 * - Convertir de la moneda de tu país a Dólar
			- Convertir de la moneda de tu país  a Euros
			- Convertir de la moneda de tu país  a Libras Esterlinas
			- Convertir de la moneda de tu país  a Yen Japonés
			- Convertir de la moneda de tu país  a Won sul-coreano
		 */
		String monedas[] = { "Dólares", "Euros", "Libras Esterlinas", "Yenes", "Wones", "Colones" };
		double tcambio$x1[] = {1,1.12,1.31,0.0072,0.00079,0.0018};
		double tcambioMx1$[] = {1,0.89,0.76,138.64,1267.13,543.78};

		comboOriginal = new JComboBox(monedas);
		comboOriginal.setBounds(100, 70, 150, 20);
		add(comboOriginal);
		comboOriginal.setSelectedIndex(5);
		comboOriginal.addItemListener(this);


		labelTitulo = new JLabel("Conversor de Monedas");
		labelTitulo.setBounds(200, 10, 400, 30);
		add(labelTitulo);

		labelOrigen = new JLabel("Medida Original");
		labelOrigen.setBounds(100, 40, 300, 30);
		add(labelOrigen);

		labelResultado = new JLabel("Medida Final");
		labelResultado.setBounds(310, 40, 300, 30);
		add(labelResultado);

		labelMedidaOrg = new JLabel("Valor a Convertir:");
		labelMedidaOrg.setBounds(100, 110, 150, 30);
		add(labelMedidaOrg);
		//labelMedidaOrg.setForeground(Color.blue);

		medidaOrg = new JTextField();
		//medidaOrg.setBackground(Color.LIGHT_GRAY);
		numerico (medidaOrg);
		
		medidaOrg.setBounds(210, 115, 100, 20);
		add(medidaOrg);

		btncalcular = new JButton("Calcular");
		btncalcular.setBounds(430, 110, 100, 25);
		add(btncalcular);

		origenSel = new JLabel((String) comboOriginal.getItemAt(5));
		origenSel.setBounds(320, 110, 100, 30);
		add(origenSel);
		origenSel.setText((String) comboOriginal.getSelectedItem());
		//origenSel.setForeground(Color.blue);

		comboResultado = new JComboBox(monedas);
		comboResultado.setBounds(310, 70, 150, 20);
		add(comboResultado);
		comboResultado.setSelectedIndex(0);
		comboResultado.addItemListener(this);

		labelMedidaRes = new JLabel("Valor convertido:");
		labelMedidaRes.setBounds(100, 160, 150, 30);
		add(labelMedidaRes);
		labelMedidaRes.setForeground(Color.LIGHT_GRAY);
		labelMedidaRes.setFont(new Font("Tahoma", Font.BOLD, 16));

		resultadoSel = new JLabel((String) comboResultado.getItemAt(0));
		resultadoSel.setBounds(240, 160, 300, 30);
		add(resultadoSel);
		resultadoSel.setForeground(Color.LIGHT_GRAY);
		resultadoSel.setFont(new Font("Tahoma", Font.BOLD, 16));

		// Botón
		btnsalir = new JButton("Regresar");
		btnsalir.setBounds(180, 250, 200, 40);
		add(btnsalir);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double org, res;
				double resultado;

				if (e.getSource() == btnsalir) {
					// Cierro la pantalla
					setVisible(false);
					//System.exit(0);
				} else if (e.getSource() == btncalcular) {
					org = tcambio$x1[comboOriginal.getSelectedIndex()];
					res = tcambioMx1$[comboResultado.getSelectedIndex()];
					if (!(medidaOrg.getText().isEmpty())) {
						resultado = Double.parseDouble(medidaOrg.getText()) * org * res;
					}
					else resultado = 0;
					String seleccionado = resultado + " " + (String) comboResultado.getSelectedItem();
					resultadoSel.setText(seleccionado);
				}
			}
		};
		btnsalir.addActionListener(al);
		btncalcular.addActionListener(al);
		initPantalla();
	}

	private void initPantalla() {

		setLayout(null); // Layout absoluto
		setTitle("Conversor de Monedas"); // Título del JFrame
		ImageIcon img = new ImageIcon("./imagenes/intercambio.jpeg");
		setIconImage(img.getImage());
		
		setBounds(350, 150, 600, 400);
		setResizable(false); // No redimensionable
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar proceso al cerrar ventana
		setVisible(true); // Mostrar JFrame
	}

	public static void main(String[] args) {
		new ConversorMonedasGeneral();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int org, res;
		double resultado;

		if (e.getSource() == comboOriginal) {
			medidaOrg.setText(null);
			String seleccionado = (String) comboOriginal.getSelectedItem();
			origenSel.setText(seleccionado);
			/*
			if (seleccionado != "Colones") {
				comboResultado.setSelectedIndex(5);	
				comboResultado.setEnabled(false); //enableInputMethods(false);
			}
			else {
				comboResultado.setEnabled(true);
			}
			*/
		} else if (e.getSource() == comboResultado) {
			String seleccionado = (String) comboResultado.getSelectedItem();
			resultadoSel.setText(seleccionado);
		}
	}
}