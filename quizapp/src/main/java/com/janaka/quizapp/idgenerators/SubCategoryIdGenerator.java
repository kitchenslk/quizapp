package com.janaka.quizapp.idgenerators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 6, 2013 - 11:13:25 PM
 * Project	: quizapp
 */
public class SubCategoryIdGenerator implements IdentifierGenerator{
	
private Log logger = LogFactory.getLog(this.getClass());
	
	
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		
		String prefix = "SCT";
        Connection connection = session.connection();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT nextval ('sub_category_id') as nextval");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                String code = prefix + StringUtils.leftPad("" + id,9, '0');
                logger.debug("Generated " + this.getClass().getCanonicalName() + " Id: " + code);
                return code;
            }
            ps.close();
            connection.close();

        } catch (SQLException e) {
        	logger.error(e);
            throw new HibernateException(
                    "Unable to generate " + this.getClass().getCanonicalName() + " Id Sequence");
        }
        return null;
	}


}
