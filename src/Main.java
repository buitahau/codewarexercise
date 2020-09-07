import com.java8.tutorial.codewar.simple_prime_streaming.SimplePrimeStreaming;
import com.java8.tutorial.functional_interface.ConsumerTest;
import com.java8.tutorial.functional_interface.FunctionalInterfaceTest;
import com.java8.tutorial.object.User;

import java.util.Hashtable;
import java.util.function.DoubleSupplier;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public static void main(String[] args)
                    throws NamingException {

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext" +
                        ".RefFSContextFactory");

        Context ctx = new InitialContext(env);
        User u = (User)ctx.lookup("com.java8.tutorial.object.User");

        FunctionalInterfaceTest functionalInterface = () ->
            System.out.println("Call Functional Interface from Java 8");

        DoubleSupplier doubleSupplier = () -> Double.SIZE;

        ConsumerTest<User> consumerTest = new ConsumerTest<User>() {

            @Override public void accept(User user) {
                user.setUserId(1);
                System.out.println("run first" + user.getUserId());
            }
        };

        ConsumerTest<User> consumerTest1 = user -> {
            user.setUserName("hau");
            System.out.println("run second" +  user.getUserId() + user.getUserName());
        };

        consumerTest.accept(new User());
        // both consumer use same object
        consumerTest.andThen(consumerTest1).accept(new User());
        functionalInterface.call();

    }
}
