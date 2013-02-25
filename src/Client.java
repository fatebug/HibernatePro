import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import model.TdoOrderMaster;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author hukun
 * @version 创建时间：2013-2-19 上午09:34:30
 */
public class Client {
	private DaoSupport dao = new DaoSupport();
	
	public static void main(String[] args) {
		Client client = new Client();
//		client.delete();
		client.operate();
	}

	@SuppressWarnings("deprecation")
	public void operate() {
		Session session = null;
		Transaction tx = null;
		TdoOrderMaster order = null;
		Serializable id = null;
		/*
		try {
			session = dao.getSession(); //创建Session，从工厂创建出一个
			tx = session.beginTransaction(); //打开事务（hibernate默认的事务是False，所以得手动开启事务）

			order = new TdoOrderMaster();
			order.setOrderNo("test0957");
			Serializable id = session.save(order);
//			session.persist(order);
			System.out.println("id = "+id);
			tx.commit(); //手动提交，提交事务你必须得到原先开启事务的上下文，不能随便提交。可以用Session.getTransanction()来得到先前开启事务的上下文
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		} finally {
			dao.close(session);
		}
		
		order.setICity("上海");
		order.setBAddress1("深圳");
		
		try {
			session = dao.getSession();
			tx = session.beginTransaction();
			
//			session.update(order);
//			order.setBAddress2("上海");
//			order.setBAddress3("上海");
			
			order = (TdoOrderMaster)session.load(TdoOrderMaster.class, new Long(241));
			order.setBAddress4("上海");
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally{
			dao.close(session);
		}
		
		
		try {
			Configuration config = new Configuration().configure();
			SessionFactory sf = config.buildSessionFactory();
			session = sf.openSession();
			Connection con= session.connection();
			boolean isTrue = con.getAutoCommit();
			System.out.println("AutoCommit = "+isTrue); //~false
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dao.close(session);
		}
		
		//#
		try {
			Configuration config = new Configuration().configure();
			SessionFactory sf = config.buildSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			order = new TdoOrderMaster();
			order.setBAddress1("上海");
			id = session.save(order);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally{
			dao.close(session);
		}
		
		try {
			Configuration config = new Configuration().configure();
			SessionFactory sf = config.buildSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			id = new Long(246);
			order = (TdoOrderMaster)session.load(TdoOrderMaster.class, id);
			order.setBAddress2("上海");
			order.setBAddress3("上海");
			order.setBAddress4("上海");
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}finally{
			dao.close(session);
		}
		*/
		try {
			Configuration c = new Configuration().configure();
			SessionFactory sf = c.buildSessionFactory();
			session = sf.openSession();
			id = new Long(246);
			order = (TdoOrderMaster)session.load(TdoOrderMaster.class, id);
			System.out.println("getBAddress1 = "+order.getBAddress2());
			System.out.println("getBAddress2 = "+order.getBAddress2());
			System.out.println("getBAddress3 = "+order.getBAddress2());
			System.out.println("getBAddress4 = "+order.getBAddress2());
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		
	}

	public void delete() {
		Session session = null;
		try {
			session = dao.getSession();
			session.beginTransaction();

			TdoOrderMaster order = (TdoOrderMaster) session.get(TdoOrderMaster.class, new Long(241));
			session.delete(order);
			System.out.println("OrderNo = "+order.getOrderNo());

			session.getTransaction().commit();
			System.out.println("OrderNo ===== "+order.getOrderNo());
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			dao.close(session);
		}
	}

}
