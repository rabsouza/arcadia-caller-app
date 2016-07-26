package br.com.battista.arcadiacaller.model.dto;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuildDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private NameGuildEnum name;

    private String username;

}
