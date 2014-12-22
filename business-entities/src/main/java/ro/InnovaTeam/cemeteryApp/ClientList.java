package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
public class ClientList extends BaseList<ClientDTO> implements Serializable {

    public ClientList() {
    }

    public ClientList(List<ClientDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "ClientList{" +
                "content=" + content +
                '}';
    }
}
