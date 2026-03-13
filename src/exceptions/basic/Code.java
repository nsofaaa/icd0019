package exceptions.basic;

public class Code {

    public String readDataFrom(FakeFile file) {
        try {
            file.open();
            String text = file.read();
            return text;
        }
        catch (Exception e) {
            return "some default value";
        }
        finally {
            try {
               file.close();
            }
            catch(Exception e) {
            }
        }



    } //фигурные скобки аналог отступа в питоне

    public static Integer minimumElement(int[] integers) {
        if (integers == null || integers.length == 0) {        // ("||" - или) ("&&" - и)
            throw new IllegalArgumentException("spisok pustoj");
        }

        int minimumElement = integers[0];

        for (int current : integers) {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

    public static boolean containsSingleLetters(String s) {
        if (s == null ) {
            throw new IllegalArgumentException("string pustoj");

        }

            int index = 0;


            while (index < s.length()) {
                if (s.charAt(index) == s.charAt(index + 1)) {
                    return false;
                }

                index++;
            }

            return true;
    }
}
