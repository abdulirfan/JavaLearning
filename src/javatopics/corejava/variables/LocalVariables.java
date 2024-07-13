package javatopics.corejava.variables;

public class LocalVariables {

    static {
        int a = 10;
        System.out.println("value of 'a' in static block is " + a);
    }

    LocalVariables(){
        int a =10; // it is a local variable and the scope of this variable is within this constructor block.
        System.out.println("value of 'a' in constructor is " + a); // the value of the variable cannot be accessed outside of this block.
    }

    void sampleMethod()
    {
        int a = 20; // it is a local variable to this method.
        int b = 40;
        int c; // java will not not assign default values to local variables.
        c=60; // value of local variable must be defined before we use it.
        System.out.println("sum of local variable in method is " + (a+b+c));
    }

    static void printDefinition()
    {
        System.out.println(
                "Local Variables:\n"+
                "In Java, a local variable is a variable that is declared within a method, constructor, or block and can only be used within that scope.\n" +
                "Local variables are created when the method, constructor, or block is entered and destroyed once it is exited.\n" +
                "They are not accessible outside of the method, constructor, or block in which they are declared.\n" +
                "\n" +
                "Here are some key points about local variables:\n" +
                "\n" +
                "Scope: The scope of a local variable is limited to the method, constructor, or block in which it is declared.\n" +
                "Initialization: Local variables must be initialized before they can be used. Java does not provide default values for local variables.\n" +
                "Memory: Local variables are stored on the stack, and memory is reclaimed once the method or block execution is complete.\n" +
                "Access Modifiers: Local variables cannot have access modifiers (like public, private, protected) because their scope is already limited to the block in which they are declared.");
    }

    public static void main(String[] args) {
        LocalVariables localVariables = new LocalVariables();
        localVariables.sampleMethod();
        System.out.println("---------");
        printDefinition();
    }
}
