package reflection.tester;

import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    private StringBuilder results = new StringBuilder();

    public void runTests(List<String> testClassNames, String... tagsToRun) {
        for (String className : testClassNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Object instance = clazz.getDeclaredConstructor().newInstance();

                for (Method m : clazz.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(MyTest.class)) {
                        MyTest ann = m.getAnnotation(MyTest.class);
                        List<String> methodTags = Arrays.asList(ann.tags());

                        // провреяю тест
                        boolean run = (tagsToRun.length == 0);
                        for (String t : tagsToRun) {
                            if (methodTags.contains(t)) run = true;
                        }

                        if (!run) {
                            results.append(m.getName()).append("() - SKIPPED\n");
                        } else {
                            try {
                                m.invoke(instance);
                                results.append(m.getName()).append("() - OK\n");
                            } catch (Exception e) {
                                results.append(m.getName()).append("() - FAILED\n");
                            }
                        }
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public String getResult() {
        return results.toString().trim();
    }

    public static void assertThrows(Runnable code, Class<? extends Exception> expected) {
        try {
            code.run();
        } catch (Exception e) {
            if (expected.isInstance(e)) return;
            throw new AssertionError("Vale erind");
        }
        throw new AssertionError("Erindit ei visatud");
    }
}