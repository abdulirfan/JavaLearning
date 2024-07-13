package javatopics.corejava.variables;

class Student
{
    String name;
    int age;
}

public class InstanceVariables {
    int instanceVariable; // it is the instance variable of type int, the default value is zero.
    Student student; // it is the instance variable of type Student, the default value is null.

    public static void main(String[] args) {
        InstanceVariables instanceVariables = new InstanceVariables();
        System.out.println("default value of instanceVariable is "+ instanceVariables.instanceVariable);
        System.out.println("default value of Student is " + instanceVariables.student);
        //instance variable can only be accessed with object reference
        System.out.println("------------");
        instanceVariables.printDefinition();
    }

    void printDefinition()
    {
        System.out.println(
                "In Java, an instance variable (also known as a member variable or non-static field) is a variable that is declared within a class but outside any method, constructor, or block.\n" +

                "Instance variables are associated with objects of the class and each object has its own copy of the instance variables.\n" +
                "These variables are initialized when the object is created and remain in memory as long as the object exists.\n" +
                "Key Characteristics of Instance Variables:\n" +
                "Declaration: Instance variables are declared within a class but outside any method, constructor, or block using the syntax <access-modifier> <data-type> <variable-name>;.\n" +
                "Initialization: Instance variables are initialized when an object of the class is instantiated (created) using the new keyword.\n" +
                "Scope: Instance variables are accessible throughout the class. They are typically accessed using object references (this) within instance methods or through object references from outside the class (if the access modifier allows).\n" +
                "Default Values: Instance variables are assigned default values if not explicitly initialized. The default values depend on the data type (e.g., 0 for numeric types, false for boolean, null for reference types).\n" +
                "Lifetime: The lifetime of instance variables is tied to the lifetime of the object they belong to. They are destroyed when the object is garbage collected.");
    }
}


