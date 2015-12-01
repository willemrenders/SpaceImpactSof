package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.Timer;

import GUI.Paneel.KnopHandler;
import GUI.Paneel.SchuifHandler;
import GUI.Paneel.ToetsenbordHandler;
import plane.Plane;

class Paneel extends JPanel {

    private ImageIcon bgImg;
    
    private ImageIcon imgStart, imgStartHover, imgKiesVliegtuig, imgKiesVliegtuigHover, imgHighscores, imgHighscoresHover;
    
    private ImageIcon imgVliegtuig01, imgVliegtuig02, imgVliegtuig03, imgVliegtuig04;
    
    private ImageIcon imgTerug, imgTerugHover;
    
    private ImageIcon imgOpslaan, imgOpslaanHover;

    private Timer timer;

    private Random rand = new Random();
    
    private AudioInputStream streamExplosion, streamBackground, streamDead, streamLaunch;
    private AudioFormat formatExplosion, formatBackground, formatDead, formatLaunch;
    private DataLine.Info infoExplosion, infoBackground, infoDead, infoLaunch;
    private Clip clipExplosion, clipBackground, clipDead, clipLaunch;
    
    private JButton btnStart, btnKiesVliegtuig, btnHighscores;
    private JButton btnVliegtuig01, btnVliegtuig02, btnVliegtuig03, btnVliegtuig04;
    private JButton btnTerug;
    private JButton btnOpslaan;
    private JLabel labelMenu, labelOpslaan, labelHighscores;
    private JTextField txtOpslaan;
    
    private JLabel labelMoeilijkheidsgraad, labelMoeilijk, labelMakkelijk;
    private JScrollBar scrbMoeilijkheidsgraad;
    private double moeilijkheidsgraad;
    private int puntenMultiplier;
    
