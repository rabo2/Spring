package kr.or.ddit.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.dto.MemberVO;

public class User implements UserDetails {
	
	private MemberVO member; 
	
	public User(MemberVO member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(member.getAuthority()));
		return roles;
	}

	@Override
	public String getPassword() {
		return member.getPwd();
	}

	@Override
	public String getUsername() {
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return member.getEnabled() != 4;
	}

	@Override
	public boolean isAccountNonLocked() {
		return member.getEnabled() != 3;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return member.getEnabled() != 2;
	}

	@Override
	public boolean isEnabled() {
		return member.getEnabled() != 0;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
