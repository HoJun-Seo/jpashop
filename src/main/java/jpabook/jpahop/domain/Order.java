package jpabook.jpahop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	//@Column(name = "MEMBER_ID") // 객체를 관계형 DB에 맞춰서 설계할 경우의 방식(데이터 중심 설계)
	//private Long memberId;
	//Member 측의 주문 데이터를 가져오는데 있어, 위와 같이 주문번호 자체를 사용하여 해당 멤버를 찾는것보다
	//아예 주문 클래스에서 Member 측의 데이터를 곧장 가져올 수 있게끔 만들면 훨씬 간편하고 객체지향적으로
	//프로그램을 코딩할 수 있다.

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID") // 연관관계 매핑
	private Member member;
	// 가급적이면 설계할 때는 단뱡향으로 연관관계를 매핑하자, 양방향은 나중에 필요할 때 매핑해도 늦지않다.

	// Member - Order 관계와는 반대로 Order - OrderItem 관계에서는 양방향 매핑이 비즈니스 적으로 상당히 가치 있다고 볼 수 있다.
	// 하나의 주문에서 몇가지 상품이 주문 되었는지 내역을 찾을 경우가 많기도 하기 때문
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public void addOrderItem(OrderItem orderItem){ // 편의성 메소드를 만들자.
		orderItems.add(orderItem);
		orderItem.setOrder(this);
		// 양방향 연관관계를 위한 역방향 삽입
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
