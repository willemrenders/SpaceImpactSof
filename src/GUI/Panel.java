package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Collections;
import javax.sound.sampled.*;

import objects.*;

class Paneel extends JPanel {

    private ImageIcon bgImg;
    
    private ImageIcon imgStart, imgStartHover, imgKiesVliegtuig, imgKiesVliegtuigHover, imgHighscores, imgHighscoresHover;
    
    private ImageIcon imgVliegtuig01, imgVliegtuig02, imgVliegtuig03, imgVliegtuig04;
    
    private ImageIcon imgTerug, imgTerugHover;
    
    private ImageIcon imgOpslaan, imgOpslaanHover;

    private Timer timer;

    private Plane plane;

    private Raket raket;
    
    private Levens levens;
    
    private Punten punten;
    
    private Level level;
    
    private Pauze pauze;

    private PlainTegenstander plaintegenstander;
    
    private RaketTegenstander rakettegenstander;

    private ArrayList<Raket> raketten;

    private ArrayList<PlainTegenstander> plaintegenstanders;
    
    private ArrayList<RaketTegenstander> rakettegenstanders;
    
    private ArrayList<String> highscores = new ArrayList<String>();

    private Random rand = new Random();
    
    private int teller, rand1, rand2, rand3, rand4;
    
    private int start;
    
    private Boolean opgeslagen;
    
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
        raketten = new ArrayList<Raket>();
        plaintegenstanders = new ArrayList<PlainTegenstander>();
        rakettegenstanders = new ArrayList<RaketTegenstander>();

        plain = new Plain(0, 300);
        raket = new Raket(0, 337);
        levens = new Levens();
        punten = new Punten();
        level = new Level();
        pauze = new Pauze();
        pauze.setActief(true);
        plaintegenstander = new PlainTegenstander(1000, 300, 1);
        rakettegenstander = new RaketTegenstander(1000, 300, 1);
        
        opgeslagen = false;
        
        bgImg = new ImageIcon(SpaceImpactV2.class.getResource("/images/spaceImpactBackground.jpg"));
        imgStart = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgStart.png"));
        imgStartHover = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgStartHover.png"));
        imgKiesVliegtuig = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgKiesVliegtuig.png"));
        imgKiesVliegtuigHover = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgKiesVliegtuigHover.png"));
        imgHighscores = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgHighscores.png"));
        imgHighscoresHover = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgHighscoresHover.png"));
        
        imgVliegtuig01 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain01.png"));
        imgVliegtuig02 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain02.png"));
        imgVliegtuig03 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain03.png"));
        imgVliegtuig04 = new ImageIcon(SpaceImpactV2.class.getResource("/images/plain04.png"));
        
        imgTerug = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgTerug.png"));
        imgTerugHover = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgTerugHover.png"));
        
        imgOpslaan = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgOpslaan.png"));
        imgOpslaanHover = new ImageIcon(SpaceImpactV2.class.getResource("/images/imgOpslaanHover.png"));
        

        timer = new Timer(10, new TimerHandler());
        
        teller = 0;
        rand1 = 200;
        rand2 = 600;
        rand3 = 800;
        rand4 = 1000;
        
        start = 0;
        
        moeilijkheidsgraad = 1;
        puntenMultiplier = 3;
        
        
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
        levens.teken(g);
        punten.teken(g);
        level.teken(g);

        for (Raket r : raketten) {
            if (r.isActief()) {
                r.teken(g);
            }
        }
        
        plain.teken(g);
        
        for (RaketTegenstander rt : rakettegenstanders) {
            if (rt.isActief()) {
                rt.teken(g);
            }
        }

        for (PlainTegenstander p : plaintegenstanders) {
            if (p.isActief()) {
                p.teken(g);
            }
        }
        
        if (levens.getAantal() == 0) {
            levens.gedaan(g);
            plain.tekenDood(g);
            scrbMoeilijkheidsgraad.setEnabled(true);
            if (opgeslagen == false) {
                labelOpslaan.setVisible(true);
                txtOpslaan.setVisible(true);
                btnOpslaan.setVisible(true);
            }
            
        }
        
