package sarin.app.serviceimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import sarin.app.entities.StudentDTO;
import sarin.app.services.StudentService;

@Repository
public class serviceImplement implements StudentService {
	
	private Connection con;
	@Autowired
	private DataSource dataSource;
	
	
	
	
	public ArrayList<StudentDTO> list(int limit,int page) throws SQLException {		
		int offset = limit * page;		
		try {
			StudentDTO s;
			con=dataSource.getConnection();
			ResultSet rs = null;
			String sql = "SELECT * FROM  student limit ? offset ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			rs=ps.executeQuery();
			ArrayList<StudentDTO> a = new ArrayList<StudentDTO>();			
			while(rs.next()){	
				s = new StudentDTO();
				s.setId(rs.getInt("id"));
				s.setFristname(rs.getString("first_name"));
				s.setLastname(rs.getString("last_name"));
				s.setClassroom(rs.getString("classroom"));
				a.add(s);	
			}			
			return a;
			}catch (SQLException e){
				e.printStackTrace();
			}
			finally {
				con.close();
			}
			return null;	
	}




	public ArrayList<StudentDTO> listAllrecorde() throws SQLException {
		try {
			StudentDTO s;
			con=dataSource.getConnection();
			ResultSet rs = null;
			String sql = "SELECT * FROM  student ";
			PreparedStatement ps = con.prepareStatement(sql);			
			rs=ps.executeQuery();
			ArrayList<StudentDTO> a = new ArrayList<StudentDTO>();			
			while(rs.next()){	
				s = new StudentDTO();
				s.setId(rs.getInt("id"));
				s.setFristname(rs.getString("first_name"));
				s.setLastname(rs.getString("last_name"));
				s.setClassroom(rs.getString("classroom"));
				a.add(s);	
			}			
			return a;
			}catch (SQLException e){
				e.printStackTrace();
			}
			finally {
				con.close();
			}
			return null;			
	}

}
