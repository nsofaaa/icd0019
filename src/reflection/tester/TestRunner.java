package reflection.tester;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    private final StringBuilder results = new StringBuilder();

    public void runTests(List<String> testClassNames, String... tagsToRun) {
        for (String className : testClassNames) {
            executeTestsFromClass(className, tagsToRun);
        }
    }

    private void executeTestsFromClass(String className, String... tagsToRun) {
        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(MyTest.class)) {
                    processMethod(m, instance, tagsToRun);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to run tests in class: " + className, e);
        }
    }

    private void processMethod(Method m, Object instance, String... tagsToRun) {
        MyTest ann = m.getAnnotation(MyTest.class);
        List<String> methodTags = Arrays.asList(ann.tags());

        boolean shouldRun = tagsToRun.length == 0 || hasMatchingTag(methodTags, tagsToRun);

        if (!shouldRun) {
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

    private boolean hasMatchingTag(List<String> methodTags, String... tagsToRun) {
        for (String t : tagsToRun) {
            if (methodTags.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public String getResult() {
        return results.toString().trim();
    }

    public static void assertThrows(Runnable code, Class<? extends Exception> expected) {
        try {
            code.run();
        } catch (Exception e) {
            if (expected.isInstance(e)) {
                return;
            }
            throw new AssertionError("Wrong exception type", e);
        }
        throw new AssertionError("Exception was not thrown");
    }
}