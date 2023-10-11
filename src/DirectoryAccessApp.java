package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DirectoryAccessApp extends JFrame {

    private StringBuilder dirList = new StringBuilder();

    private JTextField inputCurDir;
    private JPasswordField inputPasswd;

    public DirectoryAccessApp() {
        initializeUI();
    }

    public StringBuilder getDirList() {
        return dirList;
    }

    public void setInputCurDirText(String text) {
        inputCurDir.setText(text);
    }

    private void initializeUI() {
        setTitle("Программа для закрытия/открытия каталога на запись.");
        setBounds(500, 500, 550, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridLayout(0, 2));

        inputCurDir = new JTextField(dirList.toString(), 30);
        JLabel labelCurDir = new JLabel("Каталог: ");
        JLabel labelPasswd = new JLabel("Пароль: ");

        JButton button1 = createButton("Открыть каталог на запись", false);
        JButton button2 = createButton("Закрыть каталог на запись", true);
        inputPasswd = new JPasswordField("");

        container.add(labelCurDir);
        container.add(inputCurDir);

        container.add(labelPasswd);
        container.add(inputPasswd);

        container.add(button1);
        container.add(button2);
    }

    private JButton createButton(String label, boolean lock) {
        JButton button = new JButton(label);

        button.addActionListener(e -> {
            String curDir = inputCurDir.getText();
            char[] passwdChar = inputPasswd.getPassword();
            buttonPress(curDir, passwdChar, lock, lock ? "закрыт(ы)" : "открыт(ы)");
        });

        return button;
    }

    protected void buttonPress(String curDir, char[] passwdChar, boolean lock, String str) {
        if (checks(curDir) != 0) return;
        String passwd = new String(passwdChar);
        if (checkPasswd(passwd, curDir) != 0) return;

        for (String dir : dirList.toString().split(" ")) {
            File file = new File(dir);
            if (lock) {
                file.setReadOnly();
            } else {
                file.setWritable(true);
            }
        }

        JOptionPane.showMessageDialog(null, "Каталог(и) " + str + " на запись");
        setVisible(false);
        System.exit(0);
    }

    protected int checks(String folder) {
        for (String curStr : folder.split(" ")) {
            File f = new File(curStr);

            if (curStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Каталог не указан!");
                return 1;
            }

            if (!f.exists()) {
                JOptionPane.showMessageDialog(null, "Каталог " + curStr + " не существует!");
                return 1;
            }
        }
        return 0;
    }

    protected int checkPasswd(String ps1, String inpCD) {
        char[] chars = inpCD.toCharArray();
        String curPasswd = chars[0] + "" + chars[0] + "" + chars[1] + "" + chars[1];
        if (!ps1.equals(curPasswd)) {
            JOptionPane.showMessageDialog(null, "Неверный пароль!");
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DirectoryAccessApp app = new DirectoryAccessApp();
            StringBuilder dirList = app.getDirList();
            for (String dir : args) {
                dirList.append(dir).append(" ");
            }
            app.setInputCurDirText(dirList.toString().trim());
            app.setVisible(true);
        });
    }
}