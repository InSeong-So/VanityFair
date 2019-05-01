package Vanityfair.web.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import Vanityfair.support.exception.UnAuthorizedException;
import Vanityfair.web.domain.master.AbstractEntity;
import lombok.ToString;

@Entity
@ToString
public class User extends AbstractEntity {
	public static final GuestUser GUEST_USER = new GuestUser();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false, length = 20)
	private String userId;

	@Column(nullable = false, length = 16)
	private String password;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String phone;

	@Column
	private String add1;

	@Column
	private String add2;

	@Column
	private String add3;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public User() {

	}

	public User(String userId, String password, String name, String email) {
		this(0L, userId, password, name, email);
	}

	public User(long id, String userId, String password, String name, String email) {
		super(id);
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	private boolean matchUserId(String userId) {
		return this.userId.equals(userId);
	}

	public void update(User loginUser, User target) {

		if (!matchUserId(loginUser.getUserId())) {
			throw new UnAuthorizedException();
		}

		if (!matchPassword(target.getPassword())) {
			return;
		}

		this.name = target.name;
		this.email = target.email;
	}

	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

	public boolean isGuestUser() {
		return false;
	}

	private static class GuestUser extends User {
		@Override
		public boolean isGuestUser() {
			return true;
		}
	}
}
