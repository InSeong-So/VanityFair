package vanityfair2.web.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column(unique = true, nullable = false, length = 20)
	private String id;

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
}
