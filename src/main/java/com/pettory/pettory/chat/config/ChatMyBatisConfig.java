package com.pettory.pettory.chat.config;

import com.pettory.pettory.chat.dto.chatting.InsertChattingDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import com.pettory.pettory.chat.mapper.ChattingMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ChatMyBatisConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.jdbc-url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        /* 데이터베이스 연결을 시도할 때 타임 아웃 (밀리세컨 단위 - 30초) */
        dataSource.setConnectionTimeout(30000);

        /* 데이터베이스 연결 유휴 상태 (쿼리를 보내지 않고 데이터를 업데이트 하지 않는 시간) 유지 - 10분 */
        dataSource.setIdleTimeout(600000);

        /* 데이터베이스 연결 최대 유지 시간 - 30분 */
        dataSource.setMaxLifetime(1800000);

        /* 미리 만들 커넥션 객체 갯수 */
        dataSource.setMaximumPoolSize(50);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.getTypeAliasRegistry().registerAlias("InsertChattingDTO", InsertChattingDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("ModifyChattingDTO", ModifyChattingDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("SoftDeleteChattingDTO", SoftDeleteChattingDTO.class);
        configuration.addMapper(ChattingMapper.class);
        configuration.setMapUnderscoreToCamelCase(true);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
