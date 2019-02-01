package client;


import ejb.HelloBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Client {

    public static void main(String[] args)
            throws Exception {
        call("server-1", true);
        call("server-2", true);

        for (int i = 30; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        call("server-1", false);
    }

    public static Properties getCtxProperties() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        return props;
    }

    public static void call(String container, boolean exceptedFailure) {
        try {
            InitialContext ctx = new InitialContext(getCtxProperties());
            String lookupName = "ejb:/" + container + "/HelloBean!ejb.HelloBeanRemote";
            HelloBeanRemote bean = (HelloBeanRemote) ctx.lookup(lookupName);
            System.out.println(bean.hello());
        } catch (Exception e) {
            if (exceptedFailure) {
                System.out.println("EXPECTED FAILURE");
                e.printStackTrace();
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}
