
public class p_arc {

    public static void main (String args[]) {

        String ChattrDir = "";

        try {
            if (args[0] != "")
                ChattrDir = args[0];
        } catch (Exception e) {

        }

        CloseDir closeDir = new CloseDir();
        closeDir.start(ChattrDir);
    }
}