    public Paneel() {        
        
    	
    	//Images
        bgImg = new ImageIcon(GUI.class.getResource("/images/spaceImpactBackground.jpg"));
        imgStart = new ImageIcon(GUI.class.getResource("/images/imgStart.png"));
        imgStartHover = new ImageIcon(GUI.class.getResource("/images/imgStartHover.png"));
        imgKiesVliegtuig = new ImageIcon(GUI.class.getResource("/images/imgKiesVliegtuig.png"));
        imgKiesVliegtuigHover = new ImageIcon(GUI.class.getResource("/images/imgKiesVliegtuigHover.png"));
        imgHighscores = new ImageIcon(GUI.class.getResource("/images/imgHighscores.png"));
        imgHighscoresHover = new ImageIcon(GUI.class.getResource("/images/imgHighscoresHover.png"));
        
        imgVliegtuig01 = new ImageIcon(GUI.class.getResource("/images/plain01.png"));
        imgVliegtuig02 = new ImageIcon(GUI.class.getResource("/images/plain02.png"));
        imgVliegtuig03 = new ImageIcon(GUI.class.getResource("/images/plain03.png"));
        imgVliegtuig04 = new ImageIcon(GUI.class.getResource("/images/plain04.png"));
        
        imgTerug = new ImageIcon(GUI.class.getResource("/images/imgTerug.png"));
        imgTerugHover = new ImageIcon(GUI.class.getResource("/images/imgTerugHover.png"));
        
        imgOpslaan = new ImageIcon(GUI.class.getResource("/images/imgOpslaan.png"));
        imgOpslaanHover = new ImageIcon(GUI.class.getResource("/images/imgOpslaanHover.png"));
        
        Plane plane;
        
        ActionListener handler = new KnopHandler();
        
        SchuifHandler schuifhandler = new SchuifHandler();
        
        labelMenu = new JLabel("");
        btnStart = new JButton("Start");
        btnStart.addActionListener(handler);
        btnKiesVliegtuig = new JButton("Kies Vliegtuig");
        btnKiesVliegtuig.addActionListener(handler);
        btnHighscores = new JButton("Highscores");
        btnHighscores.addActionListener(handler);
        
        btnVliegtuig01 = new JButton("Vliegtuig01");
        btnVliegtuig01.addActionListener(handler);
        btnVliegtuig02 = new JButton("Vliegtuig02");
        btnVliegtuig02.addActionListener(handler);
        btnVliegtuig03 = new JButton("Vliegtuig03");
        btnVliegtuig03.addActionListener(handler);
        btnVliegtuig04 = new JButton("Vliegtuig04");
        btnVliegtuig04.addActionListener(handler);
        
        btnTerug = new JButton("");
        btnTerug.addActionListener(handler);
        
        labelOpslaan = new JLabel("");
        btnOpslaan = new JButton("Opslaan");
        txtOpslaan = new JTextField();
        btnOpslaan.addActionListener(handler);
        
        labelHighscores = new JLabel("");
        
        
        labelMoeilijkheidsgraad = new JLabel("Moeilijkheidsgraad");
        labelMoeilijk = new JLabel("Moeilijk");
        labelMakkelijk = new JLabel("Makkelijk");
        scrbMoeilijkheidsgraad = new JScrollBar(JScrollBar.HORIZONTAL, 2, 1, 1, 4);
        scrbMoeilijkheidsgraad.addAdjustmentListener(schuifhandler);
        
        labelMenu.setPreferredSize(new Dimension(900, 250));
        btnStart.setPreferredSize(new Dimension(510, 50));
        btnKiesVliegtuig.setPreferredSize(new Dimension(510, 50));
        btnHighscores.setPreferredSize(new Dimension(510, 50));
        
        btnVliegtuig01.setPreferredSize(new Dimension(90, 90));
        btnVliegtuig02.setPreferredSize(new Dimension(90, 90));
        btnVliegtuig03.setPreferredSize(new Dimension(90, 90));
        btnVliegtuig04.setPreferredSize(new Dimension(90, 90));
        
        btnTerug.setPreferredSize(new Dimension(700, 50));
        
        labelOpslaan.setPreferredSize(new Dimension(900, 550));
        txtOpslaan.setPreferredSize(new Dimension(510, 50));
        btnOpslaan.setPreferredSize(new Dimension(510, 50));
        
        labelHighscores.setPreferredSize(new Dimension(900, 300));
        
        labelMoeilijkheidsgraad.setPreferredSize(new Dimension(900, 50));
        labelMoeilijk.setPreferredSize(new Dimension(250, 25));
        labelMakkelijk.setPreferredSize(new Dimension(250, 25));
        scrbMoeilijkheidsgraad.setPreferredSize(new Dimension(510, 25));
        
        
        
        btnStart.setContentAreaFilled(false);
        btnStart.setBorder(null);
        btnStart.setIcon(imgStart);
        btnStart.setRolloverIcon(imgStartHover);
        btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKiesVliegtuig.setContentAreaFilled(false);
        btnKiesVliegtuig.setBorder(null);
        btnKiesVliegtuig.setIcon(imgKiesVliegtuig);
        btnKiesVliegtuig.setRolloverIcon(imgKiesVliegtuigHover);
        btnKiesVliegtuig.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHighscores.setContentAreaFilled(false);
        btnHighscores.setBorder(null);
        btnHighscores.setIcon(imgHighscores);
        btnHighscores.setRolloverIcon(imgHighscoresHover);
        btnHighscores.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnVliegtuig01.setContentAreaFilled(false);
        btnVliegtuig01.setIcon(imgVliegtuig01);
        btnVliegtuig01.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVliegtuig02.setContentAreaFilled(false);
        btnVliegtuig02.setIcon(imgVliegtuig02);
        btnVliegtuig02.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVliegtuig03.setContentAreaFilled(false);
        btnVliegtuig03.setIcon(imgVliegtuig03);
        btnVliegtuig03.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVliegtuig04.setContentAreaFilled(false);
        btnVliegtuig04.setIcon(imgVliegtuig04);
        btnVliegtuig04.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnTerug.setContentAreaFilled(false);
        btnTerug.setBorder(null);
        btnTerug.setIcon(imgTerug);
        btnTerug.setRolloverIcon(imgTerugHover);
        btnTerug.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnOpslaan.setContentAreaFilled(false);
        btnOpslaan.setBorder(null);
        btnOpslaan.setIcon(imgOpslaan);
        btnOpslaan.setRolloverIcon(imgOpslaanHover);
        btnOpslaan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtOpslaan.setFont(new Font("TimesRoman", Font.BOLD, 40));
        txtOpslaan.setHorizontalAlignment(JTextField.CENTER);
        
        
        labelMoeilijkheidsgraad.setFont(new Font("TimesRoman", Font.BOLD, 30));
        labelMoeilijkheidsgraad.setHorizontalAlignment(JLabel.CENTER);
        labelMoeilijkheidsgraad.setVerticalAlignment(JLabel.BOTTOM);
        labelMoeilijk.setFont(new Font("TimesRoman", Font.BOLD, 20));
        labelMoeilijk.setHorizontalAlignment(JLabel.LEFT);
        labelMakkelijk.setFont(new Font("TimesRoman", Font.BOLD, 20));
        labelMakkelijk.setHorizontalAlignment(JLabel.RIGHT);
        
        
        add(labelMenu);
        add(btnStart);
        add(btnKiesVliegtuig);
        add(btnHighscores);
        
        add(btnVliegtuig01);
        add(btnVliegtuig02);
        add(btnVliegtuig03);
        add(btnVliegtuig04);
        
        
        add(labelHighscores);
        
        
        add(btnTerug);
        
        add(labelOpslaan);
        add(txtOpslaan);
        add(btnOpslaan);
        
        add(labelMoeilijkheidsgraad);
        add(labelMoeilijk);
        add(labelMakkelijk);
        add(scrbMoeilijkheidsgraad);
        
        
        
        btnVliegtuig01.setVisible(false);
        btnVliegtuig02.setVisible(false);
        btnVliegtuig03.setVisible(false);
        btnVliegtuig04.setVisible(false);
        
        btnTerug.setVisible(false);
        
        labelOpslaan.setVisible(false);
        txtOpslaan.setVisible(false);
        btnOpslaan.setVisible(false);
        
        labelHighscores.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bgImg.paintIcon(this, g, 0, 0);
        
        //Paintcomponent shit check original

        this.requestFocusInWindow();
    }

    
    class ToetsenbordHandler extends KeyAdapter {

