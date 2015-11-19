package sarin.app.restcontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;







import org.springframework.web.servlet.ModelAndView;

import sarin.app.entities.StudentDTO;
import sarin.app.services.StudentService;

@RestController
@RequestMapping("/")
public class restController {
	@Autowired
	StudentService serviceImplement;	
	
	@RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
	public ModelAndView homePage(){
		ModelAndView model= new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value={"/list"}, method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllStudent(@RequestParam(required = false) Integer page ) throws SQLException{
		Map<String, Object> map = new HashMap<String,Object>();
		if(page==null ||  page.equals("")){
				page=0;
		}
		ArrayList<StudentDTO> user = serviceImplement.list(5, page);
		
	
		if(user.isEmpty()){
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "USER NOT FOUND...");
			return new ResponseEntity<Map<String,Object>>
										(map,HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "USER HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", serviceImplement.list(5, page));
		map.put("RECORDE", serviceImplement.listAllrecorde().size());
								
	
		return new ResponseEntity<Map<String,Object>>
		(map,HttpStatus.OK);
	}

	
}
