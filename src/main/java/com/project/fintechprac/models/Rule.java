package com.project.fintechprac.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Setter
@Getter
@Table(name = "rules")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class Rule extends BaseEntity{
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RuleType type;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "params", nullable = false)
    private Map<String,Object> params;
    @Column(name = "weight", nullable = false)
    private int weight;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