        public void keyPressed(KeyEvent ke) {
            Plane plane;
			switch (ke.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    plane.omlaag();
                    break;
                case KeyEvent.VK_UP:
                	plane.omhoog();
                    break;
            }
        }

        public void keyTyped(KeyEvent ke) {
            switch (ke.getKeyChar()) {
                case KeyEvent.VK_SPACE:
                	
                	//TODO spawnRAKET
                    
                    try {
                        streamLaunch = AudioSystem.getAudioInputStream(GUI.class.getResource("/sounds/launch.wav"));
                        formatLaunch = streamLaunch.getFormat();
                        infoLaunch = new DataLine.Info(Clip.class, formatLaunch);
                        clipLaunch = (Clip) AudioSystem.getLine(infoLaunch);
                        clipLaunch.open(streamLaunch);
                        clipLaunch.start();
                    }
                    
                    catch (Exception e) {
                        
                    }
                    
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (pauze.isActief() == false) {
                        timer.stop();
                        pauze.setActief(true);
                        clipBackground.stop();
                        repaint();
                        labelMenu.setVisible(true);
                        btnStart.setVisible(true);
                        btnKiesVliegtuig.setVisible(true);
                        btnHighscores.setVisible(true);
                        btnVliegtuig01.setVisible(false);
                        btnVliegtuig02.setVisible(false);
                        btnVliegtuig03.setVisible(false);
                        btnVliegtuig04.setVisible(false);
                        btnTerug.setVisible(false);
                        labelOpslaan.setVisible(false);
                        txtOpslaan.setVisible(false);
                        btnOpslaan.setVisible(false);
                        labelHighscores.setVisible(false);
                        labelMoeilijkheidsgraad.setVisible(true);
                        labelMoeilijk.setVisible(true);
                        labelMakkelijk.setVisible(true);
                        scrbMoeilijkheidsgraad.setVisible(true);
                        
                    }
                    else {
                        timer.start();
                        pauze.setActief(false);
                        clipBackground.loop(10);
                        labelMenu.setVisible(false);
                        btnStart.setVisible(false);
                        btnKiesVliegtuig.setVisible(false);
                        btnHighscores.setVisible(false);
                        btnVliegtuig01.setVisible(false);
                        btnVliegtuig02.setVisible(false);
                        btnVliegtuig03.setVisible(false);
                        btnVliegtuig04.setVisible(false);
                        btnTerug.setVisible(false);
                        labelOpslaan.setVisible(false);
                        txtOpslaan.setVisible(false);
                        btnOpslaan.setVisible(false);
                        labelHighscores.setVisible(false);
                        labelMoeilijkheidsgraad.setVisible(false);
                        labelMoeilijk.setVisible(false);
                        labelMakkelijk.setVisible(false);
                        scrbMoeilijkheidsgraad.setVisible(false);
                    }
                    
            }
        }

    }
    
