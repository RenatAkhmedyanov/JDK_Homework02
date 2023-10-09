package server;

import client.Client;

public class Server {
    ServerView serverView;
    Repository repository;
    boolean work;

    public Server(Storage storage) {
        this.repository = storage;
        this.serverView = new ServerGUI(this);
    }

    public void start() {
        if (work) {
            printText("Сервер уже был запущен");
        } else {
            work = true;
            printText("Сервер запущен!");
        }
    }

    public void stop() {
        if (!work) {
            printText("Сервер уже был остановлен");
        } else {
            work = false;
            printText("Сервер остановлен!");
        }
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        addToList(client);
        return true;
    }

    public void sendMessage(String text) {
        if (!work) {
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    public String getLog() {
        return repository.readLog();
    }

    public void answerAll(String text) {
        serverView.answerAll(text);
    }

    public void saveInLog(String text) {
        repository.saveInLog(text);
    }


    public void printText(String text) {
        serverView.showMessage(text);
    }

    public void appendLog(String text) {
        serverView.appendLog(text);
    }
    private void addToList(Client client){
        serverView.addList(client);
    }
}
