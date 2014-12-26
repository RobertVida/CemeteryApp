package ro.InnovaTeam.cemeteryApp.tests.helpers;

import ro.InnovaTeam.cemeteryApp.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 12/25/2014.
 */
public enum EntityTypes {
    CEMETERY,
    PARCEL,
    GRAVE,
    CLIENT;

    private static Map<Class<? extends BaseDTO>, EntityTypes> classes = new HashMap<Class<? extends BaseDTO>, EntityTypes>(){{
        put(CemeteryDTO.class, CEMETERY);
        put(ParcelDTO.class, PARCEL);
        put(GraveDTO.class, GRAVE);
        put(ClientDTO.class, CLIENT);
    }};

    private static Map<EntityTypes, Class<? extends BaseDTO>> types = new HashMap<EntityTypes, Class<? extends BaseDTO>>(){{
        put(CEMETERY, CemeteryDTO.class);
        put(PARCEL, ParcelDTO.class);
        put(GRAVE, GraveDTO.class);
        put(CLIENT, ClientDTO.class);
    }};

    public static Class<? extends BaseDTO> getClassForType(EntityTypes type){
        return types.get(type);
    }

    public static EntityTypes getTypeForClass(Class<? extends BaseDTO>  clazz){
        return classes.get(clazz);
    }
}
