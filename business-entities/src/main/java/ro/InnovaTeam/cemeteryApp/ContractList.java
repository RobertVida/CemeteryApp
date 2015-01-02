package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 1/2/2015.
 */
public class ContractList extends BaseList<ContractDTO> implements Serializable {

    public ContractList() {
    }

    public ContractList(List<ContractDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "ContractList{" +
                "content=" + content +
                '}';
    }
}
