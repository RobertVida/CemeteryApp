package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Roxana on 11/28/2014.
 */
public class ClientList implements Serializable {

    private List<ClientDTO> content;

    public ClientList(){}

    public ClientList(List<ClientDTO> content){
        this.content = content;
    }

    public List<ClientDTO> getContent(){
        return content;
    }

    public void setContent(List<ClientDTO> content){
        this.content = content;
    }

    @Override
    public String toString() {
        return "ClientList{" +
                "content=" + content +
                '}';
    }
}
