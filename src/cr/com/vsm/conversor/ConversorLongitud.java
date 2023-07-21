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

public class ConversorLongitud extends JFrame implements ItemListener {

	private JComboBox comboOriginal;
	private JComboBox comboResultado;
	private JLabel labelTitulo, labelOrigen, origenSel, labelMedidaOrg, labelMedidaRes;
	private JLabel labelResultado, resultadoSel;
	private JLabel lbimg;
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

	public ConversorLongitud() {
		String medidas[] = { "Kilómetros", "Hectómetros", "Decámetros", "Metros", "Decímetros", "Centímetros",
				"Milímetros" };

		comboOriginal = new JComboBox(medidas);
		comboOriginal.setBounds(100, 70, 150, 20);
		add(comboOriginal);
		comboOriginal.setSelectedIndex(0);
		comboOriginal.addItemListener(this);

		labelTitulo = new JLabel("Conversor de Medidas de Longitud");
		labelTitulo.setBounds(170, 10, 400, 30);
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
		
		medidaOrg.setBounds(210, 115, 50, 20);
		add(medidaOrg);

		btncalcular = new JButton("Calcular");
		btncalcular.setBounds(400, 110, 100, 25);
		add(btncalcular);

		origenSel = new JLabel((String) comboOriginal.getItemAt(0));
		origenSel.setBounds(265, 110, 300, 30);
		add(origenSel);
		//origenSel.setForeground(Color.blue);

		comboResultado = new JComboBox(medidas);
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
				int org, res;
				double resultado;

				if (e.getSource() == btnsalir) {
					// Cierro la pantalla
					setVisible(false);
					//System.exit(0);
				} else if (e.getSource() == btncalcular) {
					org = comboOriginal.getSelectedIndex();
					res = comboResultado.getSelectedIndex();
					if (!(medidaOrg.getText().isEmpty())) {
						if (org > res) {
							res = org - res;
							resultado = Double.parseDouble(medidaOrg.getText()) / Math.pow(10, res);

						} else {
							res = res - org;
							resultado = Double.parseDouble(medidaOrg.getText()) * Math.pow(10, res);
						}
					} else
						resultado = 0;
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
		setTitle("Conversor de Medidas de Longitud"); // Título del JFrame
		ImageIcon img = new ImageIcon("./imagenes/metro.jpeg");
		setIconImage(img.getImage());

		
		setBounds(350, 150, 600, 400);
		setResizable(false); // No redimensionable
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		lbimg = new JLabel("");
		lbimg.setIcon(new ImageIcon("./imagenes/metros2.jpg")); //medidas.png"));
		lbimg.setBounds(400,100, 300, 200); 
		add(lbimg);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar proceso al cerrar ventana
		setVisible(true); // Mostrar JFrame
	}

	public static void main(String[] args) {
		new ConversorLongitud();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int org, res;
		double resultado;

		if (e.getSource() == comboOriginal) {
			medidaOrg.setText(null);
			String seleccionado = (String) comboOriginal.getSelectedItem();
			origenSel.setText(seleccionado);
		} else if (e.getSource() == comboResultado) {
			String seleccionado = (String) comboResultado.getSelectedItem();
			resultadoSel.setText(seleccionado);
		}
	}
}