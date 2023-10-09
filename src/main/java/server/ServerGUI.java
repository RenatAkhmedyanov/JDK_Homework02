package server;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerGUI extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    Server server;

    JButton btnStart, btnStop;
    JTextArea log;
    List<Client> clientList;

    public ServerGUI(Server server){
        clientList = new ArrayList<>();
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }


    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();

            }
        });

        headerPanel.add(btnStart);
        headerPanel.add(btnStop);
        return headerPanel;
    }


    public void appendLog(String text) {
        log.append(text + "\n");
    }

    @Override
    public void answerAll(String text) {
        for (Client client : clientList) {
            client.serverAnswer(text);
        }
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }



    @Override
    public void addList(Client client) {
        clientList.add(client);
    }
}
