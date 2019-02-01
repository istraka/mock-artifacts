package client;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import ejb.IIOPNamingHome;
import ejb.IIOPRemote;


public class Client {

    public static void main(String[] args)
            throws Exception {

        final Properties prope = new Properties();
        System.setProperty("com.sun.CORBA.ORBUseDynamicStub", "true");
        prope.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
        prope.put(Context.PROVIDER_URL, "corbaloc::127.0.0.1:3528/NameService");
        final InitialContext context = new InitialContext(prope);
        final Object iiopObj = context.lookup("IIOPNamingBean");
        final IIOPNamingHome object = (IIOPNamingHome) PortableRemoteObject.narrow(iiopObj, IIOPNamingHome.class);
        final IIOPRemote result = object.create();
        System.out.println(result.hello());
    }
}
