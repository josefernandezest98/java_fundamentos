	import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

	public class CalculadoraGrafica implements ActionListener, MouseListener {
		
		private Calculadora c; //Objeto de la clase Calculadora
		private JTextField tfexp,tfx,tfy,tfz,tfres;  //Campos de texto para las expresiones y
		                                     //los valores de las variables
			
		public CalculadoraGrafica(){
			//En el constructor se crean las componentes gráficas
			JFrame f=new JFrame(); //Objeto JFrame que contendrá todas las componentes gráficas
			f.setTitle("Calculadora");
			f.setMinimumSize(new Dimension(300, 620)); 
			f.setLocation(50,50);
			f.getContentPane().setLayout(new GridLayout(3,1));
			
			JPanel panel1=new JPanel(); panel1.setLayout(null); //El JFrame estará dividido en tres paneles
			JPanel panel2=new JPanel(); panel2.setLayout(null);
			JPanel panel3=new JPanel(); panel3.setLayout(null);
				
			JLabel l1=new JLabel("---Introducir expresión----------"); //Etiqueta
			l1.setBounds(5, 0, 290, 30);
			l1.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
			l1.setForeground(Color.blue);
			panel1.add(l1);
			
			tfexp=new JTextField("Introduzca una expresion");//Campo de texto para introducir la expresión
			tfexp.addMouseListener(this);
			tfexp.setBounds(5, 30, 270, 30);
			panel1.add(tfexp);
			JPanel panel11=new JPanel();  panel11.setLayout(new GridLayout(3,6));
			JButton b;
			String[] buttons={"x","y","z","1","2","3","+","*","-","4","5","6","(",")","0","7","8","9"};
			for (int i=0; i<buttons.length; i++){
				b=new JButton(buttons[i]); 
				b.addActionListener(this);  
				panel11.add(b); //Botones para introducir la expresión
			}
					panel11.setBounds(15,70,250,120);
			panel1.add(panel11);
			f.getContentPane().add(panel1);
			
			JLabel l2=new JLabel("---Dar valores a las variables---"); //Etiqueta
			l2.setBounds(5, 51, 290, 30);
			l2.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
			l2.setForeground(Color.blue);
			panel2.add(l2);
			
			JPanel panel21=new JPanel(); panel21.setLayout(new GridLayout(3,2));
			panel21.setBounds(40,90,130,90);
			panel21.add(new JLabel("x  ",JLabel.CENTER));
			tfx=new JTextField("Valor");//Hacemos que comience en Valor
			tfx.addMouseListener(this);
			panel21.add(tfx);
			panel21.add(new JLabel("y  ",JLabel.CENTER));
			tfy=new JTextField("Valor");//Hacemos que comience en Valor
			tfy.addMouseListener(this);
			panel21.add(tfy);
			panel21.add(new JLabel("z  ",JLabel.CENTER));
			tfz=new JTextField("Valor");//Hacemos que comience en Valor
			tfz.addMouseListener(this);
			panel21.add(tfz);
			panel2.add(panel21);
			f.getContentPane().add(panel2);
			
			JLabel l3=new JLabel("---Resultados---"); //Etiqueta
			l3.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
			l3.setBounds(5, 51, 290, 30);
			l3.setForeground(Color.blue);
			panel3.add(l3);
			
			//Botones para realizar las operaciones sobre la expresión
			b=new JButton("Sustituir"); b.addActionListener(this);  b.setBounds(5,90,87,30); panel3.add(b);
			b=new JButton("Calcular"); b.addActionListener(this);  b.setBounds(97,90,87,30); panel3.add(b);
			b=new JButton("Borrar"); b.addActionListener(this);  b.setBounds(189,90,87,30); panel3.add(b);
			tfres=new JTextField(); tfres.setBounds(5, 130, 270, 30);
			panel3.add(tfres);
			f.getContentPane().add(panel3);
			
			f.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent ev) {
			String aux= ((JButton)ev.getSource()).getText();
				
			if (aux.equals("Sustituir")) {
				this.sustituir();
			} else { 
				if (aux.equals("Calcular")) {
					this.calcular();
				} else {
					if (aux.equals("Borrar")){
						this.reset();
					} else {
						String aux2=tfexp.getText();
						tfexp.setText(aux2+aux);
					}
				}
			}
		}
			
		private void calcular(){
			int valorX,valorY,valorZ;
			String s=tfexp.getText();
			Expresion exp=parser(s);
			Valoracion v=new Valoracion();
			try{
				if(exp.estaVariable("x")){ //En este if comprobamos que vamos a necesitar dicha variable
					if(tfx.getText().equals("")){
						valorX=0;
					}
					else{
						valorX = new Integer(tfx.getText());
					}
					
					v.anadirVariable("x",valorX); //En este, la añadimos porque sabemos que está, si no, nó tendría sentido añadirla
				}
				if(exp.estaVariable("y")){
					if(tfy.getText().equals("")){
						valorY=0;
					}
					else{
						valorY = new Integer(tfy.getText());
					}
					
					v.anadirVariable("y",valorY);
				}
				if(exp.estaVariable("z")){
					if(tfz.getText().equals("")){
						valorZ=0;
					}
					else{
						valorZ = new Integer(tfz.getText());
					}
					
					v.anadirVariable("z",valorZ);
				}
				
				
				//Estos if´s lo que hacen es que si esta vacio el cuadro, entonces le asignamos el valor 0
				//si no creamos un entero con el valor que tenga, si da una excepión no calcula la expresión
				//Tambien se repite lo mismo que en caso X para lo de añadir la variable a la valoracion
				
				
				
				
				c=new Calculadora(exp,v);
				int res=c.calcular();
				tfres.setText(Integer.toString(res));
				
			}
			catch(NumberFormatException e){
				tfres.setText("Introduzca valores enteros en los campos x,y,z");
				
			}
	
		}
				
		private void sustituir(){
			int valorX,valorY,valorZ;
			String s=tfexp.getText();
			Expresion exp=parser(s);
			Valoracion v=new Valoracion();
			try{
				
				if(exp.estaVariable("x")){ //En este if comprobamos que vamos a necesitar dicha variable
					if(tfx.getText().equals("")){
						valorX=0;
					}
					else{
						valorX = new Integer(tfx.getText());
					}
					
					v.anadirVariable("x",valorX); //En este, la añadimos porque sabemos que está, si no, nó tendría sentido añadirla
				}
				if(exp.estaVariable("y")){
					if(tfy.getText().equals("")){
						valorY=0;
					}
					else{
						valorY = new Integer(tfy.getText());
					}
					
					v.anadirVariable("y",valorY);
				}
				if(exp.estaVariable("z")){
					if(tfz.getText().equals("")){
						valorZ=0;
					}
					else{
						valorZ = new Integer(tfz.getText());
					}
					
					v.anadirVariable("z",valorZ);
				}
				
				
				//Estos if´s lo que hacen es que si esta vacio el cuadro, entonces le asignamos el valor 0
				//si no creamos un entero con el valor que tenga, si da una excepión no calcula la expresión
				//Tambien se repite lo mismo que en caso X para lo de añadir la variable a la valoracion
				
				c=new Calculadora(exp,v);
				c.sustituirValores();
				tfres.setText(c.toString());
				
			}
			catch(NumberFormatException e){
				tfres.setText("Introduzca valores enteros en los campos x,y,z");
				
			}
			
		}
			  
		private void reset(){
			tfx.setText("");
			tfy.setText("");
			tfz.setText("");
			tfexp.setText("");
			tfres.setText("");
		}

		private static String getExp1(String s){
			int i=1,na=1,nc=0;
			
			while (na!=nc){
				if (s.charAt(i)=='(') {
					na++;
				} else {
					if (s.charAt(i)==')') {
						nc++;
					}
				}
				i++;
			}
			return s.substring(0,i);
		}
		
		private static Expresion parser(String s){
			if (s.charAt(0) =='(') {
				String s1=getExp1(s);
				if (s1.length()==s.length()){
					return parser(s.substring(1,s.length()-1));
				}
				char op=s.charAt(s1.length());
				String s2=s.substring(s1.length()+1, s.length());
				if (op=='+') return new Suma (parser(s1),parser(s2));
					else return new Producto (parser(s1),parser(s2));
			} else {
				if (s.charAt(0) =='-') {
					if (s.charAt(1)=='('){
						s=s.substring(2, s.length()-1);
						return new ExpresionUnaria(parser(s));
					} else {
						s=s.substring(1, s.length());
						return new ExpresionUnaria(parser(s));
					}
				} else {
					int i1=s.indexOf("+");
					int i2=s.indexOf("*");
					if ((i1==-1) && (i2==-1)){
						if (s.equals("x")||s.equals("y")||s.equals("z")){
							return new ExpresionAtomica(s);
						} else {
							return new ExpresionAtomica(Integer.parseInt(s));
						}
					} else if ((i1!=-1) && ((i2==-1) ||(i1<i2))) {
						String s1=s.substring(0,i1);
						String s2=s.substring(i1+1, s.length());
						return new Suma(parser(s1),parser(s2));
					} else {
						String s1=s.substring(0,i2);
						String s2=s.substring(i2+1, s.length());
						return new Producto(parser(s1),parser(s2));
						
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(tfx.getText().equals("Valor")&&(e.getSource()==tfx)){
				tfx.setText("");
			}
			if(tfy.getText().equals("Valor")&&(e.getSource()==tfy)){
				tfy.setText("");
			}
			if(tfz.getText().equals("Valor")&&(e.getSource()==tfz)){
				tfz.setText("");
			}
			if(tfexp.getText().equals("Introduzca una expresion")&&(e.getSource()==tfexp)){
				tfexp.setText("");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
