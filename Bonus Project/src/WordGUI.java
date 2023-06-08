import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.border.LineBorder;
/*
 * This program reflects the GUI version of the wordGameTest which basically has the same ideas of the console output version
 * but this time it is more user interactive. One thing that is different from the console output version is that it has a 
 * game over screen which includes a end score and average response time added to it as a bonus. 
 * 
 */
public class WordGUI{
    WordGame word = new WordGame();
    
    //JLabels
    private JLabel label,complete, errCount,q, time, categor, help, scoreBoard,title;
    
    //JFrame
    private JFrame frame;
    
    //JText
    private JTextField text;
    
    //JPanels
    private JPanel p,picture,healthBarPanel, jp, allSouth,flow;
    
    //JProgressBar
    private JProgressBar healthBar;
    
    //JButton
    private JButton fruit, animal, flower, quit,enterButton, again;
    
    //ActionListener classes
    categoryHandler ch = new categoryHandler();
    timeTracer tt = new timeTracer();
    keyPress kp = new keyPress();
    gameOver gg = new gameOver();
    
    //fonts and formatters
    Font titleFont = new Font("Times New Roman", Font.BOLD, 22);
    Font cateFont = new Font("Times New Roman", Font.BOLD, 32);
    Font everyFont = new Font("Times New Roman", Font.BOLD, 20);
    DecimalFormat df = new DecimalFormat("#.###");
    
    //all variables
    private String question = "", answer = "";
	private double startTime;
	private int score;
	private int health = 50;
    private boolean goneOnce = true;
    private boolean checkTime = false;
    private int countErr;
    private int howManyQuestion;
    private double totalTime;
	
