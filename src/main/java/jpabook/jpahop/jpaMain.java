package jpabook.jpahop;

import jpabook.jpahop.domain.Member;
import jpabook.jpahop.domain.Order;
import jpabook.jpahop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try{

			/*Order order = em.find(Order.class, 1L);
			Long memberId = order.getMemberId();

			Member member = em.find(Member.class, memberId);*/ // 객체지향 스럽지 않은 방식

			//Order order = em.find(Order.class, 1L);
			//Member findMember = order.getMember();
			// Order 클래스에서 Member 객체를 얻어올 수 있게끔 객체를 선언해두면
			// 위와 같이 간편하게 Member 정보를 찾아올 수 있다.
			// Member 객체를 선언해두지 않았을 경우 과정 : 1. 주문 번호 검색 2. 주문에 해당하는 멤버 번호 검색 3. 멤버 검색
			// Member 객체를 선언해두었을 경우 과정 : 1. 주문 번호 검색 2. 주문 번호에 해당하는 멤버 검색
			// 즉, 멤버 번호를 찾는과정을 거칠 필요 없이 더 빠르게 멤버 정보를 찾아올 수 있다.

			Order order = new Order();
			//order.addOrderItem(new OrderItem());
			em.persist(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			// 굳이 양방향 매핑을 하지 않아도 이런식으로 코드를 작성해줄 수 있다.
			// 즉, 양방향 연관관계가 아니라도 애플리케이션 개발하는데 별 다른 문제가 없다.
			// 그럼에도 불구하고 양방향 연관관계로 매핑하여 OrderItems : List 같은 걸 만드는 이유? : 개발상의 편의 또는 나중에 조회가 필요하면 사용하기도 한다.
			// 그런데 실전에서는 JPQL 을 많이 활용하게 되는데, JPQL 을 약간 복잡하게 짜는 이유들 때문에 양방향 연관관계가 필요하다고 할 수 있다.(자세한건 모르겠음, 직접 겪어봐야 알것 같다.)
			// 핵심은 할 수 있으면 최대한 단방향으로 하되, 그런데 실무에서는 아무래도 조회를 편하게 하거나, JPQL 을 좀 더 편하게 작성하려다 보니 양쪽 방향으로 조회해야 될 일이 많이 생기게 된다.

			em.persist(orderItem);

			tx.commit();
		} catch (Exception e){
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
