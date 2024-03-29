package rocketBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import rocketDomain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {

	public static ArrayList<RateDomainModel> getAllRates()
	{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		ArrayList<RateDomainModel> alRates = new ArrayList<RateDomainModel>();		
		
		try {
			tx = session.beginTransaction();	
			
			//TODO - RocketDALRateDAL.getAllRates
			//			probably not a bad idea to sort the results...  Add an OrderBy
			//			example can be found here:
			//  		http://www.tutorialspoint.com/hibernate/hibernate_query_language.htm			
			Query lstRates = session.createQuery("FROM RateDomainModel ORDER BY RateDomainModel.iMinCreditScore ASC).list();");
			for (Iterator iterator = lstRates.iterate(); iterator.hasNext();) {
				RateDomainModel rte = (RateDomainModel) iterator.next();
				alRates.add(rte);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alRates;
	}

}