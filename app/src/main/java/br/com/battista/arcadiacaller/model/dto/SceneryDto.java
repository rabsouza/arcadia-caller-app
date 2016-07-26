package br.com.battista.arcadiacaller.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SceneryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean completed = Boolean.FALSE;

}
