package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.UserEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"username"}, callSuper = false)
@Table(name = UserEntry.TABLE_NAME)
@JsonAutoDetect
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = UserEntry.COLUMN_NAME_USERNAME, notNull = true, index = true, unique = true)
    private String username;

    @Column(name = UserEntry.COLUMN_NAME_MAIL, notNull = true, index = true)
    private String mail;

    @Column(name = UserEntry.COLUMN_NAME_URL_AVATAR, notNull = true)
    private String urlAvatar;

    @Column(name = UserEntry.COLUMN_NAME_PROFILE, notNull = true)
    private ProfileAppConstant profile;

    @Column(name = UserEntry.COLUMN_NAME_FRIENDS)
    private List<String> friends = Lists.newArrayList();

    @Override
    public Object getPk() {
        return getId();
    }
}
