
public class p_arc {

    public static void main (String args[]) {

        String ChattrDir = "";

        try {
            if (args[0] != "") {
                int flag=0;
                for (String s: args) {
                    if (flag == 0) {
                        ChattrDir=args[0];
                        flag=1;
                        continue;
                    }
                    ChattrDir = ChattrDir + " " + s;
                }
            }
        } catch (Exception e) {

        }



        CloseDir closeDir = new CloseDir();
        closeDir.start(ChattrDir);
    }
}
