package vagapov.user_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    /**
     * Версия
     */
    private Integer version;

    /**
     * GUID пользователя
     */
    private UUID userGuid;

    /**
     * Признак удаленного пользователя
     */
    private Boolean deleted;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * ФИО
     */
    private String fullName;

    /**
     * День рождения
     */
    private String birthDate;

    /**
     * Место рождения
     */
    private String birthPlace;

    /**
     * Пол
     */
    private String sex;

    /**
     * Буквенный код страны гражданство
     */
    private String citizenshipCode;
}
