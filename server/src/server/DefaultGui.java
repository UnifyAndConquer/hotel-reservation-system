package server;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// code inherited by client 
abstract class DefaultGui implements SimpleGui 
{
    protected Scanner inputScanner;
    protected PrintStream out;
    protected JButton button = new JButton("Blank");
    protected Socket socket;
    protected String name;
    protected int port;

    public DefaultGui(int port, String name) 
    {
        this.port = port;
        this.name = name;
    }

    @Override
    public void sendLine(String nextLine) 
    {
        button.setText(nextLine);
    }

    public void createGui() 
    {
        button.addActionListener(e -> actionPerformed(e));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.add(button);

        JFrame frame = new JFrame(getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    protected void actionPerformed(ActionEvent e) 
    {
        out.println("client says hello");
        out.flush();
        
        System.out.println("client says hello");
//        button.setText(getName());
    }

    public String getName() 
    {
        return name;
    }
}