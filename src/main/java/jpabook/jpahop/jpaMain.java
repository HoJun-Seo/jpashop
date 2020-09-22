package jpabook.jpahop;

import jpabook.jpahop.domain.Member;
import jpabook.jpahop.domain.Order;

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

			Order order = em.find(Order.class, 1L);
			Member findMember = order.getMember();
			// Order 클래스에서 Member 객체를 얻어올 수 있게끔 객체를 선언해두면
			// 위와 같이 간편하게 Member 정보를 찾아올 수 있다.
			// Member 객체를 선언해두지 않았을 경우 과정 : 1. 주문 번호 검색 2. 주문에 해당하는 멤버 번호 검색 3. 멤버 검색
			// Member 객체를 선언해두었을 경우 과정 : 1. 주문 번호 검색 2. 주문 번호에 해당하는 멤버 검색
			// 즉, 멤버 번호를 찾는과정을 거칠 필요 없이 더 빠르게 멤버 정보를 찾아올 수 있다.

			tx.commit();
		} catch (Exception e){
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
