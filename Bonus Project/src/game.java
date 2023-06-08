import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class game extends JPanel {
    int count = 0;
    WordGame word = new WordGame();
    
    JLabel label,complete, errCount,q, success, time, categor, help, scoreBoard, hp;
    
   // JFrame frame;
    JPanel panel,p,picture,centerPanel,healthBarPanel;
    JTextField text;
    
    JProgressBar healthBar;
    
    JButton fruit, animal, flower, quit,b, again;
    
    categoryHandler ch = new categoryHandler();
    timeTracer tt = new timeTracer();
    keyPress kp = new keyPress();
    gameOver gg = new gameOver();
    
    String question = "", answer = "";
    
    Font titleFont = new Font("Times New Roman", Font.BOLD, 22);
    Font cateFont = new Font("Times New Roman", Font.BOLD, 32);
    Font everyFont = new Font("Times New Roman", Font.BOLD, 20);
    
    DecimalFormat df = new DecimalFormat("#.###");
    
	static int counter = 0;
	static double startTime;
	static int score = 0;
	static int health = 50;
	static boolean goneOnce = true;
	static boolean checkTime = false;
	
	static int howManyQuestion = 0;
	static double totalTime = 0.0;
	public game() {
		GUI();
	}
	public void GUI() {
        panel = new JPanel();
        panel.setBackground(Color.cyan);
        panel.setVisible(true);
        
        picture = new JPanel();
        picture.setBackground(Color.cyan);
        picture.setVisible(true);
        picture.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        
        JLabel l3 = new JLabel(new ImageIcon("fruits.jpg"));
        picture.add(l3);
        
        JLabel l2 = new JLabel(new ImageIcon("animals.jpg"));
        picture.add(l2);

        JLabel l1 = new JLabel(new ImageIcon("flower.jpg"));
        picture.add(l1);
        
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.cyan);
        centerPanel.setBounds(350, 300, 100, 100);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.setPreferredSize(new Dimension(200,110));       
        fruit = new JButton("1.Fruits");
        fruit.addActionListener(ch);
        buttonPanel.add(fruit);
        
        animal = new JButton("2.Animals");
        animal.addActionListener(ch);
        buttonPanel.add(animal);
        
        flower = new JButton("3.Flowers");
        flower.addActionListener(ch);
        buttonPanel.add(flower);
        
        centerPanel.add(buttonPanel);
        quit = new JButton("4.Quit");
        quit.addActionListener(gg);
        buttonPanel.add(quit);
        
        JLabel pick = new JLabel("Pick a category(1:fruit,2:animal,3:flower, q: quit):");
        pick.setFont(titleFont);
        panel.add(pick);
        
        if(goneOnce) {
	    	scoreBoard = new JLabel("Score: "+ score);
	    	scoreBoard.setFont(everyFont);
	        scoreBoard.setBounds(750,360,100,25);
	        
	        healthBarPanel = new JPanel();
	        healthBarPanel.setBounds(10,360, 300,30);
	        healthBarPanel.setBackground(Color.cyan);
	        
	        hp = new JLabel("Health Bar:");
	        hp.setBounds(10,335,150,25);
	        hp.setFont(everyFont);
	        
	        healthBar = new JProgressBar(0,50);
	        healthBar.setPreferredSize(new Dimension(300,40));
	        healthBar.setBorder(new LineBorder(Color.black,2));
	        healthBar.setBackground(Color.red);
	        healthBar.setForeground(Color.green);
	        healthBar.setValue(health);
	        healthBarPanel.add(healthBar);
        }
        this.add(hp);
        this.add(healthBarPanel);
        this.add(scoreBoard);
        this.add(picture, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.SOUTH);
	}
    public void runWordGame(HashMap<String, String> hm, String category) {

    	panel.setVisible(false);
    	picture.setVisible(false);
    	centerPanel.setVisible(false);
		for(String q: hm.keySet()) {
			question = q;
			answer = hm.get(q);
		}
    	p = new JPanel();
    	p.setBackground(Color.cyan);
		p.setVisible(true);
    	p.setLayout(null);
    	
    	q = new JLabel("What words do these make?: " + question);
    	q.setFont(everyFont);
    	q.setBounds(10,20, 500,50);
    	
    	label = new JLabel("Answer: ");
    	label.setFont(everyFont);
    	label.setBounds(10,60,80,25);
    	
    	errCount = new JLabel("");
    	errCount.setFont(everyFont);
    	errCount.setBounds(10,210,165,25);
    	
    	complete = new JLabel("");
    	complete.setFont(everyFont);
    	complete.setBounds(10,190,300,25);
    	
    	time = new JLabel("");
    	time.setFont(everyFont);
    	time.setBounds(10, 230, 500, 25);
    	
    	categor = new JLabel("Category: " + category);
    	categor.setBounds(10,-10, 350, 50);
    	categor.setFont(cateFont);
    	
    	help = new JLabel("Press this Button down below or 'enter' on key pad.");
    	help.setFont(everyFont);
    	help.setBounds(10,90, 800, 25);
    	
    	b = new JButton("Enter");
    	b.setBounds(10,120,80,25);
    	b.addActionListener(tt);
    	
    	text = new JTextField();
    	text.setBounds(100,60,165,25);
    	text.addKeyListener(kp);
    	

    	p.add(b);
    	p.add(errCount);
    	p.add(complete);
    	p.add(label);
    	p.add(text);
    	p.add(q);
    	p.add(time);
    	p.add(categor);
    	p.add(help);
    	
    	this.add(p);

    }
	public void gameOverScreen() {
		p.setVisible(false);
		scoreBoard.setVisible(false);
		healthBarPanel.setVisible(false);
		hp.setVisible(false);
		
		JPanel jp = new JPanel();
		jp.setVisible(true);
		jp.setLayout(new GridLayout(3,1));
		
		JLabel la = new JLabel("GAME OVER.");
		la.setHorizontalAlignment(JLabel.CENTER);
		la.setVerticalAlignment(JLabel.TOP);
		la.setFont(titleFont);
		
		
		JPanel format = new JPanel();
		format.setLayout(new GridLayout(2,1,50,30));

		
		double math = totalTime/howManyQuestion;
		JLabel resultT = new JLabel("Avg time per question: " + df.format(math));	
		resultT.setHorizontalAlignment(JLabel.CENTER);
		resultT.setVerticalAlignment(JLabel.TOP);
		resultT.setFont(everyFont);
		
		JLabel resultS = new JLabel("End Score: " + score);
		resultS.setHorizontalAlignment(JLabel.CENTER);
		resultS.setVerticalAlignment(JLabel.CENTER);
		resultS.setFont(everyFont);
		
		format.add(resultS);
		format.add(resultT);
		
		JPanel flat = new JPanel();
		flat.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel i = new JPanel();
		i.setLayout(new GridLayout(1,2,20,10));
		again = new JButton("Play Again");
		again.addActionListener(gg);
		quit = new JButton("Quit");
		quit.addActionListener(gg);
		
		i.add(again);
		i.add(quit);
		
		flat.add(i);
		
		jp.add(la);
		jp.add(format);
		jp.add(flat);
		
		this.add(jp);
		
	}
	public void damageReceived() {
		if(health>0) {
			health = health-10;
		}
	}
	public void healthReceived() {
		if(health<50) {
			health = health + 10;
		}
	}
	public void checkText() {
		String user = text.getText();
		if(user.equalsIgnoreCase(answer)) {
			goneOnce = false;
			checkTime = true;
			score+=10;
			healthReceived();
			scoreBoard.setText("Score: " + score);
			complete.setText("Good Job!");
			count = 0;
			errCount.setText("");
			
		}else {
			checkTime = false;
			damageReceived();
			scoreBoard.setText("Score: " + score);
			complete.setText("Try again!");
			count++;
			errCount.setText("Error count: " + count);
		}
        healthBar.setValue(health);
        if(health == 0) {
        	gameOverScreen();
        }
		if(checkTime) {
			double endTime = System.currentTimeMillis();
			double response = (endTime -startTime)/1000.0;
			totalTime += response;
			time.setText("Response Time: " + response + " sec");
			javax.swing.Timer timer = new javax.swing.Timer(1500, event ->{
    			p.setVisible(false);
    			GUI();
			});
			timer.setRepeats(false);
			timer.start();
		}
	}
	public class keyPress implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				checkText();
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {

		}
		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
	public class timeTracer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			checkText();
		}
	}
	
	public class categoryHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			howManyQuestion++;
			if(event.getSource() == fruit) {
				try {
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(1),"Fruits");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == animal) {
				try {
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(2),"Animals");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}else if(event.getSource() == flower) {
				try {
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(3),"Flowers");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
	}
	public class gameOver implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == again) {
				
				
			}else if(event.getSource() == quit) {
				System.exit(0);
			}
		}
	}
}
