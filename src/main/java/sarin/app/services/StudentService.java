package sarin.app.services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sarin.app.entities.StudentDTO;


public interface StudentService {
		
	ArrayList<StudentDTO> list(int limit ,int page) throws SQLException;
}
