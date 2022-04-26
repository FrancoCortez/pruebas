package cl.pim.pimcoredb.auth;

import cl.pim.pimcoredb.base.BaseIdEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "user_history")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserHistory extends BaseIdEntity {

    @Column(name = "mail", length = 100, unique = true)
    private String mail;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "last_password_update")
    private LocalDateTime lastPasswordUpdate;

    @Column(name = "code_role", length = 50)
    private String codeRole;

    @Column(name = "id_role", length = 36)
    private String idRole;

    @Column(name = "name_role", length = 50)
    private String nameRole;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