	public WordGUI() {//default constructor creates frame of the game and calls GUI
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);//sets the background color of the frame 
        frame.setVisible(true);
        categoryScreen();
	}
	public void categoryScreen() {//this is the front page of the game where it ask user to pick a category
		

        title = new JLabel("Pick a category(1:fruit,2:animal,3:flower, q: quit):");//main title of the category page
        title.setVisible(true);
        title.setFont(titleFont);
        title.setHorizontalAlignment(JLabel.CENTER);//Aligns the title on the middle of the frame
        title.setVerticalAlignment(JLabel.CENTER);
        
        picture = new JPanel();//this panel is used for the pictures;
        picture.setOpaque(false);//for each panel we set the opaque to false that way we are able to change the color of the background of the frame
        picture.setVisible(true);
        picture.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));//picture panel uses flow layout which evenly lays out the image on the same row at the center with x & y difference of 30.
        
        JLabel l3 = new JLabel(new ImageIcon("fruits.jpg"));
        picture.add(l3);
        
        JLabel l2 = new JLabel(new ImageIcon("animals.jpg"));
        picture.add(l2);

        JLabel l1 = new JLabel(new ImageIcon("flower.jpg"));
        picture.add(l1);
        
        
        flow = new JPanel();
        flow.setVisible(true);
        flow.setOpaque(false);
        flow.setLayout(new GridBagLayout());//introduces gridbaglayout which is another type of layout manager that allows for more free range on the position of the stuff within the panel.
        GridBagConstraints gbc = new GridBagConstraints();//required constraints when creating gridbag
        
        JPanel buttonPanel = new JPanel();//button panel uses grid layout which creates a grid of row:4 col:1 and adds the buttons in this order.
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.setPreferredSize(new Dimension(120,100));//creates desired size of the button
        
        //adds fruit, animal, flower, and quit to buttonPanel which will be added to center panel and creates a action listener for them
        fruit = new JButton("1.Fruits");
        fruit.addActionListener(ch);//action listener will listen to when the user presses the button. It calls the object ch of categoryHandler which will do specific things depending on the button pressed.
        buttonPanel.add(fruit);
        
        animal = new JButton("2.Animals");
        animal.addActionListener(ch);
        buttonPanel.add(animal);
        
        flower = new JButton("3.Flowers");
        flower.addActionListener(ch);
        buttonPanel.add(flower);
        
        quit = new JButton("4.Quit");
        quit.addActionListener(gg);
        buttonPanel.add(quit);
        
        gbc.gridx = 0;//sets the position of the grid starting at x:0 y:0
        gbc.gridy= 0;
        gbc.anchor = GridBagConstraints.CENTER;//anchor represents the position in which it should display the panel which in this case is in the center.
        flow.add(buttonPanel, gbc);
        
        //if statement is used so that it only creates a score board and health bar once.
        if(goneOnce) {
        	allSouth = new JPanel();//main jpanel for bottom to add to frame
        	allSouth.setOpaque(false);
        	allSouth.setVisible(true);
        	allSouth.setLayout(new GridBagLayout());//creates another gridbaglayout 
        	GridBagConstraints c = new GridBagConstraints();
        	
        	c.gridx = 0;//sets posoition at 0,0
        	c.gridy = 0;
        	c.anchor = GridBagConstraints.CENTER;//anchors towards the center
        	c.weightx = 1;
        	c.weighty = 1;
        	
        	JPanel HealthandScore = new JPanel();//creates a panel for health and score
        	HealthandScore.setOpaque(false);
        	HealthandScore.setLayout(new GridLayout(1,2,80,0));//sets a layout of type grid with row: 1 and col:2 with a horizontal spacing of 80
            
	        healthBarPanel = new JPanel();
	        healthBarPanel.setOpaque(false);
	        
	        healthBar = new JProgressBar(0,50);//Creates a jprogressbar at specified min and max
	        healthBar.setStringPainted(true);
	        healthBar.setFont(new Font("MV Boli", Font.BOLD, 25));//creates font for the string within the healthbar
	        healthBar.setString("Health");
	        healthBar.setPreferredSize(new Dimension(300,40));
	        healthBar.setBorder(new LineBorder(Color.black,2));//creates a border of the healthBar
	        healthBar.setBackground(Color.red);
	        healthBar.setForeground(Color.green);
	        healthBar.setValue(health);//sets the value of the health bar and adds to healthbarpanel
	        
	        healthBarPanel.add(healthBar);
	        HealthandScore.add(healthBarPanel);
	        
	    	scoreBoard = new JLabel("Score: "+ score);
	    	scoreBoard.setFont(everyFont);
	    	
	    	HealthandScore.add(scoreBoard);
        		        
	        allSouth.add(HealthandScore, c);
        }
        //everything is added to the frame and packed
        frame.add(title, BorderLayout.NORTH);
        frame.add(allSouth, BorderLayout.SOUTH);
        frame.add(flow, BorderLayout.WEST);
        frame.add(picture, BorderLayout.CENTER);
        frame.pack();//pack will get the desired size of the frame without having to create a fixed value
	}
    public void runWordGame(HashMap<String, String> hm, String category) {//runWordGame is the main game of the wordgame after user have choosen a category
    	//sets these panel visibility to false which is the GUI page so that we can create a new 'screen' that will
    	//represent the wordgame scrambler
    	title.setVisible(false);
    	flow.setVisible(false);
    	picture.setVisible(false);
		for(String q: hm.keySet()) {//grabs the passed hashmap in the parameter and finds its key(question) and value(answer)
			question = q;
			answer = hm.get(q);
		}
    	p = new JPanel();//main panel for everything
    	p.setOpaque(false);
		p.setVisible(true);
    	p.setLayout(null);
    	
    	//creates 7 JLabels which will create the desired format of the game
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
    	
    	//creates a JButton for the user to click enter when finished typing their guess
    	enterButton = new JButton("Enter");
    	enterButton.setBounds(10,120,80,25);
    	enterButton.addActionListener(tt);//adds action listener of object tt
    	
    	//jtextField is used so that the user can text in box representing their answer
    	text = new JTextField();
    	text.setBounds(100,60,165,25);
    	text.addKeyListener(kp);//key listener is used so the user can decide to press enter on key pad 
    	
    	//adds everything to the panel
    	p.add(enterButton);
    	p.add(errCount);
    	p.add(complete);
    	p.add(label);
    	p.add(text);
    	p.add(q);
    	p.add(time);
    	p.add(categor);
    	p.add(help);
    	
    	//adds to frame
    	frame.add(p);

    }
	public void gameOverScreen() {//game over screen is used to represent when user health bar is at 0
		//set these panels to false which represents the score, health bar, and run game panels.
		p.setVisible(false);
		allSouth.setVisible(false);
		
		jp = new JPanel();//creates the main Jpanel which everything will be added in
		jp.setOpaque(false);
		jp.setVisible(true);
		jp.setLayout(new GridLayout(3,1));//creates a grid layout for this panel which has a row: 3 and col:1

		JLabel la = new JLabel("GAME OVER.");//creates the title and centers at the top of the panel
		la.setHorizontalAlignment(JLabel.CENTER);
		la.setVerticalAlignment(JLabel.TOP);
		la.setFont(titleFont);
		
		
		JPanel format = new JPanel();//creates another panel for two labels: avg time and end score
		format.setOpaque(false);
		format.setLayout(new GridLayout(2,1,50,30));//creates a layout of type grid which has row:2, col:1 and spacing of x:50, y:30 between one another.

		//calculates avg time and adds to jLabel
		double math = totalTime/howManyQuestion;
		JLabel resultT = new JLabel("Avg time per question: " + df.format(math));	
		resultT.setHorizontalAlignment(JLabel.CENTER);
		resultT.setVerticalAlignment(JLabel.TOP);
		resultT.setFont(everyFont);
		
		//calculates end score and adds to jLabel
		JLabel resultS = new JLabel("End Score: " + score);
		resultS.setHorizontalAlignment(JLabel.CENTER);
		resultS.setVerticalAlignment(JLabel.CENTER);
		resultS.setFont(everyFont);
		
		//adding the label to the panel
		format.add(resultS);
		format.add(resultT);
		
		JPanel flat = new JPanel();//creates another panel for the buttons: play again or quit
		flat.setOpaque(false);
		flat.setLayout(new FlowLayout(FlowLayout.CENTER));//has a layout of type flow so everything is on the same line
		
		JPanel i = new JPanel();//uses another panel of layout grid which is used for the buttons so we can get an even jbutton box for both.
		i.setOpaque(false);
		i.setLayout(new GridLayout(1,2,20,10));
		
		//creates the two jbutton and their respective actionlistener
		again = new JButton("Play Again");
		again.addActionListener(gg);
		
		quit = new JButton("Quit");
		quit.addActionListener(gg);
		
		//adds button to the grid panel
		i.add(again);
		i.add(quit);
		
		//adds grid panel to flow panel
		flat.add(i);
		
		//adds everything to main panel
		jp.add(la);
		jp.add(format);
		jp.add(flat);
		
		//add to frame
		frame.add(jp);
		
	}
	//methods used to subtract health or add
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
	//checkText is used to check whether users answer in the text box was valid.
	public void checkText() {
		String user = text.getText();//gets the text from text box and checks with answer.
		if(user.equalsIgnoreCase(answer)) {
			//if user gets it right it does the following.
			goneOnce = false;//goneOnce is used to see if user has already played once so it doesn't create another health bar or score board
			checkTime = true;//check time is used to calculate the response time
			score+=10;
			healthReceived();
			scoreBoard.setText("Score: " + score);
			complete.setText("Good Job!");
			countErr = 0;
			errCount.setText("");
			
		}else {
			checkTime = false;
			damageReceived();
			scoreBoard.setText("Score: " + score);
			complete.setText("Try again!");
			countErr++;
			errCount.setText("Error count: " + countErr);
		}
        healthBar.setValue(health);//after running through the if statement above, updates health bar
        if(health == 0) {//checks if health bar is gone after updating
        	gameOverScreen();
        }
		if(checkTime) {//checktime is used to calculate response time and transition to the category 'screen' again.
			//calculates response
			double endTime = System.currentTimeMillis();
			double response = (endTime -startTime)/1000.0;
			totalTime += response;
			time.setText("Response Time: " + response + " sec");
			
			//javax.swing.timer is used to delay the transition between the game screen to category screen by 1500 milliseconds
			javax.swing.Timer timer = new javax.swing.Timer(1500, event ->{
    			p.setVisible(false);
    			categoryScreen();
			});
			timer.setRepeats(false);
			timer.start();
		}
	}
	//class keyPress is used to check whether or not the key pressed from the user was the enter key
	public class keyPress implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {//if user pressed enter key, it will check the text by calling checktext method
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
	//time tracer class is used for when user pressed down the enter button on the gui instead
	public class timeTracer implements ActionListener{
		public void actionPerformed(ActionEvent e) {//calls check text if it happens
			checkText();
		}
	}
	//categoryHandler is used during the time the user decides on which category they want.
	public class categoryHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			howManyQuestion++;//increments howManyQuestion which is used for the game over screen when calculating avg response time.
			
			if(event.getSource() == fruit) {//event.getSource is used to evaluate what button the user pressed	
				try {
					startTime = System.currentTimeMillis();//begins the time when user presses a button
					runWordGame(word.randomizer(1),"Fruits");//calls runWordGame that takes in a hashmap and string which is required for the game screen
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
	//gameOver is used to either reset the game or exit if user pressed the button
	public class gameOver implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == again) {
				//'resets' entire game by resetting all values to default and calls gui again.
				health=50;
				score = 0;
				countErr = 0;
				howManyQuestion = 0;
				totalTime = 0.0;
				goneOnce = true;
				checkTime = false;
				jp.setVisible(false);
				categoryScreen();
				
			}else if(event.getSource() == quit) {//quits game if user pressed quit
				System.exit(0);
			}
		}
	}
}