package jpabook.jpahop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;

	// 대부분의 경우 Member 에 Order 클래스에 객체를 선언하여 양방향 매핑을 하는 것이 그닥 좋은 설계가 아니다.
	// 테이블 입장에서 쿼리를 사용한다고 해도 MEMBER_ID 와 같은 외래 키를 통해 결과를 얻던가 하게 된다.
	// 그런데 Member 를 찾아서 getOrders 를 찾아 주문 내역을 뿌리는 식으로 만들면 설계를 잘못했다고 볼 수도 있다.
	// 연관관계 매핑을 할 때는 끊어내야 할 관심사를 잘 끊어내는 것이 중요하다.
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
