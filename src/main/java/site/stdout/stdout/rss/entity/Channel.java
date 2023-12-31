package site.stdout.stdout.rss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 20)
public class Channel extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String icon;
	private String name;
	private String subname;
	private boolean isInternational;

}
