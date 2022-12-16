package com.springboot.store.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.store.other.Authority;
import com.springboot.store.other.UserRole;

import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedEntityGraph(name = "UserComplete", attributeNodes = {
		@NamedAttributeNode(value = "userRoles", subgraph = "role-subgraph") }, subgraphs = {
				@NamedSubgraph(name = "role-subgraph", attributeNodes = { @NamedAttributeNode("role") }) })
@Entity
@Data
@Builder
@SuppressWarnings("serial")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@NotNull
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private State state;
	private String Trang_thai;
	@NotNull
	@Email
	private String email;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(userRole -> authorites.add(new Authority(userRole.getRole().getName())));
		return authorites;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + id + "]" + "[username=" + username + "]" + "[password=" + password
				+ "]" + "[email=" + email + "]";
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
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getTrang_thai() {
		return Trang_thai;
	}

	public void setTrang_thai(String trang_thai) {
		Trang_thai = trang_thai;
	}
	
	public static List<User> ls = new ArrayList<User>();
	
	public User findByUsername(String username) {
		for(User user : ls) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	public int update(User user) {
		for(int i = 0; i<ls.size();i++) {
			if(ls.get(i).getUsername().equals(user.getUsername())) {
				ls.set(i, user);
				return i;
			}
		}
		
		return -1;
	}
	public int save(User user) {
		if(findByUsername(user.getUsername())!= null) {
			update(user);
		}else {
			ls.add(user);
		}
		return 1;
	}
	public int delete(String username) {
		User u = findByUsername(username);
		if(u != null) {
			ls.remove(u);
			return 1;
		}
		return 0;
	}
	public List<User> getAll(){
		return ls;
	}

}