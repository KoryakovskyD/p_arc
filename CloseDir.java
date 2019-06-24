


public class CloseDir {

    protected static String curDir = "";

    protected static String getDir() {
        return curDir;
    }


    protected void start(String dir) {

        curDir = dir;

        JFrame frame = new JFrame();
        frame.setVisible(true);
    }
}
