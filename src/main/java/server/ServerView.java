package server;

import client.Client;

public interface ServerView {
    void appendLog(String text);
    void answerAll(String text);
    void showMessage(String text);
    void addList(Client client);

}
