import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author hukun
 * @version 创建时间：2013-2-19 上午10:45:19
 */
public class DaoSupport {

	public Session getSession() {
		//读取hibernate.cfg.xml文件
		Configuration cfg = new Configuration().configure();

		//创建SessionFactory，一个数据库对应一个SessionFactory
		SessionFactory sf = cfg.buildSessionFactory();

		//相当于JDBC中的Connection，但和Connectio不一样，
		//可以理解为对Connection做了一层封装
		//创建Session，从工厂创建出一个
		Session session = sf.openSession();
		return session;
	}
	
	public void close(Session session){
		//如果事务不为空
		if (session != null) {
			//如果事务没有关闭
			if (session.isOpen()) {
				//那么关闭事务
				session.close();
			}
		}
	}
}