        if (pauze.isActief() == true) {
            pauze.teken(g);
        }
        
        if (highscores.size() > 5) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g.drawString("HIGHSCORES", 245, 150);
            for (int aantalScores = highscores.size() - 1; aantalScores >= highscores.size() - 5; aantalScores--) {
                g.drawString(highscores.get(aantalScores), 245, 245 - (aantalScores - highscores.size() + 1) * 55);
            }
            highscores.clear();
            
        }
        if (highscores.size() > 0 && highscores.size() <= 5) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("HIGHSCORES", 245, 150);
            for (int aantalScores = highscores.size() - 1; aantalScores >= 0; aantalScores--) {
                g.drawString(highscores.get(aantalScores), 245, 245 - (aantalScores - highscores.size() + 1) * 55);
            }
            highscores.clear();
        }

        this.requestFocusInWindow();
    }

    public void clean(){
        ArrayList<PlainTegenstander> plaintegenstandersNew = new ArrayList<PlainTegenstander>();
        for (PlainTegenstander p: plaintegenstanders){
            if (p.isActief()){
                plaintegenstandersNew.add(p);
            }
        }
        plaintegenstanders = plaintegenstandersNew;
        
        ArrayList<Raket> rakettenNew = new ArrayList<Raket>();
        for (Raket r: raketten){
            if (r.isActief()){
                rakettenNew.add(r);
            }
        }
        raketten = rakettenNew;
        
        ArrayList<RaketTegenstander> raketTegenstandersNew = new ArrayList<RaketTegenstander>();
        for (RaketTegenstander rt: rakettegenstanders){
            if (rt.isActief()){
                raketTegenstandersNew.add(rt);
            }
        }
        rakettegenstanders = raketTegenstandersNew;
        
    }
    
    public void botsing() {
        for (Raket r : raketten) {
            if (r.isActief()) {
                for (PlainTegenstander p : plaintegenstanders) {
                    if (p.isActief()) {
                        if ((r.getX()) + r.getBreedte() >= p.getX()
                                && r.getX() + r.getBreedte() <= p.getX() + p.getGrootte()
                                && r.getY() + r.getHoogte() > p.getY()
                                && r.getY() < p.getY() + p.getGrootte()) {
                            
                            try {
                                streamExplosion = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/explosion.wav"));
                                formatExplosion = streamExplosion.getFormat();
                                infoExplosion = new DataLine.Info(Clip.class, formatExplosion);
                                clipExplosion = (Clip) AudioSystem.getLine(infoExplosion);
                                clipExplosion.open(streamExplosion);
                                clipExplosion.start();
                            }
                            
                            catch (Exception e) {
                                
                            }
                            
                            if (p.getType() == 1) {
                                r.setActief(false);
                                p.setActief(false);
                                punten.setAantal(punten.getAantal() + 1 * puntenMultiplier);
                            }
                            if (p.getType() == 2) {
                                r.setActief(false);
                                p.setType(1);
                                punten.setAantal(punten.getAantal() + 1);
                            }
                            if (p.getType() == 3) {
                                r.setActief(false);
                                p.setActief(false);
                                punten.setAantal(punten.getAantal() + 5 * puntenMultiplier);
                            }
                            if (p.getType() == 4) {
                                r.setActief(false);
                                p.setActief(false);
                                punten.setAantal(punten.getAantal() + 15 * puntenMultiplier);
                            }
                            
                            
                        }
                    }
                }
            }
        }
        for (PlainTegenstander p : plaintegenstanders) {
            if (p.isActief()) {
                if (p.getY() + p.getGrootte() >= plain.getY()
                        && p.getY() <= plain.getY() + plain.getGrootte()
                        && p.getX() <= plain.getGrootte())
                {
                    try {
                        streamExplosion = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/explosion.wav"));
                        formatExplosion = streamExplosion.getFormat();
                        infoExplosion = new DataLine.Info(Clip.class, formatExplosion);
                        clipExplosion = (Clip) AudioSystem.getLine(infoExplosion);
                        clipExplosion.open(streamExplosion);
                        clipExplosion.start();
                    }
                    
                    catch (Exception e){
                        
                    }
                    
                    levens.setAantal(levens.getAantal() - 1);
                    p.setActief(false);
                }
            }
        }
        for (RaketTegenstander rt : rakettegenstanders) {
            if (rt.isActief()) {
                if (rt.getY() + rt.getHoogte() >= plain.getY()
                        && rt.getY() <= plain.getY() + plain.getGrootte()
                        && rt.getX() <= plain.getGrootte())
                {
                    try {
                        streamExplosion = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/explosion.wav"));
                        formatExplosion = streamExplosion.getFormat();
                        infoExplosion = new DataLine.Info(Clip.class, formatExplosion);
                        clipExplosion = (Clip) AudioSystem.getLine(infoExplosion);
                        clipExplosion.open(streamExplosion);
                        clipExplosion.start();
                    }
                    
                    catch (Exception e) {
                        
                    }
                    
                    levens.setAantal(levens.getAantal() - 1);
                    rt.setActief(false);
                }
                
            }
        }
        
        if (levens.getAantal() <= 0)
        {
            
            try {
                streamDead = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/dead.wav"));
                formatDead = streamDead.getFormat();
                infoDead = new DataLine.Info(Clip.class, formatDead);
                clipDead = (Clip) AudioSystem.getLine(infoDead);
                clipDead.open(streamDead);
                clipDead.start();
            }
            
            catch (Exception e) {
                
            }
            
            timer.stop();
            clipBackground.stop();
            clipExplosion.stop();
            repaint();
        }
    }

    public void beweeg() {
        for (Raket r : raketten) {
            r.beweeg();
            if (r.getX() > 1000) {
                r.setActief(false);
            }
        }
        for (PlainTegenstander p : plaintegenstanders) {
            p.beweeg();
            p.setTijd(p.getTijd() + 1);
            if (p.getX() < -90) {
                p.setActief(false);
            }
            
            if (p.getType() == 4) {
                p.beweeg();
                if (p.getTijd() == 120) {
                    rakettegenstanders.add(new RaketTegenstander(p.getX(), p.getY() + p.getGrootte() / 2 - 8, 4));
                    try {
                        streamLaunch = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/launch.wav"));
                        formatLaunch = streamLaunch.getFormat();
                        infoLaunch = new DataLine.Info(Clip.class, formatLaunch);
                        clipLaunch = (Clip) AudioSystem.getLine(infoLaunch);
                        clipLaunch.open(streamLaunch);
                        clipLaunch.start();
                    }
                    
                    catch (Exception e) {
                        
                    }
                    p.setTijd(20);
                }
            }
            
            else if (p.getType() == 3) {
                if (p.getTijd() == 110) {
                    rakettegenstanders.add(new RaketTegenstander(p.getX(), p.getY() + p.getGrootte() / 2 - 8, 2));
                    rakettegenstanders.add(new RaketTegenstander(p.getX(), p.getY() + p.getGrootte() / 2 - 8, 3));
                    try {
                        streamLaunch = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/launch.wav"));
                        formatLaunch = streamLaunch.getFormat();
                        infoLaunch = new DataLine.Info(Clip.class, formatLaunch);
                        clipLaunch = (Clip) AudioSystem.getLine(infoLaunch);
                        clipLaunch.open(streamLaunch);
                        clipLaunch.start();
                    }
                    
                    catch (Exception e) {
                        
                    }
                    p.setTijd(-100);
                }
            }
            
            else {
                if (p.getTijd() == 200) {
                    rakettegenstanders.add(new RaketTegenstander(p.getX(), p.getY() + p.getGrootte() / 2 - 8, 1));
                    try {
                        streamLaunch = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/launch.wav"));
                        formatLaunch = streamLaunch.getFormat();
                        infoLaunch = new DataLine.Info(Clip.class, formatLaunch);
                        clipLaunch = (Clip) AudioSystem.getLine(infoLaunch);
                        clipLaunch.open(streamLaunch);
                        clipLaunch.start();
                    }
                    
                    catch (Exception e) {
                        
                    }
                    p.setTijd(0);
                }
            }
        }
        for (RaketTegenstander rt : rakettegenstanders) {
            rt.beweeg();
            if (rt.getType() == 2) {
                rt.beweegOmhoog();
            }
            if (rt.getType() == 3) {
                rt.beweegOmlaag();
            }
            if (rt.getType() == 4) {
                rt.beweeg();
            }
            if (rt.getX() < -52) {
                rt.setActief(false);
            }
        }
    }

    class TimerHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) { 
            if (teller < 30) {
                if (rand.nextInt((int)(moeilijkheidsgraad * rand1)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 1));
                    teller+=1;
                }
            }
            
            if (teller >= 30 && teller < 60) {
                level.setAantal(2);
                if (rand.nextInt((int)(moeilijkheidsgraad * rand1)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 1));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand2)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 2));
                    teller+=1;
                }
            }
            
            if (teller >= 60 && teller < 90) {
                level.setAantal(3);
                if (rand.nextInt((int)(moeilijkheidsgraad * rand1)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 1));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand2)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 2));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand3)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 3));
                    teller+=1;
                }
            }
            if (teller >= 90 && teller < 120) {
                level.setAantal(4);
                if (rand.nextInt((int)(moeilijkheidsgraad * rand1)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 1));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand2)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 2));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand3)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 3));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand4)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 4));
                    teller+=1;
                }
            }
            
            if (teller >= 120) {
                if (rand.nextInt((int)(moeilijkheidsgraad * rand1)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 1));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand2)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 2));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand3)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 3));
                    teller+=1;
                }
                if (rand.nextInt((int)(moeilijkheidsgraad * rand4)) == 1) {
                    plaintegenstanders.add(new PlainTegenstander(1000, rand.nextInt(600), 4));
                    teller+=1;
                }
            }
            
            if (teller == 120) {
                level.setAantal(5);
                rand1 = 400;
                rand2 = 400;
                rand3 = 600;
                rand4 = 800;
            }
            
            if (teller == 150) {
                level.setAantal(6);
                rand1 = 400;
                rand2 = 400;
                rand3 = 600;
                rand4 = 600;
            }
            
            if (teller == 180) {
                level.setAantal(7);
                rand1 = 400;
                rand2 = 400;
                rand3 = 400;
                rand4 = 600;
            }
            
            if (teller == 210) {
                level.setAantal(8);
                rand1 = 400;
                rand2 = 400;
                rand3 = 400;
                rand4 = 400;
            }
            
            if (teller == 240) {
                level.setAantal(9);
                rand1 = 300;
                rand2 = 300;
                rand3 = 300;
                rand4 = 300;
            }
            
            if (teller == 270) {
                level.setAantal(10);
                rand1 = 200;
                rand2 = 200;
                rand3 = 200;
                rand4 = 200;
            }
            
            if (teller == 300) {
                level.setAantal(11);
                rand1 = 150;
                rand2 = 150;
                rand3 = 150;
                rand4 = 150;
            }
            
            if (teller == 330) {
                level.setAantal(12);
                rand1 = 100;
                rand2 = 100;
                rand3 = 100;
                rand4 = 100;
            }
            
            if (teller == 360) {
                level.setAantal(13);
                rand1 = 50;
                rand2 = 50;
                rand3 = 50;
                rand4 = 50;
            }
            
            beweeg();
            botsing();
            clean();
            
            System.out.println("# raketten:" + raketten.size());
            System.out.println("# plaintegenstanders:" + plaintegenstanders.size());
            System.out.println("# rakettegenstanders:" + rakettegenstanders.size());
            repaint();
            
        }
    }

    class ToetsenbordHandler extends KeyAdapter {

        public void keyPressed(KeyEvent ke) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    plain.omlaag();
                    break;
                case KeyEvent.VK_UP:
                    plain.omhoog();
                    break;
            }
        }

        public void keyTyped(KeyEvent ke) {
            switch (ke.getKeyChar()) {
                case KeyEvent.VK_SPACE:
                    raketten.add(new Raket(0, plain.getY() + 37));
                    try {
                        streamLaunch = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/launch.wav"));
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
                    streamBackground = AudioSystem.getAudioInputStream(SpaceImpactV2.class.getResource("/sounds/background.wav"));
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