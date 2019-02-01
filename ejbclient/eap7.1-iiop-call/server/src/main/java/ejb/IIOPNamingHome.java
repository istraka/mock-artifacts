package ejb;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * @author Stuart Douglas
 */
public interface IIOPNamingHome extends EJBHome {

    IIOPRemote create() throws RemoteException;

}
