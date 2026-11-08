package com.calebandwill.webserver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.util.JSONPObject;

import java.sql.SQLType;

@Entity
public class TextObfuscationEntity {

    @Id
    private int id;
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonIgnore // @TODO Might be an issue soon
    private JSONPObject mapJsonObject;



}
