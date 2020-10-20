package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.mygdx.game.common.SampleFactory;
import com.mygdx.game.common.SampleInfos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;


    // AWT Abstract Window Toolkit
    // enables us to embed libdgx app/game into java desktop app
    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList sampleList;
    private JPanel controlPanel;

    public GdxSamplerLauncher() throws HeadlessException{
        init();
    }

    private void init() {
        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lwjglAWTCanvas != null){
                    // stop will call our dispose and stop libgdx app
                    lwjglAWTCanvas.stop();
                }
            }
        });

        //JFrame settings
        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // tell window (JFrame) to resize and layout our components
        pack();
        setVisible(true);
    }

    private void createControlPanel(){
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //constraints for scroll pane
        c.gridx = 0; // column
        c.gridy = 0; // row
        c.fill = GridBagConstraints.VERTICAL; // fill vertically
        c.weighty = 1; // weight used when fill empty space

        sampleList = new JList(SampleInfos.getSampleNames().toArray());
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    launchSelectedSample();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);

        // constraints for button
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL; //Fill horizontally
        c.weighty = 0;

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedSample();
            }
        });

        controlPanel.add(launchButton, c);
    }


    private void launchSelectedSample(){
        String sampleName = (String) sampleList.getSelectedValue();

        if (sampleName == null || sampleName.isEmpty()) {
            System.out.println("Sample name is empty cannot launch");
            return;
        }

        launchSample(sampleName);
    }
    private void launchSample(String name){
        System.out.println("launching sample name = " + name);

        Container container = getContentPane();

        if(lwjglAWTCanvas != null){
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample = SampleFactory.newSample(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH,HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();

    }

    public static void main(String[] args) {
        // must be used to run our JFrame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }
}