    class KnopHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart)
            {
                raketten.clear();
                plaintegenstanders.clear();
                rakettegenstanders.clear();
                plain.setY(300);
                levens.setAantal(5);
                punten.setAantal(0);
                level.setAantal(1);
                pauze.setActief(false);
                teller = 0;
                labelMenu.setVisible(false);
                btnStart.setVisible(false);
                btnKiesVliegtuig.setVisible(false);
                btnHighscores.setVisible(false);
                labelOpslaan.setVisible(false);
                txtOpslaan.setVisible(false);
                btnOpslaan.setVisible(false);
                labelMoeilijkheidsgraad.setVisible(false);
                labelMoeilijk.setVisible(false);
                labelMakkelijk.setVisible(false);
                scrbMoeilijkheidsgraad.setVisible(false);
                scrbMoeilijkheidsgraad.setEnabled(false);
                rand1 = 200;
                rand2 = 600;
                rand3 = 800;
                rand4 = 1000;
                if (start == 0) {
                    start++;
                    addKeyListener(new ToetsenbordHandler());
                }
                
                timer.start();
                try {
                    streamBackground = AudioSystem.getAudioInputStream(GUI.class.getResource("/sounds/background.wav"));
                    formatBackground = streamBackground.getFormat();
                    infoBackground = new DataLine.Info(Clip.class, formatBackground);
                    clipBackground = (Clip) AudioSystem.getLine(infoBackground);
                    clipBackground.open(streamBackground);
                    clipBackground.loop(10);

                }
                
                catch (Exception d) {

                }
                
                opgeslagen = false;
            }
            if (e.getSource() == btnKiesVliegtuig)
            {
                btnStart.setVisible(false);
                btnKiesVliegtuig.setVisible(false);
                btnHighscores.setVisible(false);
                labelMoeilijkheidsgraad.setVisible(false);
                labelMoeilijk.setVisible(false);
                labelMakkelijk.setVisible(false);
                scrbMoeilijkheidsgraad.setVisible(false);
                btnVliegtuig01.setVisible(true);
                btnVliegtuig02.setVisible(true);
                btnVliegtuig03.setVisible(true);
                btnVliegtuig04.setVisible(true);
                btnTerug.setVisible(true);
                
            }
            if (e.getSource() == btnHighscores)
            {
                String inhoudLijn = "";
                try {
                    BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
                    inhoudLijn = in.readLine();
                    inhoudLijn = in.readLine();
                    while (inhoudLijn != null) {
                        highscores.add(inhoudLijn);
                        System.out.println(inhoudLijn);
                        inhoudLijn = in.readLine();
                        
                    }
                    in.close();
                    
                    
                    
                }
                
                catch (IOException d) {
                    
                }
                
                btnStart.setVisible(false);
                btnKiesVliegtuig.setVisible(false);
                btnHighscores.setVisible(false);
                labelHighscores.setVisible(true);
                btnTerug.setVisible(true);
                labelMoeilijkheidsgraad.setVisible(false);
                labelMoeilijk.setVisible(false);
                labelMakkelijk.setVisible(false);
                scrbMoeilijkheidsgraad.setVisible(false);
                
                Collections.sort(highscores);
                
                System.out.println(highscores);
                repaint();
                
                for (String counter: highscores) {
                    System.out.println(counter);
                }
                
            }
            if (e.getSource() == btnVliegtuig01)
            {
                plain.setType(1);
                repaint();
            }
            if (e.getSource() == btnVliegtuig02)
            {
                plain.setType(2);
                repaint();
            }
            if (e.getSource() == btnVliegtuig03)
            {
                plain.setType(3);
                repaint();
            }
            if (e.getSource() == btnVliegtuig04)
            {
                plain.setType(4);
                repaint();
            }
            if (e.getSource() == btnTerug)
            {
                btnStart.setVisible(true);
                btnKiesVliegtuig.setVisible(true);
                btnHighscores.setVisible(true);
                labelMoeilijkheidsgraad.setVisible(true);
                labelMoeilijk.setVisible(true);
                labelMakkelijk.setVisible(true);
                scrbMoeilijkheidsgraad.setVisible(true);
                btnVliegtuig01.setVisible(false);
                btnVliegtuig02.setVisible(false);
                btnVliegtuig03.setVisible(false);
                btnVliegtuig04.setVisible(false);
                btnTerug.setVisible(false);
                labelHighscores.setVisible(false);
                repaint();
            }
            if (e.getSource() == btnOpslaan)
            {
                opgeslagen = true;
                labelOpslaan.setVisible(false);
                txtOpslaan.setVisible(false);
                btnOpslaan.setVisible(false);
                
                try {
                    BufferedWriter uit = new BufferedWriter(new FileWriter("highscores.txt", true));
                    uit.newLine();
                    if (punten.getAantal() < 10) {
                        uit.write("0000" + punten.getAantal() + ": " + txtOpslaan.getText());
                    }
                    if (punten.getAantal() < 100 && punten.getAantal() >= 10) {
                        uit.write("000" + punten.getAantal() + ": " + txtOpslaan.getText());
                    }
                    if (punten.getAantal() < 1000 && punten.getAantal() >= 100) {
                        uit.write("00" + punten.getAantal() + ": " + txtOpslaan.getText());
                    }
                    if (punten.getAantal() < 10000 && punten.getAantal() >= 1000) {
                        uit.write("0" + punten.getAantal() + ": " + txtOpslaan.getText());
                    }
                    if (punten.getAantal() < 100000 && punten.getAantal() >= 10000) {
                        uit.write("" + punten.getAantal() + ": " + txtOpslaan.getText());
                    }
                    
                    uit.close();
                }
                
                catch (IOException d) {
                    
                }
                
            }
        }
    }
    
    class SchuifHandler implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            moeilijkheidsgraad = scrbMoeilijkheidsgraad.getValue() * 0.5;
            puntenMultiplier = (int)(6 / scrbMoeilijkheidsgraad.getValue());
        }
    }
}