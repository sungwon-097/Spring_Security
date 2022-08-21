package com.cos.security1.config.auth;

// security 가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
// 로그인 진행이 완료가 되면 Security Session 을 만들어 줌(Security ContextHolder)
// object => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야함
// User 오브젝트의 타입은 UserDetails

// security 가 가지고 있는 Security Session => Authentication => UserDetails

import com.cos.security1.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user; // composition
    private Map<String, Object> attributes;
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    public PrincipalDetails(User user){
        this.user = user;
    }
    public PrincipalDetails(User user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }
    // 해당 user 의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>(); // ArrayList 는 Collection 의 자식이다
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    public User getUserEntity(){return this.user;}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
//        user.getLoginDate(); 으로 휴면계정 판단 가능
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
