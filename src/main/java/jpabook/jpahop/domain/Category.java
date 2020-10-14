package jpabook.jpahop.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{
	@Id @GeneratedValue
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();
	// Category 클래스 내부에서 다대일 연관관계 매핑을 따로 만들어줬다.

	@ManyToMany
	@JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID")
	, inverseJoinColumns = @JoinColumn(name = "ITEM_ID")) // 중간 테이블 매핑, 다대다 연관관계의 주인으로서 매핑된다.
	private List<Item> items = new ArrayList<>();
}
