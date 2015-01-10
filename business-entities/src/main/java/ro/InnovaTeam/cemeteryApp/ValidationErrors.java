package ro.InnovaTeam.cemeteryApp;

/**
 * Created by robert on 1/9/2015.
 */
public abstract class ValidationErrors {
    /*CEMETERY*/
    public static final String CEMETERY_NAME_BLANK = "Numele este invalid.";
    public static final String CEMETERY_ADDRESS_BLANK = "Adresa este invalida.";

    /*CLIENT*/
    public static final String CLIENT_FIRST_NAME_BLANK = "Prenumele clientului este invalid.";
    public static final String CLIENT_LAST_NAME_BLANK = "Numele clientului este invalid.";
    public static final String CLIENT_CNP_INVALID = "CNP-ul trebuie sa contina fix 13 cifre.";
    public static final String CLIENT_PHONE_NUMBER_INVALID = "Numarul de telefon trebuie sa contina fix 10 cifre.";
    public static final String CLIENT_ADDRESS_BLANK = "Adresa este invalida.";

    /*CONTRACT*/
    public static final String CONTRACT_STRUCTURE_ID_NULL = "Id-ul structurii este necesar.";
    public static final String CONTRACT_REQUEST_ID_NULL = "Id-ul cererii este necesar.";
    public static final String CONTRACT_SIGNED_ON_NULL = "Data semnarii contractului este necesara.";
    public static final String CONTRACT_UPDATED_INVALID = "Data de actualizare este invalida. Asigura-te ca este dupa data semnarii contractului.";
    public static final String CONTRACT_EXPIRES_INVALID = "Data de expirare este invalida. Asigura-te ca este dupa data actualizarii contractului.";

    /*DECEASED*/
    public static final String DECEASED_FIRST_NAME_BLANK = "Prenumele decedatului este invalid.";
    public static final String DECEASED_LAST_NAME_BLANK = "Numele decedatului este invalid.";
    public static final String DECEASED_CNP_INVALID = "CNP-ul trebuie sa contina fix 13 cifre.";
    public static final String DECEASED_RELIGION_BLANK = "Religia este invalida.";
    public static final String DECEASED_DIED_ON_INVALID = "Data de deces este invalida.";
    public static final String DECEASED_STRUCTURE_ID_INVALID = "Id-ul structurii este necesar.";
    public static final String DECEASED_BURIAL_ON_INVALID = "Data de inmormantare este invalida.";

    /*REQUEST*/
    public static final String REQUEST_CLIENT_ID_INVALID = "";
    public static final String REQUEST_CREATED_ON_INVALID = "";
    public static final String REQUEST_INFOCET_NUMBER_INVALID = "";
    public static final String REQUEST_STATUS_BLANK = "";

    /*STRUCTURE_HISTORY*/
    public static final String STRUCTURE_HISTORY_STRUCTURE_ID_INVALID = "Id-ul structurii este necesar.";
    public static final String STRUCTURE_HISTORY_DESCRIPTION_BLANK = "Descrierea este este necesara.";
    public static final String STRUCTURE_HISTORY_DATE_INVALID = "Data este invalida.";

    /*USER*/
    public static final String USER_USERNAME_BLANK = "Numele utilizatorului este invalid.";
    public static final String USER_PASSWORD_BLANK = "Parola este invalida.";

    /*GRAVE*/
    public static final String GRAVE_PARCEL_ID_INVALID = "Id-ul parcelei este necesar.";
    public static final String GRAVE_CREATED_ON_INVALID = "Data de creare este invalida.";
    public static final String GRAVE_TYPE_BLANK = "Eroare necunoscuta. Incearca mai tarziu.";
    public static final String GRAVE_WIDTH_INVALID = "Latimea este invalida. Asigura-te ca e mai mult de 1m.";
    public static final String GRAVE_LENGTH_INVALID = "Lungimea este invalida. Asigura-te ca e mai mult de 1m.";

    /*MONUMENT*/
    public static final String MONUMENT_PARCEL_ID_INVALID = "Id-ul parcelei este necesar.";
    public static final String MONUMENT_CREATED_ON_INVALID = "Data de creare este invalida.";
    public static final String MONUMENT_TYPE_BLANK = "Eroare necunoscuta. Incearca mai tarziu.";
    public static final String MONUMENT_WIDTH_INVALID = "Latimea este invalida. Asigura-te ca e mai mult de 1m.";
    public static final String MONUMENT_LENGTH_INVALID = "Lungimea este invalida. Asigura-te ca e mai mult de 1m.";
    public static final String MONUMENT_NAME_BLANK = "Numele este invalid.";
    public static final String MONUMENT_DESCRIPTION_BLANK = "Descrierea este invalida.";

    /*PARCEL*/
    public static final String PARCEL_NAME_BLANK = "Numele este invalid.";
    public static final String PARCEL_CEMETERY_ID_INVALID = "Id-ul cimitirului este necesar.";
}
