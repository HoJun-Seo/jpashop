package jpabook.jpahop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn 단일 테이블 전략을 사용하여 상속관계를 매핑할 경우, 해당 어노테이션은 없어도 상관없다.
public abstract class Item extends BaseEntity{ // Item 클래스에만 따로 데이터를 저장할 일이 없다고 가정해서 abstract 키워드를 사용해 추상 키워드로 만들어준다.

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;

	private String name;
	private int price;
	private int stockQuantity;

	@ManyToMany(mappedBy = "items") // 연관관계의 주인 클래스가 아니므로 mappedBy 속성을 활용한다.
	private List<Category> categories = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
