package vagapov.user_service.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Данные по пользователю
 */
@Entity
@DynamicUpdate
@Table(name = "USER")
@Setter
@Getter
@EqualsAndHashCode
public class UserEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Версия
     */
    @Version
    private Short version;

    /**
     * GUID пользователя
     */
    @Column(name = "user_guid")
    private UUID userGuid;

    /**
     * Дата создания пользователя
     */
    @Column(name = "create_date")
    private OffsetDateTime createDate;

    /**
     * Последнее изменение пользователя
     */
    @Column(name = "change_date")
    private OffsetDateTime changeDate;

    /**
     * Признак удаленного пользователя
     */
    @Column(name = "deleted")
    private Boolean deleted = false;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * ФИО
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * День рождения
     */
    @Column(name = "birth_date")
    private String birthDate;

    /**
     * Место рождения
     */
    @Column(name = "birth_place")
    private String birthPlace;

    /**
     * Пол
     */
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SexEntity sex;

    /**
     * Буквенный код страны гражданство
     */
    @Column(name = "citizenship_code")
    private String citizenshipCode;
}
