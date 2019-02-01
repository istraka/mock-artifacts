package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import java.rmi.RemoteException;

@Stateless
@Remote(HelloBeanRemote.class)
public class HelloBean {
    private static Logger log = Logger.getLogger(HelloBean.class);


    public String hello() throws RemoteException {
        log.info("hello called with message");
        return "Hello there";
    }
}
