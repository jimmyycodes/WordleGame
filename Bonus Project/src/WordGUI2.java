import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class WordGUI2{
    int count = 0;
    WordGame word = new WordGame();
    
    JLabel label,complete, errCount,q, success, time, categor, help, scoreBoard, hp;
    
    JFrame frame;
    JPanel panel,p,picture,centerPanel,healthBarPanel;
    JTextField text;
    
    JProgressBar healthBar;
    
    JButton fruit, animal, flower, quit,b;
    
    categoryHandler ch = new categoryHandler();
    timeTracer tt = new timeTracer();
    keyPress kp = new keyPress();
    
    String question = "", answer = "";
    
    Font titleFont = new Font("Times New Roman", Font.BOLD, 22);
    Font cateFont = new Font("Times New Roman", Font.BOLD, 32);
    Font everyFont = new Font("Times New Roman", Font.BOLD, 20);
    
	static int counter = 0;
	static double startTime;
	static int score;
	static int health = 50;
	static boolean goneOnce = true;
    public static void main(String[] args) {
    	WordGUI2 g = new WordGUI2();
    	g.Screen();
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
    	
    	frame.add(p);

    }
    public void Screen() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
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
        centerPanel.add(buttonPanel);
        
        fruit = new JButton("1.Fruits");
        fruit.addActionListener(ch);
        buttonPanel.add(fruit);
        
        animal = new JButton("2.Animals");
        animal.addActionListener(ch);
        buttonPanel.add(animal);
        
        flower = new JButton("3.Flowers");
        flower.addActionListener(ch);
        buttonPanel.add(flower);
        
        
        quit = new JButton("4.Quit");
        quit.addActionListener(
        	new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			System.exit(0);
        		}
        	}
        );
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
        frame.add(hp);
        frame.add(healthBarPanel);
        frame.add(scoreBoard);
        frame.add(picture, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.SOUTH);
        frame.pack();
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
	public class keyPress implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				checkText();
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}
	public class timeTracer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			checkText();
		}
	}
	public void subtractScore() {
		if(score>0) {
			score-=10;
		}
	}
	public void checkText() {
		String user = text.getText();
		if(user.equalsIgnoreCase(answer)) {
			counter++;
			goneOnce = false;
			score+=10;
			healthReceived();
			scoreBoard.setText("Score: " + score);
			complete.setText("Good Job!");
			count = 0;
			errCount.setText("");
			
		}else {
			damageReceived();
			subtractScore();
			scoreBoard.setText("Score: " + score);
			complete.setText("Try again!");
			count++;
			errCount.setText("Error count: " + count);
		}
        healthBar.setValue(health);
		if(counter%2==0) {
			double endTime = System.currentTimeMillis();
			time.setText("Response Time: " + (endTime -startTime)/1000.0 + " sec");
			javax.swing.Timer timer = new javax.swing.Timer(1500, event ->{
    			p.setVisible(false);
    			GUI();
			});
			timer.setRepeats(false);
			timer.start();
		}
	}
	public class categoryHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == fruit) {
				try {
					counter++;
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(1),"Fruits");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == animal) {
				try {
					counter++;
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(2),"Animals");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}else if(event.getSource() == flower) {
				try {
					counter++;
					startTime = System.currentTimeMillis();
					runWordGame(word.randomizer(3),"Flowers");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
	}
}