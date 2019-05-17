import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class JFrame extends javax.swing.JFrame {

    public JFrame() {
        super("Программа для закрытия/открытия каталога на запись.");
                this.setBounds(500,500,550,180);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Container container = this.getContentPane();
                container.setLayout(new GridLayout(0,2));

                JTextField inputCurDir = new JTextField(CloseDir.getDir(), 30);
                JLabel labelCurDir = new JLabel("Каталог: ");

                JButton button1 = new JButton("Открыть каталог на запись");
                JButton button2 = new JButton("Закрыть каталог на запись");

                container.add(labelCurDir);
                container.add(inputCurDir);

                container.add(button1);
                container.add(button2);


        button1.addActionListener(e -> {

            if (checks(inputCurDir.getText()) != 0 ) {
                setVisible(false);
                JFrame app = new JFrame();
                app.setVisible(true);
                return;
            }

                    String command = "sudo chattr -R -i " + inputCurDir.getText();
                    try {
                          Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
                          process.waitFor();
                    } catch (IOException e1) {
                         e1.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Каталог открыт на запись");

                    setVisible(false);
                    System.exit(0);
                });

                button2.addActionListener(e -> {

                    if (checks(inputCurDir.getText()) != 0 ) {
                        setVisible(false);
                        JFrame app = new JFrame();
                        app.setVisible(true);
                        return;
                    }

                    String command = "sudo chattr -R +i " + inputCurDir.getText();
                    try {
                        Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
                        process.waitFor();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Каталог закрыт на запись");

                    setVisible(false);
                    System.exit(0);
                });

    }


    protected int checks(String folder) {

        File f = new File(folder);

        // Проверка на отсутствие введенных данных
        if (folder.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Каталог не указан!");
            return 1;
        }

        if (!f.exists()) {
            JOptionPane.showMessageDialog(null, "Указанного каталога не существует!");
            return 1;
        }
        return 0;
    }

}