package vanityfair2.web.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	// @ManyToOne
	// @JoinColumn(foreignKey = @ForeignKey(name = "fk_board_writer"))
	// private Users writer;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private int category;

}
