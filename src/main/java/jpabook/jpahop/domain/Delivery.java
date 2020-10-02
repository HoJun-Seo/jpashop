package jpabook.jpahop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery {

	@Id @GeneratedValue
	private Long id;

	// 배송지 주소
	private String city;
	private String street;
	private String zipcode;
	private DeliberyStatus status;

	@OneToOne(mappedBy = "delivery")
	private Order order;
}
