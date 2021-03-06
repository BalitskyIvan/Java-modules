package edu.school21.app;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    private Scanner scan;
    private String className;
    private Class<?> myClass;
    private Field[] fields;
    private Method[] methods;
    private ArrayList<Method> listMethods;
    private ArrayList<String> listStringMethods;
    private Class<?>[] parametersInput;
    private Object finalObject;


    public Program() {
        printProgramOperations();
    }
    private void printProgramOperations() {
        System.out.println("Classes:");
        System.out.println("  - User");
        System.out.println("  - Car");
        System.out.println("---------------------");
        System.out.println("Enter class name:");
        getInputString();

    }
    private void getInputString() {
        scan = new Scanner(System.in);
        className = scan.nextLine();
        try {
            getFieldsAndMethods();
            createObject();
            changeField();
            callMethod();
            scan.close();
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private void getFieldsAndMethods() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        myClass = Class.forName("edu.school21.classes." + className);
        fields = myClass.getDeclaredFields();
        methods = myClass.getDeclaredMethods();
        printFields();
        printMethods();

    }
    private void printFields() {
        System.out.println("---------------------");
        System.out.println("fields:");
        for (Field field : fields) {
            System.out.println("        " + field.getType().getSimpleName() + " " + field.getName());
        }
    }
    private void printMethods() {
        System.out.println("methods:");
        listStringMethods = new ArrayList<>();
        listMethods = new ArrayList<>();
        for (Method method : methods) {
            if (!(isOverridden(method))) {
                listMethods.add(method);
                System.out.print("        " + method.getReturnType() + " ");
                createListsMethods(method);
            }
        }
    }
    private void createListsMethods(Method method) {
        String tmp = method.getName() + "(";
        int length = method.getParameters().length;
        for (Parameter param : method.getParameters()) {
            tmp += param.getType();
            if (length > 1) {
                tmp += ", ";
                length--;
            }
        }
        tmp += ")";
        System.out.println(tmp);
        listStringMethods.add(tmp);
    }
    private boolean isOverridden(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String methodName = method.getName();

        try {
            for (Class<?> t = myClass; !t.equals(Object.class); t = t.getSuperclass()) {
                if (t.equals(myClass)) {
                    continue;
                }
                Method tm = t.getDeclaredMethod(methodName, parameterTypes);
                if (tm.getName().equals(method.getName()) &&
                        Arrays.equals(tm.getParameterTypes(), method.getParameterTypes()) &&
                        tm.getReturnType().equals(method.getReturnType())) {
                    return true;
                }
            }
            Method objm = Object.class.getDeclaredMethod(methodName, parameterTypes);
            if (objm.getName().equals(method.getName()) &&
                    Arrays.equals(objm.getParameterTypes(), method.getParameterTypes()) &&
                    objm.getReturnType().equals(method.getReturnType())) {
                return true;
            }
        } catch (NoSuchMethodException ignored) {}
        return false;
    }
    private void createObject() {
        System.out.println("---------------------");
        System.out.println("Let’s create an object.");
        parametersInput = new Class[fields.length];
        int count = 0;
        Object[] obj = new Object[fields.length];
        for (Field field : fields) {
            System.out.println(field.getName() + ":");
            obj[count] = scan.nextLine();
            parametersInput[count] = field.getType();
            count++;
        }
        for (int i = 0; i < count; i++) {
            obj[i] = castObjects(obj[i].toString(), parametersInput[i]);
        }
        finalObject = makeObject(obj);
        System.out.println("Object created: " + finalObject);

    }
    private <instansetype> instansetype makeObject(Object[] obj) {
        try {
            Constructor<?> constructor = myClass.getDeclaredConstructor(parametersInput);
            instansetype instanseObj = (instansetype) constructor.newInstance(obj);
            return instanseObj;
        } catch (Exception ignored) {
            return null;
        }
    }
    private Object castObjects(String str, Class type) {
        if (Boolean.class == type || boolean.class == type) {
            return Boolean.parseBoolean(str);
        }
        if (Byte.class == type || byte.class == type) {
            return Byte.parseByte(str);
        }
        if (Short.class == type || short.class == type) {
            return Short.parseShort(str);
        }
        if (Integer.class == type || int.class == type) {
            return Integer.parseInt(str);
        }
        if (Long.class == type || long.class == type) {
            return Long.parseLong(str);
        }
        if (Float.class == type || float.class == type) {
            return Float.parseFloat(str);
        }
        if (Double.class == type || double.class == type) {
            return Double.parseDouble(str);
        }
        return str;
    }
    private void changeField() {
        System.out.println("---------------------");
        System.out.println("Enter name of the field for changing:");
        String field = scan.nextLine();
        for (int i = 0; i < fields.length; i++) {
            if (field.equals(fields[i].getName())) {
                change(field, i);
            }
        }
    }
    private void change(String field, int index) {
        System.out.println("Enter " + fields[index].getType().getSimpleName() + " value");
        try {
            fields[index].setAccessible(true);
            fields[index].set(finalObject, scan.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Object updated: " + finalObject);
    }
    private void callMethod() {
        System.out.println("Enter name of the method for call: ");
        String method = scan.nextLine();
        for (int i = 0; i < listStringMethods.size(); i++) {
            if (method.equals(listStringMethods.get(i))) {
                call(method, i);
            }
        }
    }
    private void call(String method, int index) {
        try {
            Class<?>[] parameters = listMethods.get(index).getParameterTypes();
            Object[] args = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                System.out.println("Enter " + parameters[i].getSimpleName() + " value");
                args[i] = scan.nextLine();
                args[i] = castObjects(args[i].toString(), parameters[i]);
            }
            listMethods.get(index).setAccessible(true);
            Object obj = listMethods.get(index).invoke(finalObject, args);
            System.out.println("Method returned:");
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
