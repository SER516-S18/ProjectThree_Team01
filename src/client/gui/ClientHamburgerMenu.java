package client.gui;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Desktop;
import java.net.URI;

import client.gui.actions.*;

/**
 * This class creates a hamburger menu for the client application 
 *
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-04
 *
 */

public class ClientHamburgerMenu extends JMenuBar {
	
	public ClientHamburgerMenu() {
	
	JMenu menu = new JMenu("MENU");
	menu.setIcon(new ImageIcon("img/icons8-menu-10.png"));
	add(menu);
	JMenu applicationsOption = new JMenu("APPLICATION");
	menu.add(applicationsOption);
	
	JMenuItem composerOption = new JMenuItem("EMOTIV Xavier Composer");
	applicationsOption.add(composerOption);
	composerOption.addActionListener(new ClientActionEvents(this, "EMOTIV Xavier Composer"));
	
	JMenu connectOption = new JMenu("CONNECT");
	menu.add(connectOption);
	
	JMenuItem connectComposer = new JMenuItem("Connect Composer");
	connectComposer.addActionListener(new ClientActionEvents(this, "Connect Composer"));
	connectOption.add(connectComposer);
	
	JMenu detectionsOption = new JMenu("DETECTIONS");
	menu.add(detectionsOption);
	
	JMenuItem facialExpressions = new JMenuItem("Facial Expressions");
	detectionsOption.add(facialExpressions);
	facialExpressions.addActionListener(new ClientActionEvents(this, "Facial Expressions"));
	
	JMenuItem performanceMetrices = new JMenuItem("Performance Metrics");
	detectionsOption.add(performanceMetrices);
	performanceMetrices.addActionListener(new ClientActionEvents(this, "Performance Metrics"));
		
	JMenu helpOption = new JMenu("HELP");
	menu.add(helpOption);
	
	JMenuItem emotivOnGithub = new JMenuItem("Emotiv On Github");
	helpOption.add(emotivOnGithub);
	emotivOnGithub.addActionListener(new ClientActionEvents(this, "Git"));

	
	JMenuItem aboutOption = new JMenuItem("About Xavier Control Panel");
	helpOption.add(aboutOption);
}
}
