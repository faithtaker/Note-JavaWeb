package com.example.tliasproject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasProjectApplicationTests {

    /**
     * 生成 JWT 令牌
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<String, Object>();

        claims.put("id", 1);
        claims.put("username", "kirito");

        String jwt = Jwts.builder()
                .setClaims(claims)//自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "itheima")//签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))//有效期
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析jwt
     */
    @Test
    public void parseJwt(){
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey("itheima")//指定签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzAwMTkzNTg5LCJ1c2VybmFtZSI6Imtpcml0byJ9.TCFHK7DsAxmET3-0uKzhLj6IlZ_t9BFCQejS9hDawiA")//解析令牌
                .getBody();
        System.out.println(claims);

    }


}
