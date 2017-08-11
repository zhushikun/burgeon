package com.lwrs.app.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DB属性配置类.
 */
@NoArgsConstructor
@Getter
@Setter
public class DBProperties {

  private String url;

  private String userName;

  private String password;

  private String driverClassName;

}
