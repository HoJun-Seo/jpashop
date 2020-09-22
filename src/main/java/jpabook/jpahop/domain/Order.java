package jpabook.jpahop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "MEMBER_ID") // 객체를 관계형 DB에 맞춰서 설계할 경우의 방식(데이터 중심 설계)
	private Long memberId;
	//Member 측의 주문 데이터를 가져오는데 있어, 위와 같이 주문번호 자체를 사용하여 해당 멤버를 찾는것보다
	//아예 주문 클래스에서 Member 측의 데이터를 곧장 가져올 수 있게끔 만들면 훨씬 간편하고 객체지향적으로
	//프로그램을 코딩할 수 있다.
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
